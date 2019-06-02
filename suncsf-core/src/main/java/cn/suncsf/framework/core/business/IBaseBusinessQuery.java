package cn.suncsf.framework.core.business;

import java.util.List;
import java.util.Map;

public interface IBaseBusinessQuery<T> {

    /**
     * 根据条件字段查询
     * @param map
     * @return
     */
    public T findOne(Map<String,Object> map) throws Exception;

    /**
     * 条件查询
     * @param map
     * @return
     */
    public List<T> where(Map<String,Object> map) throws Exception;
}
