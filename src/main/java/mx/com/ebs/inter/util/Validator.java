package mx.com.ebs.inter.util;

/**
 * Created by robb on 21/05/2015.
 */
public class Validator {

    public static boolean isEmptyString( final String str ){
        return (str == null || str.isEmpty()) ? true : false;
    }
}
