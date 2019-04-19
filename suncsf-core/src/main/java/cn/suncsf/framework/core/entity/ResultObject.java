package cn.suncsf.framework.core.entity;

import org.apache.commons.lang3.StringUtils;

public class ResultObject extends  EntityResult {

    public  ResultObject(){}

    /**
     * 构造状态
     * @param status
     */
    public  ResultObject(ResultStatus status){
        this.setAssertion(discriminantState(status));
        this.setStatus(status.toStatus());
        this.setMessage(status.toDescription());
    }

    /**
     * 构造状态及载体数据
     * @param status
     * @param message
     */
    public  ResultObject(ResultStatus status,String message){
        this(status);
        this.setMessage(message);
    }
    /**
     * 构造状态及载体数据
     * @param status
     * @param message
     * @param objectEninty
     */
    public  ResultObject(ResultStatus status,String message,Object objectEninty){
        this(status,message);
        this.setObjectEntity(objectEninty);
    }

    @Override
    public EntityResult createResult(int count,String message) {
        return  createResult(count > 0,message);
    }

    @Override
    public EntityResult createResult(boolean assertion,String message) {
        ResultStatus status = ResultStatus.Error;
        if(assertion){
            status = ResultStatus.Successfully;
        }
        EntityResult result = new ResultObject(status);
        if(StringUtils.isNotEmpty(message)){
            result.setMessage(message);
        }
        return  result;
    }
}
