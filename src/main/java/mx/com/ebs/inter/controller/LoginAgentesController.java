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
 * Created by robb on 01/12/2015.
 */
public class LoginAgentesController extends AbstractAutowirableServlet {

    @Autowired
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String agte = request.getParameter("agte");
        try {
            if( Validator.isEmptyString(agte)){
                throw new ValidationException("Datos no validos");
            }
            loginService.doLoginAgte(agte, request);
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
        }
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
