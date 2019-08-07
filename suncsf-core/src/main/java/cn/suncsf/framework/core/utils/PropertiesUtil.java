package cn.suncsf.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author sunchao
 * @version 1.0.0
 * @date 2019/8/3 10:26
 * @create 2019/8/3 10:26
 * @description
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 加载文件
     * @param path
     * @param cls
     * @return
     */
    public static Properties load(String path, Class<?> cls){
        Properties properties = new Properties();
        try {
            InputStream in = cls.getClassLoader()
                    .getResourceAsStream(path);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
