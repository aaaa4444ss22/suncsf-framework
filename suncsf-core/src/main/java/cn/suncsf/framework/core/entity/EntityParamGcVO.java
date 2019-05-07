package cn.suncsf.framework.core.entity;

import cn.suncsf.framework.core.entity.EntityParamVO;

/**
 * 通用实现载体
 */
public class EntityParamGcVO<T> extends EntityParamVO {

    /**
     * 通用载体
     */
    private T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
