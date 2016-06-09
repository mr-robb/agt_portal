package mx.com.ebs.inter.listener; /**
 * Created by rfonseca on 6/9/16.
 */

import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.dao.agt.RecSessionUserMapper;
import mx.com.ebs.inter.data.model.RecSessionUser;
import mx.com.ebs.inter.util.Variables;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Timestamp;

@Component
public class SessionListener implements HttpSessionListener{

    private static final Logger LOGGER = Logger.getLogger(RecSessionUserMapper.class);

    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.debug("SessionListener::sessionCreated");
    }

    @Transactional(value = Variables.TXM_PORTAL,readOnly = false)
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext =   se.getSession().getServletContext();
        WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        RecSessionUserMapper userMapper = appContext.getBean(RecSessionUserMapper.class);
        UserDataBo dataBo = (UserDataBo) se.getSession().getAttribute("userData");
        LOGGER.info("L I S T E N E R  SessionListener.sessionDestroyed() method called for user "+ dataBo.getEbsUserId());
        RecSessionUser sessionUser = new RecSessionUser();
        sessionUser.setEBS_USER_ID(dataBo.getEbsUserId());
        sessionUser.setDESTROYED_TS(new Timestamp(System.currentTimeMillis()));
        userMapper.updateLogout(sessionUser);
    }

}
