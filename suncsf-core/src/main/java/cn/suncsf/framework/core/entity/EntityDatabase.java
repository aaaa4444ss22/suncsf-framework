package cn.suncsf.framework.core.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * 数据一般键值生成工具
 */
public abstract class EntityDatabase extends EntityBase {

    private static final Logger logger = LoggerFactory.getLogger(EntityDatabase.class);

    private final static SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static long number = 0;

    /**
     * 生成ID
     * @param head
     * @return
     */
    public static String generateId(String head) {
        String id = head + String.valueOf(generateDateInt());
        logger.info("生成ID -> {}",id);
        return id;
    }

    /**
     * 生成时间序列
     * @return
     */
    public synchronized static long generateDateInt() {

        if (number == 0) {
            number = Long.valueOf(simpleDataFormat.format(new Date()));
        } else {
            number++;
        }
        logger.info("生成时间序列 -> {}",number);
        return number;
    }

    /**
     * 获取当前日期
     * @return
     */
    public synchronized  static  Date getNowDate(){
        Date date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        logger.info("获取当前日期 -> {}",date);
        return  date;
    }

    /**
     * 获取当前时间
     * @return
     */
    public synchronized  static  Timestamp getNowTimestamp(){
        Timestamp timestamp = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        logger.info("获取当前时间 -> {}",timestamp);
        return  timestamp;
    }
}
