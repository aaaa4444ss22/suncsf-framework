package cn.suncsf.framework.core.business;

import cn.suncsf.framework.core.entity.EntityResult;
import cn.suncsf.framework.core.entity.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbaseBusiness {

    private static Logger logger = LoggerFactory.getLogger(AbaseBusiness.class);

    /**
     * 返回结果
     * @param count
     * @param message
     * @return
     */
    public EntityResult getResult(int count, String message){
        logger.info("getResult(int,String)；成功数：{}，提示消息：{}",count,message);
        return  new ResultObject().createResult(count,message);
    }
    /**
     * 返回结果
     * @param count
     * @return
     */
    public EntityResult getResult(int count){
        logger.info("getResult(int)；成功数：{}，提示消息：null",count);
        return  getResult(count,null);
    }
}
