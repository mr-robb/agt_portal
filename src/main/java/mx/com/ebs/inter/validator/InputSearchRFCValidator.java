package mx.com.ebs.inter.validator;

import mx.com.ebs.inter.util.UnicodeCommonWords;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by robb on 04/11/2015.
 */
@FacesValidator("validators.RFCValidator")
public class InputSearchRFCValidator implements Validator {

    private static final String RFC_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(RFC_PATTERN);

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String message = null;
        if( value != null && value instanceof String){
            String tmp = (String)value;
            if( tmp != null && tmp.trim().length() > 0 ){
                Matcher matcher = pattern.matcher(tmp);
                if( !matcher.matches() ) {
                    message = "El RFC introducido no es v" + UnicodeCommonWords.AACUTE_LOWER + "lido";
                }
            }
        }
        if(message != null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Filtro inv"+UnicodeCommonWords.AACUTE_LOWER+"lido",message));
        }
    }
}
