package cn.suncsf.framework.core.entity;

public class ResultGeneric<T> extends  EntityResult {

    public  ResultGeneric(){}


    /**
     * 构造状态
     * @param status
     */
    public  ResultGeneric(ResultStatus status){
        this.setAssertion(discriminantState(status));
        this.setStatus(status.toStatus());
        this.setMessage(status.toDescription());
    }

    /**
     * 构造状态及载体数据
     * @param status
     * @param message
     */
    public  ResultGeneric(ResultStatus status,String message){
        this(status);
        this.setMessage(message);
    }
    /**
     * 构造状态及载体数据
     * @param status
     * @param message
     * @param dataEntity
     */
    public  ResultGeneric(ResultStatus status,String message,T dataEntity){
        this(status,message);
        this.setObjectEntity(dataEntity);
        this.setDataEntity(dataEntity);
    }

    /**
     * 泛型数据载体
     */
    private  T dataEntity;

    public T getDataEntity() {
        return dataEntity;
    }

    public void setDataEntity(T dataEntity) {
        this.dataEntity = dataEntity;
    }

    @Override
    public EntityResult createResult(int count, String message) {
        return createResult(count,message);
    }

    @Deprecated
    @Override
    public EntityResult createResult(int count, String message, Object objectEntity) {
        return null;
    }

    @Override
    public EntityResult createResult(boolean assertion, String message) {
        ResultStatus status = ResultStatus.Error;
        if(assertion){
            status = ResultStatus.Successfully;
        }
        return new ResultGeneric(status,message);
    }

    @Deprecated
    @Override
    public EntityResult createResult(boolean assertion, String message, Object objectEntity) {
        return null;
    }
}
