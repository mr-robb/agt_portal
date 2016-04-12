package mx.com.ebs.inter.util;

import org.junit.Test;

public class ProtectionTest {

    @Test
    public void testEncodePassword() throws Exception {
        System.out.println( Protection.encodePassword("robertin","e4bhse12") );
    }
}