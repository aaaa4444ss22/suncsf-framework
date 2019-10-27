package cn.suncsf.framework.core;

import static org.junit.Assert.assertTrue;

import cn.suncsf.framework.core.common.KeyValueStr;
import cn.suncsf.framework.core.entity.EntityBase;
import cn.suncsf.framework.core.entity.EntityKeyValue;
import cn.suncsf.framework.core.entity.ResultObject;
import cn.suncsf.framework.core.utils.DateUtil;
import cn.suncsf.framework.core.utils.JsonUtil;
import cn.suncsf.framework.core.utils.OkHttpUtil;
import cn.suncsf.framework.core.utils.PropertiesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        Map<String, Map<String, String>> list = PropertiesUtil.getListPropertyValue(properties, key);
//        List<Object> lista = new ArrayList<>();
        List<KeyValueStr> list1 = PropertiesUtil.builder(list,KeyValueStr.class);

        try {
            System.out.println(JsonUtil.getObjectMapper().writeValueAsString(list1));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String v = PropertiesUtil.getPropertyValue(properties,"list.statesa[0].sKey");
        System.out.println(v);

    }


    /**
     * 根据键获取相应的键列表
     */
    public static Object getList(Properties properties, final String k, final Class<?> type, final Class<?> cls) {
        if (type == List.class) {
            Map<String, Map<String, String>> list = new HashMap<>();
            new ResultObject().createResult(0,"");

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

    @Test
    public void t1(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = DateUtil.localDateToDate(DateUtil.getWeekEndDay());
        System.out.println(format.format(d));
    }

    @Test
    public void t2(){
        String body = new OkHttpUtil
                .Builder()
                .setHeads(new HashMap<>())
                .setBasicAccount(new KeyValueStr())
                .build()
                .post("http://www.baidu.com");
        System.out.println(body);
    }

    @Test
    public void t3(){
        LocalDateTime dateTime = DateUtil.dateToLocalDateTime(new Date());
        Assert.assertNotNull(dateTime);
    }

}