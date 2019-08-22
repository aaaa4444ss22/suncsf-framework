package cn.suncsf.framework.core.db.multidb;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/8/8 14:05
 * @create 2019/8/8 14:05
 * @description
 */
public enum DataSourceType {
    MasterDataSource(0,"masterDataSource"),
    ClusterDataSource(1,"clusterDataSource");
    private int index;
    private String value;

    private DataSourceType(int index,String value){
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
