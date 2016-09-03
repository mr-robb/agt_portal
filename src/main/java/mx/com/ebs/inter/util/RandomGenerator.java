package mx.com.ebs.inter.util;

import java.security.SecureRandom;

/**
 * Created by robb on 12/06/2015.
 */
public class RandomGenerator {

    private static final char[] UPPER_CHARS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final char[] LOWER_CHARS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static final char[] NUMBERS = {'0','1','2','3','4','5','6','7','8','9'};
    private static final char[] SPECIAL_CHARS = {'!','@','#','$','*','+','[',']','?'};
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateAlphanumericValue(int size){
        StringBuilder builder = new StringBuilder();
        int index =0;
        main:
        while(  index < size){
            int option = secureRandom.nextInt(100) % 7;
            char current = ' ';
            switch( option ){
                case 0:
                    current = UPPER_CHARS[secureRandom.nextInt(UPPER_CHARS.length-1)];
                    if( isCurrentEqualsLastChar(builder,current) ){
                        continue main;
                    }
                    builder.append(current);
                    break;
                case 1:
                    current = LOWER_CHARS[secureRandom.nextInt(LOWER_CHARS.length-1)];
                    if( isCurrentEqualsLastChar(builder,current) ){
                        continue main;
                    }
                    builder.append(current);
                    break;
                case 2:
                    current = NUMBERS[secureRandom.nextInt(NUMBERS.length-1)];
                    if( isCurrentEqualsLastChar(builder,current) ){
                        continue main;
                    }
                    builder.append(current);
                    break;
                case 3:
                    current = SPECIAL_CHARS[secureRandom.nextInt(SPECIAL_CHARS.length-1)];
                    if( isCurrentEqualsLastChar(builder,current) ){
                        continue main;
                    }
                    builder.append(current);
                    break;
                default:
                    continue main;
            }
            index++;
        }
        return builder.toString();
    }

    private static boolean isCurrentEqualsLastChar(final StringBuilder st, char current){
        if( st.length() == 0 ){
            return false;
        }
        return st.charAt(st.length()-1) == current;
    }

}
