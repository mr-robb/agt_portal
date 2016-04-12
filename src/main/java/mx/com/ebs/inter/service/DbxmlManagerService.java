package mx.com.ebs.inter.service;

import mx.com.ebs.inter.exception.ApplicationException;

import java.io.OutputStream;

/**
 * Created by robb on 25/05/2015.
 */
public interface DbxmlManagerService {

    byte[] getXMLDocument(String idDocument , String collectionName,boolean useDispatcher) throws ApplicationException;
    void saveXMLDocument(String idDocument, String collectionName, String content) throws ApplicationException;
}