package mx.com.ebs.inter.exception;

/**
 * Created by robb on 27/04/2015.
 */
public class ApplicationException extends Exception {

    private ExceptionTypeEnum exceptionType = ExceptionTypeEnum.APPLICATION_TYPE;

    public ApplicationException(String cause){
        super(cause);
    }

    public ApplicationException(Throwable throwable){
        super(throwable);
    }

    public ApplicationException(String cause , Throwable throwable){
        super(cause,throwable);
    }

    public ExceptionTypeEnum getExceptionType() {
        return exceptionType;
    }
}
