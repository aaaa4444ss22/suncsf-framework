package cn.suncsf.framework.core.entity;

import java.lang.reflect.ParameterizedType;

/**
 * 通用实现载体
 */
public class EntityParamGcVO<T extends EntityBase> extends EntityParamVO {

    /**
     * 通用载体
     */
    private T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
        this.setObjectEntity(entity);
    }

    @Override
    public Object getObjectEntity() {
        return this.entity;
    }

}
