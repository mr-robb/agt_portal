package mx.com.ebs.inter.exception;

/**
 * Created by robb on 27/04/2015.
 */
public class LoginFailureException extends Exception {

    private ExceptionTypeEnum exceptionType = ExceptionTypeEnum.LOGIN_FAILURE_TYPE;

    public LoginFailureException(String cause){
        super(cause);
    }

    public LoginFailureException(Throwable throwable){
        super(throwable);
    }

    public LoginFailureException(String cause, Throwable throwable){
        super(cause, throwable);
    }

    public ExceptionTypeEnum getExceptionType() {
        return exceptionType;
    }
}
