package mx.com.ebs.inter.data.model;

import mx.com.ebs.inter.util.Variables;

/**
 * Created by robb on 06/08/2015.
 */
public abstract class PaginatedResult {

    private int pageIndex=1;

    public int getPageSize() {
        return pageIndex + pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    private int pageSize;
}
