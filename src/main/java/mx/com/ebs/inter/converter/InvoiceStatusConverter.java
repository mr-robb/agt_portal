package mx.com.ebs.inter.converter;

import mx.com.ebs.inter.util.Variables;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;

/**
 * Created by robb on 30/04/2015.
 */
@FacesConverter("mx.com.ebs.inter.converter.InvoiceStatusConverter")
public class InvoiceStatusConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if( s == null ){
            return null;
        }
        return new BigDecimal(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if( o != null && o instanceof String ){
            try{
                int estatus= Integer.parseInt((String)o);
                return estatus == 0 ? Variables.SIT_COMPROBANTE_0 : Variables.SIT_COMPROBANTE_1;
            }catch (NumberFormatException nfe){}
        }
        if( (null == o)||!(o instanceof BigDecimal) ){
            return "";
        }

        BigDecimal value = (BigDecimal)o;
        if( value.intValue() == 0 ){
            return Variables.SIT_COMPROBANTE_0;
        } else
            return Variables.SIT_COMPROBANTE_1;
    }
}
