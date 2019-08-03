package cn.suncsf.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author sunchao
 * @version 1.0.0
 * @date 2019/7/18 13:54
 * @create 2019/7/18 13:54
 * @description 时间工具类
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     * LocalDate 转 Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate){
//        LocalDate createTime = LocalDate.parse(node.get("CreateTime").asText());
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay(zone).toInstant();
        return Date.from(instant);
    }


    /**
     * 获取当前日期
     * @return
     */
    public synchronized  static  Date getNowDate(){
        Date date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        logger.debug("获取当前日期 -> {}",date);
        return  date;
    }

    /**
     * 获取当前时间
     * @return
     */
    public synchronized  static Timestamp getNowTimestamp(){
        Timestamp timestamp = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        logger.debug("获取当前时间 -> {}",timestamp);
        return  timestamp;
    }

}
