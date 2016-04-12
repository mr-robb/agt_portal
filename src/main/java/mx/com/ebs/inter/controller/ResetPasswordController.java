package mx.com.ebs.inter.controller;

import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.exception.ValidationException;
import mx.com.ebs.inter.service.LoginService;
import mx.com.ebs.inter.service.RecAccesoService;
import mx.com.ebs.inter.util.Protection;
import mx.com.ebs.inter.util.RandomGenerator;
import mx.com.ebs.inter.util.UnicodeCommonWords;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.mail.EmailSender;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import static mx.com.ebs.inter.util.UnicodeCommonWords.*;
import static mx.com.ebs.inter.util.mail.EmailProperties.EMAIL_PASSWORD_CONTENT;

/**
 * Created by robb on 02/10/2015.
 */
public class ResetPasswordController extends AbstractAutowirableServlet {

    private static final Logger LOGGER = Logger.getLogger(ResetPasswordController.class);
    private static final int DEFAULT_PASSWORD_SIZE=8;

    @Autowired
    private RecAccesoService recAccesoService;

    public String sendPassword(String userId) throws ValidationException{
        if( Validator.isEmptyString(userId) ){
            throw new ValidationException("El nombre de usuario proporcionado no es v"+AACUTE_LOWER+"lido");
        }
        RecAcceso recAcceso = recAccesoService.findByEbsUserId(userId);
        if( recAcceso == null){
            throw new ValidationException("El nombre de usuario proporcionado no existe");
        }else{
            if( Validator.isEmptyString(recAcceso.getEBS_EMAIL()) ){
                throw new ValidationException("No existe una cuenta de correo electr"+OACUTE_LOWER+"nico para el usuario "+ userId);
            }else {
                recAcceso.setNINTENTOS(BigDecimal.ZERO);
                String generatedPassword = RandomGenerator.generateAlphanumericValue(DEFAULT_PASSWORD_SIZE);
                try {
                    recAcceso.setEBS_PW_ACTUAL(Protection.encodePassword(recAcceso.getEBS_USER_ID(), generatedPassword));
                } catch (NoSuchAlgorithmException e) {
                    LOGGER.error("Some error at generatin new password for user:" + userId, e);
                }
                if (recAccesoService.update(recAcceso)) {
                    String content = EmailSender.properties.getProperty(EMAIL_PASSWORD_CONTENT);
                    content = content.replaceAll("~0", userId);
                    content = content.replaceAll("~1", generatedPassword);
                    try {
                        EmailSender.sendSimple(content, recAcceso.getEBS_EMAIL());
                        return recAcceso.getEBS_EMAIL();
                    } catch (EmailException e) {
                        LOGGER.error("Some error occurred while sending email password to "+ userId, e);
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = null;
        try{
            String email = sendPassword(req.getParameter("username"));
            if( email != null ){
                message = "<div id=\"errorMessage\">Su contrase"+NTILDE_LOWER+"a se ha enviado a "+ email +"</div>";
            }else{
                message = "<div id=\"errorMessage\">No fue posibe enviar su contrase"+NTILDE_LOWER+"a, contactar al administrador</div>";
            }
        }catch (ValidationException ve){
            message = "<div id=\"errorMessage\">" + ve.getMessage()+"</div>";
        }
        req.setAttribute("errorMessage", message);
        RequestDispatcher dispatcher = req.getRequestDispatcher("resetPassword.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("resetPassword.jsp");
        dispatcher.forward(req,resp);
    }

    public RecAccesoService getRecAccesoService() {
        return recAccesoService;
    }

    public void setRecAccesoService(RecAccesoService recAccesoService) {
        this.recAccesoService = recAccesoService;
    }


}