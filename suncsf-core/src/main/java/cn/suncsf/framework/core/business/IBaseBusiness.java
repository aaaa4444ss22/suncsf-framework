package cn.suncsf.framework.core.business;

import cn.suncsf.framework.core.entity.EntityResult;

import java.util.List;
import java.util.Map;

/**
 * 基础DAO层
 */
public interface IBaseBusiness<T,K> {

    /**
     * 插入数据
     * @param t 实体
     * @return 成功数
     */
    public EntityResult save(T t) throws Exception;

    /**
     * 更新数据
     * @param t 实体
     * @return 成功数
     */
    public EntityResult update(T t) throws Exception;

    /**
     * 删除数据
     * @param k 主键或关键字
     * @return 成功数
     */
    public EntityResult delete(K k) throws Exception;

}
