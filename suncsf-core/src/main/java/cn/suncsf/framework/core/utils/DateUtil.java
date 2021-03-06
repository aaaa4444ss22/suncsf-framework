package cn.suncsf.framework.core.utils;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

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
     * 计算天数的基础除数
     */
    public static final long CALCULATE_DAY_BASE_NUMBER = 1000 * 24 * 60 * 60;
    /**
     * 计算小时数的基础除数
     */
    public static final long CALCULATE_HOUR_BASE_NUMBER = 1000 * 60 * 60;
    /**
     * 计算分钟数的基础除数
     */
    public static final long CALCULATE_MINUTE_BASE_NUMBER = 1000 * 60;

    /**
     * LocalDate 转 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
//        LocalDate createTime = LocalDate.parse(node.get("CreateTime").asText());
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay(zone).toInstant();
        return Date.from(instant);
    }
    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
//        LocalDate createTime = LocalDate.parse(node.get("CreateTime").asText());
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }


    public static LocalDateTime dateToLocalDateTime(Date date) {
        long time = date.getTime();
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static LocalDate dateToLocalDate(Date date) {
        long time = date.getTime();
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zone = ZoneId.systemDefault();
        return instant.atZone(zone).toLocalDate();
    }
    /**
     * 获取当前日期
     *
     * @return
     */
    public synchronized static Date getNowDate() {
        Date date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        logger.debug("获取当前日期 -> {}", date);
        return date;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public synchronized static Timestamp getNowTimestamp() {
        Timestamp timestamp = Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        logger.debug("获取当前时间 -> {}", timestamp);
        return timestamp;
    }


    /**
     * 获取周开始
     *
     * @return
     */
    public static LocalDate getWeekFirstDay() {
        return LocalDate.now().with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1);
    }

    /**
     * 获取周结束
     *
     * @return
     */
    public static LocalDate getWeekEndDay() {
        return LocalDate.now().with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 7);
    }


    /**
     * 获取日期在年月周
     *
     * @return
     */
    public static Map<DateTypeEnum, Integer> getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //外国月份为0-11所以月份加一
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int mweek = cal.get(Calendar.WEEK_OF_MONTH);
        return new HashMap<DateTypeEnum, Integer>() {{
            put(DateTypeEnum.Year, year);
            put(DateTypeEnum.Month, month);
            put(DateTypeEnum.Week, mweek);
        }};
    }

    public static float calculateBothDateResult(Date dt1,Date dt2,DateTypeEnum dateTypeEnum){
        Assert.assertNotNull(dt1);
        Assert.assertNotNull(dt2);
        float lresultTime = (dt1.getTime() - dt2.getTime())*1.0f;
        if(DateTypeEnum.Day == dateTypeEnum){
            return lresultTime / CALCULATE_DAY_BASE_NUMBER;
        }else if(DateTypeEnum.Hour == dateTypeEnum){
            return lresultTime / CALCULATE_HOUR_BASE_NUMBER;
        }else {
            return lresultTime / CALCULATE_MINUTE_BASE_NUMBER;
        }
    }

    public enum DateTypeEnum {
        Year,
        Month,
        Day,
        Week,
        Hour,
        Minute
    }
}
