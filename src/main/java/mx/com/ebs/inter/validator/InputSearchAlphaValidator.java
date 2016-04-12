package mx.com.ebs.inter.validator;

import mx.com.ebs.inter.util.UnicodeCommonWords;
import org.apache.commons.lang3.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by robb on 03/11/2015.
 */
@FacesValidator("validators.SpecialCharsValidator")
public class InputSearchAlphaValidator implements Validator{

    private static final char[] INVALID_CHARS={'#','$','%','&','(',')','*','\"'};

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String message = null;
        if( value != null && value instanceof String){
            String tmp = (String)value;
            if( tmp != null && tmp.trim().length() > 0 ){
                if( StringUtils.containsAny(tmp,InputSearchAlphaValidator.INVALID_CHARS) ) {
                    message = "No se permite el uso de caraceres especiales, limpie el formulario e intente de nuevo";
                }
            }
        }
        System.out.println("ValidationMessage:"+message);
        if(message != null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Filtro inv"+UnicodeCommonWords.AACUTE_LOWER+"lido",message));
        }

    }
}
