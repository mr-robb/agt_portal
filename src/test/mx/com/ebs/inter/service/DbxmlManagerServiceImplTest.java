package mx.com.ebs.inter.service;


import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class DbxmlManagerServiceImplTest {

    @Ignore
    @Test
    public void testGetXMLDocument() throws Exception {
        DbxmlManagerServiceImpl service = new DbxmlManagerServiceImpl();
        byte[] content = service.getXMLDocument("A.21786", "/des");
        System.out.println(new String(content));
        Assert.assertNotNull("Object not found", content);
    }
}