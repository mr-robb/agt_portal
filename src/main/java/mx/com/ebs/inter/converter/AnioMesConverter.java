package mx.com.ebs.inter.converter;

import mx.com.ebs.inter.util.DateUtil;
import org.apache.log4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Date;

/**
 * Created by robb on 27/05/2015.
 */
@FacesConverter("mx.com.ebs.inter.converter.AnioMesConverter")
public class AnioMesConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(AnioMesConverter.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if( s == null || s.isEmpty() ){
            return null;
        }
        try{
            return DateUtil.parseYearMonth(s);
        }catch(Exception e){
            LOGGER.error("Error al parsear fecha" , e );
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if((o == null) || !(o instanceof java.util.Date) ){
            return "";
        }
        try{
            return DateUtil.formatYearMonth((Date)o);
        }catch(Exception e){
            LOGGER.error("Error al formatear fecha" , e );
        }
        return "";
    }
}
