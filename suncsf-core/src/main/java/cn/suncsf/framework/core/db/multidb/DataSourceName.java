package cn.suncsf.framework.core.db.multidb;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/8/8 13:55
 * @create 2019/8/8 13:55
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DataSourceName {
    String value() default "masterDataSource";
}
