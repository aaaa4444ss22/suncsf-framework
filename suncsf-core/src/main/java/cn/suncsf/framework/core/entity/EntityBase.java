package cn.suncsf.framework.core.entity;

import cn.suncsf.framework.core.utils.JsonUtil;

public abstract class EntityBase  implements  IEntity{

    /**
     * 通用数据载体
     */
    private  Object objectEntity;


//    /**
//     * JSON序列化实现
//     * @return
//     */
//    @Override
//    public String toJson() {
//        return JsonUtil.toJson(this);
//    }


    public Object getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(Object objectEntity) {
        this.objectEntity = objectEntity;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
