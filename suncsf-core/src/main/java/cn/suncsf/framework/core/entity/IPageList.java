package cn.suncsf.framework.core.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页载体
 */
public abstract class IPageList<T> {

    public IPageList(List<T> source,int count,int pageIndex,int pageSize){

        if(source != null){
            this.source = new ArrayList<>(source);
        }else{
            this.source = new ArrayList<>();
        }

        this.count = count;

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = (count + pageSize - 1)/(pageSize < 0?1:pageSize);

        next = (pageIndex + 1) <= this.pageCount;
        previous = (pageIndex - 1) > 0;

    }

    private int count;
    private int pageIndex;
    private int pageSize;
    private int pageCount;
    private Boolean next;
    private Boolean previous;
    private List<T> source;

    public int getCount() {
        return count;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Boolean getNext() {
        return next;
    }

    public Boolean getPrevious() {
        return previous;
    }

    public List<T> getSource() {
        return source;
    }
}
