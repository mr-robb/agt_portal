package mx.com.ebs.inter.service;

import mx.com.ebs.validaciones.sat.ws.WsValidacionesSATLlamado;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by robb on 26/06/2015.
 */
@Service
public class XmlValidatorServiceImpl implements XmlValidatorService {

    private boolean error;

    private static final Logger LOGGER = Logger.getLogger(XmlValidatorServiceImpl.class);

    /**
     *
     * @param fileContent el contenido del archivo como arreglo de bytes
     * @return La descripción del error ó null cuando el archivo pasó la validación
     * @throws Exception
     */
    @Override
    public String validate(byte[] fileContent) throws Exception{
        WsValidacionesSATLlamado validator = new WsValidacionesSATLlamado();
        String validationResponse = validator.invocaWsValidacionesSAT(fileContent,false);
        return processResponse(validationResponse);
    }

    @Override
    public boolean isError() {
        return error;
    }

    private String processResponse(String response){
        org.jdom.input.SAXBuilder sax = new org.jdom.input.SAXBuilder();
        org.jdom.Document doc = null;
        try {
            doc = sax.build(new ByteArrayInputStream(response.getBytes()));
        } catch (JDOMException e) {
            LOGGER.error("JDOMException while parsing webservice response",e);
        } catch (IOException e) {
            LOGGER.error("IOException while parsing webservice response", e);
        }
        org.jdom.Namespace ns = doc.getRootElement().getNamespace();
        Element root = doc.getRootElement();
        List<Element> children =  root.getChildren();
        for(Element e : children){
            if( e.getName().equals("messageError") ){
                error = true;
                Element errorDescription =  e.getChild("errorDescription",ns);
                if( errorDescription != null ){
                    Element text = errorDescription.getChild("text",ns);
                    if( text != null ){
                        return text.getText();
                    }
                }
            }
        }
        return null;
    }
}
