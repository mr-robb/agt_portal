package mx.com.ebs.inter.converter;

import mx.com.ebs.inter.util.Variables;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;

/**
 * Created by robb on 31/05/2015.
 */
@FacesConverter("mx.com.ebs.inter.converter.RecAccesoStatusConverter")
public class RecAccesoStatusConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if( s == null ){
            return null;
        }
        return new BigDecimal(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if( (null == o)||!(o instanceof BigDecimal) ){
            return "";
        }
        BigDecimal value = (BigDecimal)o;
        if( value.intValue() == 1 ){
            return Variables.REC_ACCESO_ACTIVADO;
        }else if( value.intValue()==0 ) {
            return Variables.REC_ACCESO_DESACTIVADO;
        }else{
            return Variables.STR_UNKNOWN;
        }
    }
}
