package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.util.Variables;

/**
 * Created by robb on 23/06/2015.
 */
public abstract class AbstractBean {

    public int getDefaultInputMaxSize(){
        return Variables.MAX_LENGTH_INPUT_SEARCH;
    }
}
