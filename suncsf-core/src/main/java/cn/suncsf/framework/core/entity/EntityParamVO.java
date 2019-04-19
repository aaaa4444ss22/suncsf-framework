package cn.suncsf.framework.core.entity;

import java.sql.Timestamp;

/**
 * 视图参数载体
 */
public class EntityParamVO extends  EntityBase {

    /**
     * 通用字段
     */
    private  String feild;

    /**
     * 时间载体
     */
    private Timestamp timestamp;

    /**
     * 页面索引
     */
    private  int page;

    /**
     * 页面容量
     */
    private  int pageSize;

    /**
     * 通用名称字段
     */
    private  String name;

    /**
     * 通用断言
     */
    private  boolean assertion;


    public String getFeild() {
        return feild;
    }

    public void setFeild(String feild) {
        this.feild = feild;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAssertion() {
        return assertion;
    }

    public void setAssertion(boolean assertion) {
        this.assertion = assertion;
    }
}
