package mx.com.ebs.inter.service;

import com.dbxml.db.client.CollectionClient;
import com.dbxml.db.client.dbXMLClient;
import com.dbxml.db.client.xmlrpc.dbXMLClientImpl;
import com.dbxml.util.dbXMLException;
import fe.xml.CharUnicode;
import mx.com.ebs.inter.exception.ApplicationException;
import mx.com.ebs.inter.util.PropertiesLoader;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import mx.com.ebs.inter.util.XmlRecovery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by robb on 25/05/2015.
 */
@Service
public class DbxmlManagerServiceImpl implements DbxmlManagerService {

    private static final Logger LOGGER = Logger.getLogger(DbxmlManagerServiceImpl.class);

    private dbXMLClient getClient(){
        dbXMLClient client = new dbXMLClientImpl();
        client.setProperty(dbXMLClient.USER, PropertiesLoader.getProperty(Variables.DBXML_USER));
        client.setProperty(dbXMLClient.PASS , PropertiesLoader.getProperty(Variables.DBXML_PASSWORD));
        client.setProperty(dbXMLClient.PORT, PropertiesLoader.getProperty(Variables.DBXML_PORT));
        client.setProperty(dbXMLClient.HOST, PropertiesLoader.getProperty(Variables.DBXML_HOST));
        return client;
    }

    private byte[] getXMLDocument(String idDocument, String collectionName) throws ApplicationException {
        byte[] result= null;
        if(Validator.isEmptyString(idDocument) ){
            throw new ApplicationException("idDocument to retrieve cannot be null or empty");
        }
        dbXMLClient client = getClient();
        try {
                LOGGER.debug("Connecting to DBXML server at "+ client.getProperty(dbXMLClient.HOST));
                client.connect();
                LOGGER.debug("Rerieving collection " + collectionName);
                CollectionClient col = client.getCollection(collectionName);
                String xmlDoc = col.getDocumentAsText(idDocument);
                String xmlEncoded = CharUnicode.getTextEncoded(xmlDoc);
                result = xmlEncoded.getBytes();
        } catch(Exception ex) {
            LOGGER.error("Some error occurred while retrieving XML document " + idDocument + ex.getMessage());
        }finally {
            try {
                client.disconnect();
            } catch (dbXMLException e) {
                LOGGER.error("Some error occurred while trying to close DBXML server connection" , e);
            }
        }
        return result;
    }

    @Override
    public byte[] getXMLDocument(String idDocument, String collectionName, boolean useDispatcher) throws ApplicationException {
        if( useDispatcher ){
            try {
                return XmlRecovery.getDocument(idDocument,collectionName);
            } catch (IOException e) {
                LOGGER.error("Error connecting to XMLDispatcher:"+e.getMessage());
                throw new ApplicationException("Error at connecting to XMLDispatcer:"+e.getMessage());
            }
        }else{
            return getXMLDocument(idDocument,collectionName);
        }
    }


    @Override
    public void saveXMLDocument(String idDocument, String collectionName, String content) throws ApplicationException {
        if(Validator.isEmptyString(idDocument) || Validator.isEmptyString(collectionName) || Validator.isEmptyString(content) ){
            throw new ApplicationException("parameters cannot be null or empty");
        }
        dbXMLClient client = getClient();
        try {
            LOGGER.debug("Connecting to DBXML server at "+ client.getProperty(dbXMLClient.HOST));
            client.connect();
            CollectionClient col = client.getCollection(collectionName);
            col.setDocumentAsText(idDocument,content);
        } catch(Exception ex) {
            LOGGER.error("Some error occurred while retrieving XML document " + idDocument, ex );
            throw new ApplicationException("Some error occurred while trying to save XML into DBXML:"+ ex.getMessage());
        }finally {
            try {
                client.disconnect();
            } catch (dbXMLException e) {
                LOGGER.error("Some error occurred while trying to close DBXML server connection" , e);
            }
        }
    }
}
