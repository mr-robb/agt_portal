package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.util.Variables;
import org.primefaces.model.LazyDataModel;

/**
 * Created by robb on 23/06/2015.
 */
public abstract class AbstractBean<T> {

    public Integer getPaginationSize() {
        return paginationSize;
    }

    private Integer paginationSize = Variables.DEFAULT_SIZE_RESULT;
    public LazyDataModel<T> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<T> model) {
        this.model = model;
    }

    protected LazyDataModel<T> model;
    public int getDefaultInputMaxSize(){
        return Variables.MAX_LENGTH_INPUT_SEARCH;
    }
}
