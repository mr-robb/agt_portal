package mx.com.ebs.inter.service;

import mx.com.ebs.inter.exception.LoginFailureException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by robb on 27/04/2015.
 */
public interface LoginService {

    void doLogin(String user, String passwd,HttpServletRequest request,HttpServletResponse response) throws LoginFailureException;
    void doLoginAgte(String agte,HttpServletRequest request ) throws LoginFailureException;

}