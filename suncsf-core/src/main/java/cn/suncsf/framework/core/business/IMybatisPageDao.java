package cn.suncsf.framework.core.business;

import java.util.List;
import java.util.Map;

/**
 * 分页组合
 */
public interface IMybatisPageDao<T> {

    /**
     * 获取条件查询总数，与条件查询不同的是Map必须包含  #{pageIndex},#{pageSize}
     * @param map 条件
     * @return 总数
     */
    public int findWherePageListCount(Map<String,Object> map);

    /**
     * 获取条件查询集合，与条件查询不同的是Map必须包含  #{pageIndex},#{pageSize}
     * @param map 条件
     * @return 集合
     */
    public List<T> findWherePageList(Map<String,Object> map);

}
