package cn.suncsf.framework.core.entity;

import cn.suncsf.framework.core.utils.DateUtil;
import cn.suncsf.framework.core.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 数据一般键值生成工具
 */
public abstract class EntityDatabase extends EntityBase {

    private static final Logger logger = LoggerFactory.getLogger(EntityDatabase.class);

    private final static SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static long number = 0;
    private  static Queue<Long> queue = new LinkedList<>();
    private static final int baseSize = 50000;

    /**
     * 自动缓存ID
     */
    public static void init(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logger.debug("执行ID生成");
                if(queue.size() < baseSize){
                    for (int i = 0; i <baseSize-queue.size() ; i++) {
                        queue.add(generateDateInt());
                    }
                }
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        try {
            service.scheduleWithFixedDelay(runnable,0,1,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }

    /**
     * 生成ID
     * @param head
     * @return
     */
    public static String generateId(final String head) {
        String id = "";
        if(queue.size() > 0){
            Long lg = queue.poll();
            if(lg != null){
                id += String.valueOf(queue.poll());
            }
        }
        if(StringUtils.isBlank(id)){
            id = String.valueOf(generateDateInt());
        }
        return (StringUtils.isBlank(head)?"":head) + id;
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

    /**
     * 获取当前日期
     * @return
     */
    public synchronized  static  Date getNowDate(){
        return DateUtil.getNowDate();
    }

    /**
     * 获取当前时间
     * @return
     */
    public synchronized  static  Timestamp getNowTimestamp(){
        return DateUtil.getNowTimestamp();
    }

    /**
     * 如果Strong 为 "" 则返回Null
     * @param str
     * @return
     */
    public String getStringNotBlank(String str){
        return StringUtil.getStringIsNullToBlank(str);
    }
}
