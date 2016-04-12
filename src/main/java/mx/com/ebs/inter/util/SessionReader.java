package mx.com.ebs.inter.util;

import mx.com.ebs.inter.data.bo.UserDataBo;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Created by robb on 11/06/2015.
 */
public class SessionReader {

    public static UserDataBo getUserDataBo(){
        if( FacesContext.getCurrentInstance() == null ){
            return null;
        }
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if( session != null ){
            UserDataBo userDataBo = (UserDataBo)session.getAttribute("userData");
            return userDataBo;
        }
        return null;
    }
}
