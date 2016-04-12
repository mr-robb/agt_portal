package mx.com.ebs.inter.util;

import mx.com.ebs.inter.data.model.RecAcceso;
import org.junit.Test;

public class PropertiesCleanerTest{

    @Test
    public void testCleanObject() throws Exception {
        RecAcceso recAcceso = new RecAcceso();
        recAcceso.setEBS_USER_ID("     robertin        ");
        recAcceso.setEBS_EMAIL(" nsnsnsnsnsns                                    ");
        //PropertiesCleaner.cleanObject(recAcceso);
        System.out.println("EBS_USER_ID:" + recAcceso.getEBS_USER_ID());
        System.out.println("EBS_EMAIL:" + recAcceso.getEBS_EMAIL());
    }
}