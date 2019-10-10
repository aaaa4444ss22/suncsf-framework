package cn.suncsf.framework.core.business;

import cn.suncsf.framework.core.entity.EntityParamVO;
import cn.suncsf.framework.core.entity.IPageList;

import java.util.List;
import java.util.Map;

/**
 * @author sunchao
 * @version 1.0.0
 * @date 2019/10/10 16:45
 * @create 2019/10/10 16:45
 * @description
 */
public interface IMybatisPageListQuery<T> {


    /**
     * 获取分页信息
     * @param parm
     * @param <P>
     * @return
     */
    public <P extends EntityParamVO> IPageList<T> findWherePageList(P parm);
}
