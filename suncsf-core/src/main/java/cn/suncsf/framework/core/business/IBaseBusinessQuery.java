package cn.suncsf.framework.core.business;

import java.util.List;
import java.util.Map;

/**
 * 通用查询功能
 * @param <T> 泛型对象类型
 */
public interface IBaseBusinessQuery<T> {

    /**
     * 根据条件字段查询
     * @param map
     * @return
     */
    public T findOne(Map<String,Object> map) throws Exception;

    /**
     * 获取全部数据信息
     * @return
     */
    public List<T> findAll();
}
