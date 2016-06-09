package mx.com.ebs.inter.exception;

/**
 * Created by rfonseca on 6/9/16.
 */
public class UserAlreadyLoggedException extends Exception {

    public UserAlreadyLoggedException(){
        super("User already logged in");
    }
}
