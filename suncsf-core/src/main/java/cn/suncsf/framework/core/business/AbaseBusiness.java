package cn.suncsf.framework.core.business;

import cn.suncsf.framework.core.entity.EntityResult;
import cn.suncsf.framework.core.entity.ResultObject;

public abstract class AbaseBusiness {


    /**
     * 返回结果
     * @param count
     * @param message
     * @return
     */
    public EntityResult getResult(int count, String message){
        return  new ResultObject().createResult(count,message);
    }
}
