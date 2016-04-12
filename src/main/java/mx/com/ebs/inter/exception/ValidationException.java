package mx.com.ebs.inter.exception;

/**
 * Created by robb on 27/04/2015.
 */
public class ValidationException extends Exception {

    private ExceptionTypeEnum exceptionType = ExceptionTypeEnum.VALIDACION_TYPE;

    public ValidationException(String cause){
        super(cause);
    }

    public ValidationException(Throwable throwable){
        super(throwable);
    }

    public ValidationException(String cause , Throwable throwable){
        super(cause, throwable);
    }

    public ExceptionTypeEnum getExceptionType() {
        return exceptionType;
    }

}
