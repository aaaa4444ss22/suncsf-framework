package cn.suncsf.framework.core.entity;


import cn.suncsf.framework.core.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public interface IEntity extends Serializable {

    /**
     * 序列化JSON数据
     * @return
     */
    public  default  String toJson(){
        return JsonUtil.toJson(this);
    }



}
