package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.ChangePasswordBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.exception.ValidationException;
import mx.com.ebs.inter.service.RecAccesoService;
import mx.com.ebs.inter.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

/**
 * Created by robb on 11/06/2015.
 */
//@ManagedBean
//@Component
public class ChangePasswordBean extends AbstractBean implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(ChangePasswordBean.class);

    @Autowired
    private RecAccesoService recAccesoService;

    private ChangePasswordBo changePasswordBo;

    private String errorMessage;
    @PostConstruct
    public void init(){
        changePasswordBo = new ChangePasswordBo();
    }

    public void executeChange(){
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        if( userDataBo != null ){
            errorMessage = validateForm();
            if(  errorMessage == null ){
                try {
                    RecAcceso recAcceso = recAccesoService.findByEbsUserId(userDataBo.getEbsUserId());
                    if( recAcceso != null ){
                        String encodedPassword = Protection.encodePassword(userDataBo.getEbsUserId(),changePasswordBo.getCurrentPassword());
                        if( !encodedPassword.equals(recAcceso.getEBS_PW_ACTUAL()) ){
                            FacesMessageUtil.showFacesMessage("Error al cambiar password","El password actual no coincide con el de la BD","error");
                        }else {
                            recAcceso.setNINTENTOS(BigDecimal.ZERO);
                            recAcceso.setEBS_PW_ACTUAL(Protection.encodePassword(recAcceso.getEBS_USER_ID(), changePasswordBo.getNewPassword()));
                            if(recAccesoService.update(recAcceso)){
                                //FacesMessageUtil.showFacesMessage(UnicodeCommonWords.OPERACION +" exitosa","Cambio de contrase"+UnicodeCommonWords.NTILDE_LOWER+"a correcto",null);
                                FacesContext context = FacesContext.getCurrentInstance();
                                HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
                                HttpServletResponse response =(HttpServletResponse) context.getExternalContext().getResponse();
                                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("<div id=\"errorMessage\">").append("Inicie sesi").append(UnicodeCommonWords.OACUTE_LOWER).append("n con su nueva contrase").append(UnicodeCommonWords.NTILDE_LOWER).append("a</div>");
                                request.setAttribute("errorMessage", stringBuilder.toString());
                                try {
                                    dispatcher.forward(request,response);
                                } catch (ServletException e) {
                                    LOGGER.error("CHANGE PASSWORD BEAN", e);
                                } catch (IOException e) {
                                    LOGGER.error("CHANGE PASSWORD BEAN", e);
                                }
                            }else{
                                FacesMessageUtil.showFacesMessage("Error al cambiar password","No fue posible asignar nuevo password ","error");
                            }
                        }
                    }else{
                        throw new ValidationException("Usuario no encontrado");
                    }
                } catch (ValidationException e) {
                    LOGGER.error("Some error occurred while trying to select recAcceso", e);
                    FacesMessageUtil.showFacesMessage("Error al cambiar password","No fue posible encontrar al usuario "+ userDataBo.getEbsTipoUser(),"error");

                }catch(NoSuchAlgorithmException e){
                    LOGGER.error("Some error occurred while trying to select recAcceso", e);
                    FacesMessageUtil.showFacesMessage("Error al cambiar password","No fue posible asignar nuevo password ","error");
                }
           }else{
                FacesMessageUtil.showFacesMessage("No es posible cambiar la contrase"+UnicodeCommonWords.NTILDE_LOWER+"a",errorMessage,"error");
            }
        }else{
            FacesMessageUtil.showFacesMessage("Sesi"+ UnicodeCommonWords.OACUTE_LOWER +"n expirada",
                    "Inicie sesi"+UnicodeCommonWords.OACUTE_LOWER+"n  de nuevo para proceder a cambiar su contrase"+UnicodeCommonWords.NTILDE_LOWER+"a",
                    "error");
        }
        changePasswordBo = new ChangePasswordBo();
    }

    private String validateForm() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(changePasswordBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException while cleaning object changePasswordBo");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException while cleaning object changePasswordBo");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException while cleaning object changePasswordBo");
        }
        if( Validator.isEmptyString(changePasswordBo.getCurrentPassword()) ){
            stringBuilder.append("<br/>Password actual es requerido");
        }
        if( Validator.isEmptyString(changePasswordBo.getNewPassword()) ){
            stringBuilder.append("<br/>Nuevo password requerido");
        }
        if( Validator.isEmptyString(changePasswordBo.getConfirmationNewPassword()) ){
            stringBuilder.append("<br/>Confirmaci"+ UnicodeCommonWords.OACUTE_LOWER + "n  de nuevo password requerido");
        }
        if( changePasswordBo.getNewPassword() != null ) {
            if (!changePasswordBo.getNewPassword().equals(changePasswordBo.getConfirmationNewPassword())) {
                stringBuilder.append("<br/>El password debe coincidir con la confirmaci"+UnicodeCommonWords.OACUTE_LOWER+"n de password");
            }
        }
        if( stringBuilder.length() == 0 ){
            return null;
        }
        return stringBuilder.toString();
    }

    public ChangePasswordBo getChangePasswordBo() {
        return changePasswordBo;
    }

    public void setChangePasswordBo(ChangePasswordBo changePasswordBo) {
        this.changePasswordBo = changePasswordBo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RecAccesoService getRecAccesoService() {
        return recAccesoService;
    }

    public void setRecAccesoService(RecAccesoService recAccesoService) {
        this.recAccesoService = recAccesoService;
    }
}
