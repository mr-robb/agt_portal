package mx.com.ebs.inter.util;

import java.util.Random;

/**
 * Created by robb on 12/06/2015.
 */
public class RandomGenerator {

    private static final char[] LETTERS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final char[] NUMBERS = {'0','1','2','3','4','5','6','7','8','9'};
    private static final Random randomLetters = new Random();
    private static final Random randomNumbers = new Random();

    public static String generateAlphanumericValue(int size){
        StringBuilder builder = new StringBuilder();
        for( int index =0; index < size; index++ ){
            if(randomLetters.nextInt(5) % 2 == 0){
                int i = randomLetters.nextInt(LETTERS.length-1);
                builder.append(LETTERS[ i < 0 ? 0 : i]);
            }else{
                int i = randomNumbers.nextInt(NUMBERS.length-1);
                builder.append(NUMBERS[ i < 0 ? 0 : i ]);
            }
        }
        return builder.toString();
    }

}
