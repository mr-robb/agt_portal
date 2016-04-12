package mx.com.ebs.inter.util;

import org.apache.commons.lang3.text.WordUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by robb on 06/06/2015.
 */
public class PropertiesCleaner {

    private static final String[] sqlStatements = {"SELECT ","UPDATE ","DELETE ","TRUNCATE ",";","DROP "};

    public static void cleanObjectUsingCapitalizedMetods(Object objectToClean) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        cleanObject(objectToClean, true);
    }

    public static void cleanObjectUsingUppercaseMethods( Object objectToClean ) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        cleanObject(objectToClean, false);
    }

    private static void cleanObject( Object objectToClean, boolean capitalizedMethod ) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if( objectToClean == null ){
            return;
        }
        Class clazz = objectToClean.getClass();
        Field[] clazzFields = clazz.getDeclaredFields();
        for( Field currentField : clazzFields ){
            String fieldName = currentField.getName();
            if( capitalizedMethod ){
                fieldName = WordUtils.capitalize(currentField.getName());
            }else{
                fieldName = currentField.getName().toUpperCase();
            }
            Method getMethod = clazz.getDeclaredMethod("get" + fieldName,null);
            Class returnTypeClass = getMethod.getReturnType();
            if( returnTypeClass.getSimpleName().equals("String") ){
                Object value = getMethod.invoke(objectToClean, null);
                if( value != null ){
                    String strValue = (String) value;
                    strValue = strValue.trim();
                    strValue = deleteSQLStatements(strValue);
                    Method setMethod = clazz.getMethod("set"+fieldName, String.class);
                    setMethod.invoke(objectToClean, strValue);
                }

            }
        }
    }

    private static String deleteSQLStatements(String str){
        if( str == null ){
            return null;
        }
        String strUpper = str.toUpperCase();
        for( String sql : sqlStatements ){
            if( strUpper.contains(sql) ){
                return null;
            }
        }
        return str;

    }
}