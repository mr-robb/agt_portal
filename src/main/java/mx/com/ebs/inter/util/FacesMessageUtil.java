package mx.com.ebs.inter.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by robb on 09/06/2015.
 */
public class FacesMessageUtil {

    /**
     *
     * @param message Is the message to be displayed
     * @param title Is the message title
     * @param type If is error, value must be error. Otherwise info will be used
     */
    public static void showFacesMessage( String message, String title, String type ){
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage( "error".equals(type) ? FacesMessage.SEVERITY_ERROR : FacesMessage.SEVERITY_INFO , message, title));
    }
}
