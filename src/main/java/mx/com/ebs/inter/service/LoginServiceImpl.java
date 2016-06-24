package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.LoginBo;
import mx.com.ebs.inter.data.bo.MenuBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.dao.agt.RecAccesoMapper;
import mx.com.ebs.inter.data.dao.agt.RecSessionUserMapper;
import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.data.model.RecAccesoExample;
import mx.com.ebs.inter.data.model.RecSessionUser;
import mx.com.ebs.inter.exception.LoginFailureException;
import mx.com.ebs.inter.exception.UserAlreadyLoggedException;
import mx.com.ebs.inter.exception.ValidationException;
import mx.com.ebs.inter.util.UnicodeCommonWords;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import mx.com.ebs.inter.util.file.FileMenuManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by robb on 27/04/2015.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);
    private static final String AGTE_PROFILE="AGENTE";
    private static final int SESSION_DEFAULT_TIMEOUT = 15;

    @Autowired
    private RecAccesoMapper recAccesoMapper;

    @Autowired
    private RecSessionUserMapper recSessionUserMapper;

    @Override
    @Transactional(value = Variables.TXM_PORTAL,readOnly = true)
    public void doLogin(String user, String passwd, HttpServletRequest request,HttpServletResponse response) throws LoginFailureException,UserAlreadyLoggedException{
        RecAcceso acceso = recAccesoMapper.findRecAccesoUserPass(new LoginBo(user,passwd));
        if( acceso != null ){
            if( (acceso.getNINTENTOS() != null && acceso.getNINTENTOS().intValue() >= 3) || (acceso.getSTATUS() != null && acceso.getSTATUS().intValue() !=1 )){
                throw new LoginFailureException("No es posible iniciar sesi&oacute;n, el usuario "+
                        user+" se encuentra bloqueado permanentemente");

                /*Calendar calendar = Calendar.getInstance();
                calendar.setTime(acceso.getULTIMOACCESO());
                // tomo la fecha de ultimo acceso y le sumo 15 minutos para compararla
                // con la fecha actual y valiadr si ya han pasado 15 minutos
                calendar.add(Calendar.MINUTE, 15);
                Date dueAccesDate = new Date();
                if ( dueAccesDate.before(calendar.getTime()) ){
                    int minutesToAcces = (int)(calendar.getTimeInMillis() - dueAccesDate.getTime()) / ( 60000 );
                    throw new LoginFailureException("No es posible iniciar sesi&oacute;n, el usuario "+
                            user+" se encuentra bloqueado. Intente en "+ ( minutesToAcces < 1 ? "1 minuto" : minutesToAcces+" minutos"));
                }*/
            }else {

                if(isValidUserToLogin(user)){
                if (!passwd.equals(acceso.getEBS_PW_ACTUAL())) {
                    StringBuilder errorMessage = new StringBuilder();
                    errorMessage.append("No es posible iniciar sesi&oacute;n, la contrase").
                            append(UnicodeCommonWords.NTILDE_LOWER).
                            append("a proporcionada no coincide.");
                    if (!"admingral".equals(acceso.getEBS_USER_ID())) {
                        if (acceso.getNINTENTOS() == null) {
                            acceso.setNINTENTOS(BigDecimal.ONE);
                        } else {
                            acceso.setNINTENTOS(new BigDecimal(acceso.getNINTENTOS().intValue() + 1));
                        }
                        acceso.setULTIMOACCESO(Calendar.getInstance().getTime());
                        if (acceso.getNINTENTOS() != null && acceso.getNINTENTOS().intValue() == 3) {
                            // bloquea al usuario
                            acceso.setSTATUS(BigDecimal.ZERO);
                            errorMessage.append(" Su usuario ha sido bloqueado, no podr")
                                    .append(UnicodeCommonWords.AACUTE_LOWER)
                                    .append(" entrar hasta que el administrador lo desbloquee");
                        } else {
                            errorMessage.append(acceso.getNINTENTOS().intValue())
                                    .append(" intentos fallidos, al tercer intento el usuario quedar")
                                    .append(UnicodeCommonWords.AACUTE_LOWER)
                                    .append(" bloqueado de forma permanente");
                        }
                        recAccesoMapper.updateIntentos(acceso);
                    }
                    throw new LoginFailureException(errorMessage.toString());
                } else {
                    acceso.setNINTENTOS(BigDecimal.ZERO);
                    acceso.setULTIMOACCESO(Calendar.getInstance().getTime());
                    recAccesoMapper.updateIntentos(acceso);
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(60 * SESSION_DEFAULT_TIMEOUT);
                    session.setAttribute("userData", map(acceso));
                    session.setAttribute("userMainPage", "index.xhtml");
                    Cookie cookie = new Cookie("JSESSIONID", session.getId());
                    cookie.setMaxAge(60 * SESSION_DEFAULT_TIMEOUT);
                    cookie.setSecure(true);
                    response.addCookie(cookie);
                }
            }else{
                    throw new UserAlreadyLoggedException();
                }
            }


        }else{
            throw new LoginFailureException("No es posible iniciar sesi&oacute;n, revise sus credenciales");
        }
    }

    @Override
    @Transactional(value = Variables.TXM_PORTAL,readOnly = true)
    public void doLoginAgte(String agte, HttpServletRequest request) throws LoginFailureException {
        RecAccesoExample example = new RecAccesoExample();
        RecAccesoExample.Criteria criteria = example.createCriteria();
        criteria.andNUMERO_CLIENTEEqualTo(agte);
        example.setOrderByClause("EBS_USER_ID desc");
        List<RecAcceso>  result = recAccesoMapper.selectByExample( example );

        if( result != null && !result.isEmpty() ){
            HttpSession session = request.getSession(true);
            RecAcceso acceso = result.get(0);
            UserDataBo userDataBo = map(acceso);
            userDataBo.setPerfil(AGTE_PROFILE);
            session.setAttribute("userData" ,userDataBo );
            session.setAttribute("userMainPage", "index.xhtml");
        }else{
            throw new LoginFailureException("No es posible iniciar sesi&oacute;n, el agente "+ agte+" no se encuentra en la BD");
        }
    }

    private UserDataBo map(final RecAcceso recAcceso) throws LoginFailureException {
        if( recAcceso == null ){
            return null;
        }
        UserDataBo userDataBo = new UserDataBo();
        userDataBo.setEbsTipoUser(recAcceso.getEBS_TIPO_USER());
        userDataBo.setEbsUserId(recAcceso.getEBS_USER_ID());
        userDataBo.setEbsUsername(recAcceso.getEBS_NOMBRE());
        userDataBo.setNumAgt(recAcceso.getNUMERO_CLIENTE());
        //NOTA en el campo EBS_TIPO_USER se está guardando el perfil
        userDataBo.setPerfil(recAcceso.getEBS_TIPO_USER());
        List<String> userOptions = new ArrayList<String>();
        try {
            MenuBo menu= FileMenuManager.read();
            if( menu == null){
                throw new LoginFailureException("No es posible leer el archivo de configuración de perfiles");
            }else if( menu.getPerfilesMapList() == null ){
                throw new LoginFailureException("No existen perfiles en el archivo de configuración");
            }else if(Validator.isEmptyString(recAcceso.getEBS_TIPO_USER())){
                throw new LoginFailureException("No puedes iniciar sesion porque tu usuario no tiene un perfil asignado");
            }
            List<String> options = menu.getPerfilesMapList().get(recAcceso.getEBS_TIPO_USER());

            if( options != null && !options.isEmpty()){
                for( String option : options ){
                    String value = menu.getResourcesMap().get(option);
                    if( value != null ){
                        userOptions.add(value);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Se encontro una excepcion IOException al leer el archivo de menu", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Se encontro una excepcion ClassNotFoundException al leer el archivo de menu", e);
        }
        userDataBo.setMenuOptionsList(userOptions);

        return userDataBo;
    }

    public boolean isValidUserToLogin(String ebsUserId) {
        RecSessionUser sessionUser = recSessionUserMapper.find(ebsUserId);
        if(sessionUser == null){
            sessionUser = new RecSessionUser();
            sessionUser.setEBS_USER_ID(ebsUserId);
            sessionUser.setSTATUS(1);
            sessionUser.setCREATED_TS(new Timestamp(System.currentTimeMillis()));
            return recSessionUserMapper.insert(sessionUser) > 0;
        }else if ( sessionUser.getSTATUS() != 1 || System.currentTimeMillis() - sessionUser.getCREATED_TS().getTime() > (LoginServiceImpl.SESSION_DEFAULT_TIMEOUT * 60000)){
                sessionUser.setCREATED_TS(new Timestamp(System.currentTimeMillis()));
                return recSessionUserMapper.updateLogin(sessionUser) > 0;
        }else{
            return false;
        }
    }

}
