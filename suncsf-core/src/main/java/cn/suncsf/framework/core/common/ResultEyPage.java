package cn.suncsf.framework.core.common;

import java.util.List;

/**
 * Easyui Datagrid 数据格式
 * @param <T>
 */
public class ResultEyPage<T> {

    /**
     * 总条数
     */
    private  int total;

    /**
     * 查询出的数据源
     */
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


    
}
