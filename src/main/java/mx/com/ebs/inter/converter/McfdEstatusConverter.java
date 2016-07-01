package mx.com.ebs.inter.converter;

import mx.com.ebs.inter.util.Variables;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;

/**
 * Created by robb on 19/06/2015.
 */
@FacesConverter("mx.com.ebs.inter.converter.McfdEstatusConverter")
public class McfdEstatusConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if( s == null ){
            return null;
        }
        return Long.valueOf(s.trim());
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if( (null == o)||!(o instanceof Long) ){
            return "";
        }
        Long value = (Long)o;
        if( value.intValue() == 0 ){
            return Variables.SIT_COMPROBANTE_0;
        } else
            return Variables.SIT_COMPROBANTE_1;
    }
}
