package mx.com.ebs.inter.listener; /**
 * Created by rfonseca on 6/9/16.
 */

import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.dao.agt.RecSessionUserMapper;
import mx.com.ebs.inter.data.model.RecSessionUser;
import mx.com.ebs.inter.util.Variables;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Timestamp;

@Service
public class SessionListener implements HttpSessionListener{

    @Autowired
    private RecSessionUserMapper userMapper;

    private static final Logger LOGGER = Logger.getLogger(RecSessionUserMapper.class);

    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.debug("SessionListener::sessionCreated");
    }

    @Transactional(value = Variables.TXM_PORTAL,readOnly = false)
    public void sessionDestroyed(HttpSessionEvent se) {
        UserDataBo dataBo = (UserDataBo) se.getSession().getAttribute("userData");
        LOGGER.info("L I S T E N E R  SessionListener.sessionDestroyed() method called for user "+ dataBo.getEbsUserId());
        RecSessionUser sessionUser = new RecSessionUser();
        sessionUser.setEBS_USER_ID(dataBo.getEbsUserId());
        sessionUser.setDESTROYED_TS(new Timestamp(System.currentTimeMillis()));
        userMapper.updateLogout(sessionUser);
    }

}
