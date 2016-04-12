package mx.com.ebs.inter.converter;

import org.apache.log4j.Logger;
import org.springframework.format.number.CurrencyFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by robb on 29/04/2015.
 */
@FacesConverter("mx.com.ebs.inter.converter.CurrencyConverter")
public class CurrencyConverter implements Converter{

    private static final CurrencyFormatter formatter = new CurrencyFormatter();
    private static final Logger LOGGER = Logger.getLogger(CurrencyConverter.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if( null == s || s.isEmpty() ){
            return null;
        }
        try {
            return formatter.parse(s, Locale.getDefault());
        } catch (ParseException e) {
            LOGGER.error("Error al parsear monto " + s , e );
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if( (o == null)|| !(o instanceof Double) ){
            return "";
        }
        return formatter.print((Double)o, Locale.getDefault());
    }
}
