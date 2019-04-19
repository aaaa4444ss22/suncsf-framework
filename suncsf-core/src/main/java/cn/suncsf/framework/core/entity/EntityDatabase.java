package cn.suncsf.framework.core.entity;

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


    private final static SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static long number = 0;

    /**
     * 生成ID
     * @param head
     * @return
     */
    public static String generateId(String head) {
        return head + String.valueOf(generateDateInt());
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
        return number;
    }


    public synchronized  static  Date getNowDate(){
        return  Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public synchronized  static  Timestamp getNowTimestamp(){
        return Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
