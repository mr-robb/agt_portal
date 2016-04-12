package mx.com.ebs.inter.util;

import mx.com.ebs.inter.data.model.RecFelogger;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by robb on 26/06/2015.
 */
public class RecFeloggerBuilder {

    public static final Logger LOGGER = Logger.getLogger(RecFeloggerBuilder.class);

    public static RecFelogger buildRecFelogger(byte [] xmlContent, String agente, String message){
        RecFelogger recFelogger = new RecFelogger();
        org.jdom.input.SAXBuilder sax = new org.jdom.input.SAXBuilder();
        org.jdom.Document doc = null;
        try {
            doc = sax.build(new ByteArrayInputStream(xmlContent));
        } catch (JDOMException e) {
            LOGGER.error("JDOMException while parsing webservice response",e);
        } catch (IOException e) {
            LOGGER.error("IOException while parsing webservice response", e);
        }
        org.jdom.Namespace ns = doc.getRootElement().getNamespace();
        Element root = doc.getRootElement();
        if( Validator.isEmptyString(agente) ){
            agente = root.getChild("Conceptos", ns).getChild("Concepto", ns).getAttribute("descripcion").getValue().substring(29).trim();
        }

        recFelogger.setFECHA(new Timestamp(System.currentTimeMillis()));
        recFelogger.setCLASE("");
        recFelogger.setNIVEL("SEVERE");

        StringBuilder st = new StringBuilder();
        st.append("AGTE");
        st.append(agente);
        st.append(".");
        st.append( root.getAttribute("serie").getValue());
        st.append( root.getAttribute("folio").getValue());

        recFelogger.setID(st.toString());
        recFelogger.setMENSAJE(message);
        recFelogger.setAGENTE(agente);
        recFelogger.setANO("");
        recFelogger.setMES("");
        recFelogger.setSIT_COMPROBANTE(BigDecimal.ONE);
        return recFelogger;

    }
}
