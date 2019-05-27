package cn.suncsf.framework.core.business;

import java.util.List;
import java.util.Map;

/**
 * 基础DAO层
 */
public interface IBaseDao<T,K> {

    /**
     * 插入数据
     * @param t 实体
     * @return 成功数
     */
    public int save(T t);

    /**
     * 更新数据
     * @param t 实体
     * @return 成功数
     */
    public int update(T t);

    /**
     * 删除数据
     * @param k 主键或关键字
     * @return 成功数
     */
    public int delete(K k);

    /**
     * 条件查询
     * @param map
     * @return
     */
    public List<T> where(Map<String,Object> map);
}
