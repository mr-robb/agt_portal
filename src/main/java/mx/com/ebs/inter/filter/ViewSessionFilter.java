package mx.com.ebs.inter.filter;

import mx.com.ebs.inter.util.UnicodeCommonWords;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by robb on 08/06/2016.
 */
public class ViewSessionFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(ViewSessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //LOGGER.info("INTO viewSessionFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if( session == null || session.getAttribute("userData") == null ){
            request.setAttribute("errorMessage", "Tu sesi" + UnicodeCommonWords.OACUTE_LOWER + "n ha expirado por inactividad, favor de ingresar de nuevo");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
