package mx.com.ebs.inter.filter;

import mx.com.ebs.inter.data.bo.MenuBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.util.UnicodeCommonWords;
import mx.com.ebs.inter.util.file.FileMenuManager;
import org.apache.log4j.Logger;
import org.apache.xpath.XPathContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by robb on 14/06/2015.
 */
public class SecurityFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Filter SecurityFilter has been initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String resourceUri = request.getRequestURI();
        String resourceName = resourceUri.substring(resourceUri.lastIndexOf("/")+1);
        MenuBo menuBo = null;
        try {
            menuBo = FileMenuManager.read();
        } catch (ClassNotFoundException e) {
            LOGGER.error("Some error occurred while reading menu file");
        }
        if( !menuBo.getResourcesMap().containsValue(resourceName) ){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            if (session != null && session.getAttribute("userData") != null) {
                UserDataBo userDataBo = (UserDataBo) session.getAttribute("userData");
                if (menuBo.getPerfilesMapList().containsKey(userDataBo.getPerfil())) {
                    LOGGER.debug("Requested resource:" + resourceName);
                    String resourceRequestedKey = "";
                    Iterator<String> keySetIterator = menuBo.getResourcesMap().keySet().iterator();
                    while( keySetIterator.hasNext() ){
                        String key = keySetIterator.next();
                        if( menuBo.getResourcesMap().get(key).equals(resourceName) ){
                            resourceRequestedKey = key;
                            break;
                        }
                    }
                    if (menuBo.getPerfilesMapList().get(userDataBo.getPerfil()).contains(resourceRequestedKey)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("404.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    throw new ServletException("Tu perfil de usuario no existe");
                }


            } else {
                request.setAttribute("errorMessage", "Debes iniciar sesi" + UnicodeCommonWords.OACUTE_LOWER + "n para poder acceder al recurso");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}