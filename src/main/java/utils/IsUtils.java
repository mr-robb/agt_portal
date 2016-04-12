package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IsUtils {
	/** Read bytes from inputStream and writes to OutputStream, later converts * OutputStream to byte array in Java. */ 
	public static byte[] toByteArrayUsingJava(InputStream is) throws IOException{ 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		int reads = is.read(); 
		while(reads != -1){ 
			baos.write(reads); 
			reads = is.read(); 
		} 
	return baos.toByteArray(); 
	}

}
