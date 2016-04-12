package mx.com.ebs.inter.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by robb on 11/06/2015.
 */
public class Protection {

    public static String encodePassword( final String userId, final String password ) throws NoSuchAlgorithmException {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte [] digest = makeDigest(userId, password);
        return base64Encoder.encode(digest);
    }

    public static byte[] makeDigest(String str1, String str2) throws NoSuchAlgorithmException {
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
        localMessageDigest.update(str1.getBytes());
        localMessageDigest.update(str2.getBytes());
        return localMessageDigest.digest();
    }
}
