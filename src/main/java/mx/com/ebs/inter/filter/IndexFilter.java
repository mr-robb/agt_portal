package mx.com.ebs.inter.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by robb on 01/12/2015.
 */
public class IndexFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(IndexFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LOGGER.info("Into IndexFilter Filter, requested resource to: "+ request.getRequestURI());

        HttpSession session = request.getSession(false);
        if( session == null || session.getAttribute("userData") == null ){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect("index.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
