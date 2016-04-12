package mx.com.ebs.inter.util;

import mx.com.ebs.inter.data.model.RecInvoice;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecInvoiceBuilder {

    private static final Logger LOGGER = Logger.getLogger(RecInvoiceBuilder.class);

    public static RecInvoice buildRecInvoice(byte [] xmlContent, String agente){
        RecInvoice recInvoice = new RecInvoice();
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

        Double totalImp = 0d;

        if( root.getChild("Impuestos", ns).getAttribute("totalImpuestosTrasladados") != null){
            totalImp = new Double(root.getChild("Impuestos", ns).getAttribute("totalImpuestosTrasladados").getValue());
        }

        if( Validator.isEmptyString(agente) ){
            agente = root.getChild("Conceptos", ns).getChild("Concepto", ns).getAttribute("descripcion").getValue().substring(29).trim();
        }

        recInvoice.setFECHA( new Timestamp(System.currentTimeMillis()));

        StringBuilder st = new StringBuilder();
        st.append("PROV");
        st.append(agente);
        st.append(".");
        st.append( root.getAttribute("serie").getValue());
        st.append( root.getAttribute("folio").getValue());

        recInvoice.setNUMERO_FACTURA( st.toString() );
        recInvoice.setSERIE("AGTE" + agente);
        recInvoice.setFOLIO(root.getAttribute("serie").getValue() + root.getAttribute("folio").getValue());
        recInvoice.setNO_APROBACION( new BigDecimal(root.getAttribute("noAprobacion").getValue()) );
        recInvoice.setAPROBACION(new BigDecimal(root.getAttribute("anoAprobacion").getValue()));
        recInvoice.setNO_CERTIFICADO(root.getAttribute("noCertificado").getValue());
        recInvoice.setIMPORTE(Double.parseDouble( root.getAttribute("subTotal").getValue() ));
        recInvoice.setIMPORTE_TOTAL( Double.parseDouble(root.getAttribute("total").getValue()) );
        recInvoice.setRFC( root.getChild("Emisor", ns).getAttribute("rfc").getValue());
        recInvoice.setRAZON_SOCIAL( root.getChild("Emisor",ns).getAttribute("nombre").getValue() );
        recInvoice.setAGENTE(agente);
        recInvoice.setANO("");
        recInvoice.setMES("");
        recInvoice.setSIT_COMPROBANTE(BigDecimal.ONE);
        recInvoice.setNUMERO_CLIENTE(agente);
        recInvoice.setTIPO_DOCUMENTO( root.getAttribute("tipoDeComprobante").getValue() );
        recInvoice.setIMPORTE_IVA(totalImp);
        recInvoice.setIMPORTE_ISR(0D);
        recInvoice.setIMPORTE_RET(0D);
        return recInvoice;
    }

}
