package cn.suncsf.framework.core.entity;

import java.util.List;

/**
 * 分页实现类
 * @param <T>
 */
public class PageList<T> extends  IPageList<T> {

    /**
     * 构造数据
     * @param source
     * @param count
     * @param pageIndex
     * @param pageSize
     */
    public PageList(List<T> source, int count, int pageIndex, int pageSize) {
        super(source, count, pageIndex, pageSize);
    }
}
