package cn.suncsf.framework.core;

import cn.suncsf.framework.core.entity.EntityDatabase;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/6/26 17:14
 * @create 2019/6/26 17:14
 * @description
 */
public class CoreInit {

    /**
     * 构造ids队列
     */
    public static void initEnintyDatabaseIdQueue(){
        EntityDatabase.init();
    }
}
