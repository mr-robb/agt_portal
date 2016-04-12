package mx.com.ebs.inter.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by robb on 25/05/2015.
 */
public class PropertiesLoader {

    private static ResourceBundle rb = ResourceBundle.getBundle(Variables.PROPERTIES_FILENAME_CONFIG , new Locale("sp", "MX"));

    public static String getProperty(final String name){
        return rb.getString(name);
    }
}
