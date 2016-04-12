package mx.com.ebs.inter.exception;

/**
 * Created by robb on 27/04/2015.
 */
public enum ExceptionTypeEnum {

    APPLICATION_TYPE(100,"ERROR DE APLICACION"),
    URL_UNAUTHORIZED_TYPE(200,"URL NO VALIDA"),
    VALIDACION_TYPE(300,"LOS DATOS NO PASAN LA VALIDACION"),
    LOGIN_FAILURE_TYPE(400, "DATOS DE ACCESO INCORRECTOS");

    private int code;
    private String description;

    private ExceptionTypeEnum (int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ExceptionTypeEnum getByCode(int code){
        for( ExceptionTypeEnum value : ExceptionTypeEnum.values() ){
            if( value.getCode() == code ){
                return value;
            }
        }
        return null;
    }

}
