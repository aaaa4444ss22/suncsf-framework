package cn.suncsf.framework.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
       return load(path,cls,null);
    }

    /**
     * 加载文件
     * @param path
     * @param cls
     * @return
     */
    public static Properties load(String path, Class<?> cls,String encoding){
        Properties properties = new Properties();
        try {
            if(StringUtils.isBlank(encoding)){
                encoding = "UTF-8";
            }
            InputStream in = cls.getClassLoader()
                    .getResourceAsStream(path);
            properties.load(new InputStreamReader(in,encoding));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    /**
     *
     * @param properties properties 属性集合
     * @param k 键
     * @return
     */
    public static Map<String, Map<String, String>> getListPropertyValue(Properties properties, final String k)
           {
        Map<String, Map<String, String>> list = new HashMap<>();
        for (Map.Entry<Object, Object> item : properties.entrySet()) {
            final String kName = item.getKey().toString();
            if (kName.startsWith(k)) {
                try {
                    String indexStr = kName.substring(k.length(), k.length() + 3);
                    final String regEx = "[^0-9]";
                    Pattern pattern = Pattern.compile(regEx);
                    Matcher m = pattern.matcher(indexStr);
                    final String index = m.replaceAll("").trim();
                    final String[] arrays = kName.split("\\.");
                    if (list.containsKey(index) && arrays.length > 0) {
                        Map<String, String> map = list.get(index);
                        map.put(arrays[arrays.length - 1], item.getValue().toString());

                    } else {
                        Map<String, String> map = new HashMap<>();
                        map.put(arrays[arrays.length - 1], item.getValue().toString());
                        list.put(index, map);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return list;
    }

    /**
     *
     * @param properties 属性集合
     * @param k 键值
     * @param <T>
     * @return
     */
    public static <T> T getPropertyValue(Properties properties, final String k){
        Optional<Map.Entry<Object,Object>> optional = properties.entrySet()
                .stream().filter(c->c.getKey().toString().equals(k))
                .findFirst();
        if(optional.isPresent()){
            return (T)optional.get().getValue();
        }
        return null;
    }

    /**
     *
     * @param map Map<个数分组值,Map<属性名称,属性值>>
     * @param cls 接收类型
     * @param <T> 类型
     * @return 返回填充好的数据值
     */
    public static <T> List<T> builder(Map<String, Map<String, String>> map,Class<T> cls){

//        List<T> list = new ArrayList<>();
//        for (Map.Entry<String, Map<String, String>> item : map.entrySet()) {
//            try {
//                T t = cls.newInstance();
//                BeanWrapper beanWrapper = new BeanWrapperImpl(t);
//                Map<String, String> values = item.getValue();
//                beanWrapper.setPropertyValues(values);
//                list.add(t);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
        T[] list = builderArray(map,cls);
        return Arrays.asList(list);
    }
    /**
     *
     * @param map Map<个数分组值,Map<属性名称,属性值>>
     * @param cls 接收类型
     * @param <T> 类型
     * @return 返回填充好的数据值
     */
    public static <T> T[] builderArray(Map<String, Map<String, String>> map,Class<T> cls){

        T[] array = (T[] )Array.newInstance(cls,map.entrySet().size());

        int i = 0;
        for (Map.Entry<String, Map<String, String>> item : map.entrySet()) {
            try {
                T t = cls.newInstance();
                BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                Map<String, String> values = item.getValue();
                beanWrapper.setPropertyValues(values);
                array[i] = t;
                i++;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return array;

    }
}
