package mx.com.ebs.inter.controller;

import mx.com.ebs.inter.exception.LoginFailureException;
import mx.com.ebs.inter.exception.ValidationException;
import mx.com.ebs.inter.service.LoginService;
import mx.com.ebs.inter.util.Protection;
import mx.com.ebs.inter.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by robb on 27/04/2015.
 */
//@Component
public class LoginController extends AbstractAutowirableServlet {

    @Autowired
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = ( String ) request.getAttribute("username");
        String passwd = ( String ) request.getAttribute("passwd");
        try {
            if( Validator.isEmptyString(user) || Validator.isEmptyString(passwd)){
                throw new ValidationException("Datos no validos");
            }
            user = user.trim();
            passwd = passwd.trim();
            loginService.doLogin(user, Protection.encodePassword(user,passwd) ,request);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.xhtml");
            dispatcher.forward(request,response);
        } catch (LoginFailureException e) {
            request.setAttribute("errorMessage", "<div id=\"errorMessage\">" + e.getMessage()+"</div>");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);
        }catch( ValidationException ve ){
            request.setAttribute("errorMessage", "<div id=\"errorMessage\">" + ve.getMessage()+"</div>");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);
        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("errorMessage", "<div id=\"errorMessage\">" + e.getMessage()+"</div>");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if( session == null || session.getAttribute("userData") == null ) {
            response.sendRedirect("login.jsp");
        }else{
            String userMainPage = ( String ) session.getAttribute("userMainPage");
            response.sendRedirect(userMainPage);
        }
    }
}