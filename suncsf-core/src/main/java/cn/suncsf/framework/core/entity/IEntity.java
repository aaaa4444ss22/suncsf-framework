package cn.suncsf.framework.core.entity;


import cn.suncsf.framework.core.utils.JsonUtil;

import java.io.Serializable;

public interface IEntity extends Serializable {

    /**
     * 序列化JSON数据
     * @return
     */
    public  default  String toJson(){
        return JsonUtil.toJson(this);
    }


    /**
     * 当前对象等于null
     * @return
     */
    public  default boolean isNull(){
        return  this == null;
    }

    /**
     *  当前对象不等于null
     * @return
     */
    public  default boolean isNotNull(){
        return  this != null;
    }
}
