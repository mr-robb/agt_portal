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
@FacesValidator("validators.ImporteValidator")
public class InputSearcAmountValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        System.out.println("Dentro del metodo:InputSearcAmountValidator.validate:"+value.getClass().getName());
        String message = null;
        if( value !=null && value instanceof String){
            String tmp = (String)value;
            if( tmp != null && tmp.trim().length() > 0 ){
                try{
                    Double.parseDouble(tmp);
                }catch (NumberFormatException e){
                    message="El importe no es v"+UnicodeCommonWords.AACUTE_LOWER+"alido, coloque otro valor";
                }
            }
        }
        if(message != null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Filtro inv"+UnicodeCommonWords.AACUTE_LOWER+"lido",message));
        }
    }
}
