package mx.com.ebs.inter.exception;

/**
 * Created by robb on 27/04/2015.
 */
public class UrlUnauthorizedException extends Exception {

    private ExceptionTypeEnum exceptionType = ExceptionTypeEnum.URL_UNAUTHORIZED_TYPE;

    public UrlUnauthorizedException(String cause){
        super(cause);
    }

    public UrlUnauthorizedException( Throwable throwable ){
        super( throwable );
    }

    public UrlUnauthorizedException(String cause , Throwable throwable){
        super(cause,throwable);
    }

    public ExceptionTypeEnum getExceptionType() {
        return exceptionType;
    }

}