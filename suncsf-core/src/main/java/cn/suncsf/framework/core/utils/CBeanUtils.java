package cn.suncsf.framework.core.utils;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunchao
 * @version 1.0.0
 * @date 2019/8/1 14:24
 * @create 2019/8/1 14:24
 * @description
 */
public class CBeanUtils {

    public static <T> List<T> copyPropertiesTolist(List<?> sources, Class<?> vClass) {
        if (sources == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Object item : sources) {
            try {
                Object v = vClass.newInstance();
                BeanUtils.copyProperties(item, v);
                list.add((T) v);

            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public static <T> T copyProperties(Object source, Class<?> vClass) {
        if (source == null) {
            return null;
        }
        try {
            Object v = vClass.newInstance();
            BeanUtils.copyProperties(source, v);
            return (T) v;
        }catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
