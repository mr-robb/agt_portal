package mx.com.ebs.inter.util;

import org.junit.Test;

/**
 * Created by robb on 02/09/2016.
 */
public class RandomGeneratorTest {

    @Test
    public void testGenerateAlphanumericValue(){
        for( int i =0; i<1000;i++ ) {
            String generatedValue = RandomGenerator.generateAlphanumericValue(12);
            System.out.println("generated value is:" + generatedValue);
        }
    }

}
