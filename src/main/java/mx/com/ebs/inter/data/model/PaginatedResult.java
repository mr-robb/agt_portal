package mx.com.ebs.inter.data.model;

import mx.com.ebs.inter.util.Variables;

/**
 * Created by robb on 06/08/2015.
 */
public abstract class PaginatedResult {

    protected int resultLimitRows = Variables.DEFAULT_SIZE_RESULT;

    public int getResultLimitRows() {
        return resultLimitRows;
    }
}
