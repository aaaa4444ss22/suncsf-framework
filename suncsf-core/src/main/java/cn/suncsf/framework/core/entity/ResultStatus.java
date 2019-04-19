package cn.suncsf.framework.core.entity;

/**
 * 返回载体状态
 */
public enum ResultStatus {

    /**
     * 成功执行全部
     */
    Successfully("成功",200),

    /**
     * 服务器成功处理了请求，但没有返回任何内容，一般用户返回执行结果失败，如插入失败
     */
    Success("服务器成功处理了请求",204),

    /**
     *失败
     */
    Error("失败",500);

    private  String name;
    private  int index;

    private   ResultStatus(String name,int index){
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return String.valueOf(this.index) + "：" + this.name;
    }

    /**
     * 返回状态码
     * @return
     */
    public  int toStatus(){
        return  this.index;
    }

    /**
     * 返回描述
     * @return
     */
    public  String toDescription(){
        return  this.name;
    }
}
