package cn.suncsf.framework.core.entity;


/**
 * 返回载体
 */
public abstract class EntityResult extends  EntityBase {

    /**
     * 状态码
     */
    private  int status;

    /**
     * 消息
     */
    private  String message;

    /**
     * 断言
     */
    private Boolean assertion;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAssertion() {
        return assertion;
    }

    public void setAssertion(Boolean assertion) {
        this.assertion = assertion;
    }

    /**
     * 判断状态
     * @param status
     * @return
     */
    public  static  boolean discriminantState(ResultStatus status){
        boolean flag = false;
        switch (status){
            case Successfully:
                flag = true;
                break;
        }
        return  flag;
    }


    /**
     * count 小于等于 0 ：false，count 大于 0 ：true
     * @param count
     * @param message
     * @return
     */
    public abstract EntityResult createResult(int count,String message);

    /**
     * count 小于等于 0 ：false，count 大于 0 ：true
     * @param count
     * @param message
     * @param objectEntity
     * @return
     */
    public abstract EntityResult createResult(int count,String message,Object objectEntity);
    /**
     *
     * @param assertion
     * @param message
     * @return
     */
    public  abstract  EntityResult createResult(boolean assertion,String message);

    /**
     *
     * @param assertion
     * @param message
     * @param objectEntity
     * @return
     */
    public  abstract  EntityResult createResult(boolean assertion,String message,Object objectEntity);
}
