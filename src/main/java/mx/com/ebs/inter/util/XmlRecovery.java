package mx.com.ebs.inter.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class XmlRecovery {

    public static byte[] getDocument(String documentId, String collection) throws IOException {
        Socket socketClient = null;
        InputStream input = null;
        ByteArrayOutputStream baos = null;
        try {
            socketClient = new Socket(
                    PropertiesLoader.getProperty("dbxmlDispatcher.host"),
                    Integer.parseInt(PropertiesLoader.getProperty("dbxmlDispatcher.port")));
            socketClient.setSoTimeout(Integer.parseInt(PropertiesLoader.getProperty("dbxmlDispatcher.timeout")));

            input = socketClient.getInputStream();
            socketClient.getOutputStream().write((documentId +"|"+collection + "\n").getBytes());
            socketClient.getOutputStream().flush();
            byte b[] = new byte[4096];
            baos = new ByteArrayOutputStream();
            int bytes = input.read( b );
            while ( bytes > 0 ) {
                baos.write( b , 0 , bytes);
                bytes = input.read( b );
            }

        } catch (IOException e) {
            throw e;
        }finally {
                try {
                    if( input != null ) {
                        input.close();
                    }
                    if( socketClient != null ){
                        socketClient.close();
                    }
                } catch (IOException e) {
                }
            }
        if( baos != null ){
            return baos.toByteArray();
        }
        return null;
    }

}