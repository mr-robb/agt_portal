package mx.com.ebs.inter.controller;

import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.dao.agt.RecSessionUserMapper;
import mx.com.ebs.inter.data.model.RecSessionUser;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by robb on 29/04/2015.
 */
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if( session != null ) {
            WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

            UserDataBo dataBo = (UserDataBo) session.getAttribute("userData");
            if( dataBo != null ) {
                RecSessionUser sessionUser = new RecSessionUser();
                sessionUser.setEBS_USER_ID(dataBo.getEbsUserId());
                sessionUser.setDESTROYED_TS(new Timestamp(System.currentTimeMillis()));
                RecSessionUserMapper userMapper = appContext.getBean(RecSessionUserMapper.class);
                userMapper.updateLogout(sessionUser);
            }
            session.removeAttribute("userData");
            session.invalidate();

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request,response);
    }
}