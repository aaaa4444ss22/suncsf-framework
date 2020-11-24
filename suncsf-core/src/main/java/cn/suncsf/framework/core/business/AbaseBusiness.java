package cn.suncsf.framework.core.business;

import cn.suncsf.framework.core.entity.EntityResult;
import cn.suncsf.framework.core.entity.IPageList;
import cn.suncsf.framework.core.entity.PageList;
import cn.suncsf.framework.core.entity.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbaseBusiness {


    private static Logger logger = LoggerFactory.getLogger(AbaseBusiness.class);

    /**
     * 返回结果
     *
     * @param count
     * @param message
     * @return
     */
    public EntityResult getResult(int count, String message, Object objectEntity) {
        logger.debug("getResult(int,String);成功数：{}，提示消息：{}", count, message);
        return new ResultObject().createResult(count, message, objectEntity);
    }

    /**
     * 返回结果
     *
     * @param count
     * @param message
     * @return
     */
    public EntityResult getResult(int count, String message) {
        logger.debug("getResult(int,String);成功数：{}，提示消息：{}", count, message);
        return new ResultObject().createResult(count, message);
    }

    /**
     * 返回携带实体
     *
     * @param count
     * @param objectEntity
     * @return
     */
    public EntityResult getResultObjectEntity(int count, Object objectEntity) {
        logger.debug("getResult(int,Object);成功数：{}，提示消息：{}", count, objectEntity);
        return new ResultObject().createResult(count, null, objectEntity);
    }

    /**
     * 返回结果
     *
     * @param count
     * @return
     */
    public EntityResult getResult(int count) {
        logger.debug("getResult(int);成功数：{}，提示消息：null", count);
        return getResult(count, null);
    }

    /**
     * 返回并初始化
     *
     * @param result
     * @return
     */
    public EntityResult getResult(EntityResult result) {
        logger.debug("getResult(EntityResult);EntityResult：{}", result);
        if (result == null) {
            return getResult(0);
        }
        return result;
    }

    /**
     * 返回并初始化
     * @param flag
     * @param message
     * @param objectEntity
     * @return
     */
    public EntityResult getResult(boolean flag, String message, Object objectEntity) {
        return getResult(flag ? 1 : 0, message, objectEntity);
    }
    /**
     * 返回并初始化
     * @param flag
     * @param message
     * @return
     */
    public EntityResult getResult(boolean flag, String message) {
        return getResult(flag ? 1 : 0, message);
    }
    /**
     * 返回并初始化
     * @param flag
     * @return
     */
    public EntityResult getResult(boolean flag) {
        return getResult(flag ? 1 : 0);
    }

    /**
     * 返回携带实体
     * @param flag
     * @param objectEntity
     * @return
     */
    public EntityResult getResultObjectEntity(boolean flag, Object objectEntity) {
        return getResultObjectEntity(flag?1:0,objectEntity);
    }

}
