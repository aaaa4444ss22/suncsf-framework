package cn.suncsf.framework.core.common;

import cn.suncsf.framework.core.entity.EntityBase;
import cn.suncsf.framework.core.entity.IPageList;

/**
 * 返回数据工具
 */
public class ResultUtil {

    /**
     * PageList 转 Easyui PageList
     * @param pageList
     * @param <T>
     * @return
     */
    public  static <T>  ResultEyPage<T> toResultEyPage(IPageList<T> pageList){

        ResultEyPage<T> resultEyPage = new ResultEyPage<T>();
        resultEyPage.setRows(pageList.getSource());
        resultEyPage.setTotal(pageList.getCount());
        return  resultEyPage;
    }



}
