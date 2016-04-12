package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.util.SessionReader;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

import java.io.Serializable;

/**
 * Created by robb on 29/06/2015.
 */
//@ManagedBean
//@SessionScoped
//@Component
public class UserMenuBean implements Serializable {

    public boolean showOption(String menuOption){
        UserDataBo userData = SessionReader.getUserDataBo();
        if( userData == null ){
            return false;
        }
        return userData.getMenuOptionsList().contains(menuOption+".xhtml");
    }

}