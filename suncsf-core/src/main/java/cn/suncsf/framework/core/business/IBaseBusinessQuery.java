package cn.suncsf.framework.core.business;

import java.util.List;
import java.util.Map;

/**
 * 通用查询功能
 * @param <T> 泛型对象类型
 */
public interface IBaseBusinessQuery<T,K> {

    /**
     * 根据条件字段查询
     * @param key 主键
     * @return
     */
    public T findOne(K key);

    /**
     * 获取全部数据信息
     * @return
     */
    public List<T> findAll();
}
