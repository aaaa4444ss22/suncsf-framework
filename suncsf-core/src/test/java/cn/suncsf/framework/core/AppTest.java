package cn.suncsf.framework.core;

import static org.junit.Assert.assertTrue;

import cn.suncsf.framework.core.common.KeyValueStr;
import cn.suncsf.framework.core.entity.EntityBase;
import cn.suncsf.framework.core.entity.EntityKeyValue;
import cn.suncsf.framework.core.utils.PropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
@RunWith(JUnit4.class)
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    public static void main(String[] args) {
        Properties properties = PropertiesUtil.load("test.properties", AppTest.class);
        final String key = "list.states";
        final Class<?> cls = List.class;
        final Class<?> ccls = KeyValueStr.class;
        Map<String, Map<String, String>> list = (Map<String, Map<String, String>>) getList(properties, key, cls, ccls);
        List<Object> lista = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> item : list.entrySet()) {
            try {
                Object o = ccls.newInstance();
                BeanWrapper beanWrapper = new BeanWrapperImpl(o);
                        Method[] methods = o.getClass().getMethods();
                Map<String, String> map = item.getValue();
                for (Method method : methods) {
//                    System.out.println(method.getName());
                    for (Map.Entry<String, String> kv : map.entrySet()) {
                        if (method.getName().replace("set", "").equalsIgnoreCase(kv.getKey())) {
                            try {
                                System.out.println(method.getName());
                                Method field = o.getClass().getDeclaredMethod(method.getName(), Object.class);
                                field.setAccessible(true);
                                field.invoke(o, kv.getValue());

                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }
                lista.add(o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println(lista.size());
    }


    /**
     * 根据键获取相应的键列表
     */
    public static Object getList(Properties properties, final String k, final Class<?> type, final Class<?> cls) {
        if (type == List.class) {
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
        return null;
    }


}