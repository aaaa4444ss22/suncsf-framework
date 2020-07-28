package cn.suncsf.framework.core.utils;


import cn.suncsf.framework.core.entity.EntityKeyValue;
import cn.suncsf.framework.core.utils.extr.KeyValueGen;
//import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//                BeanUtils.copyProperties();
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
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
