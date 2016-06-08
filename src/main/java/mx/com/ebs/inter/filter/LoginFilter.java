package mx.com.ebs.inter.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by robb on 27/04/2015.
 */
public class LoginFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Iniciando filtro LoginFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Dentro del filtro LoginFilter, se ha solictado el recurso login.action");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if( session == null || session.getAttribute("userData") == null ){
            String user = request.getParameter("sec_user");
            String pass = request.getParameter("sec_pass");
            request.setAttribute("username",user);
            request.setAttribute("passwd",pass);
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            String userMainPage = ( String ) session.getAttribute("userMainPage");
            LOGGER.debug("Se redirecciona a la pagina " + userMainPage);
            response.sendRedirect(userMainPage);
        }
    }

    @Override
    public void destroy() {
    }
}
