package cn.suncsf.framework.core.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * JSON工具
 */
public class JsonUtil {

    private  final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 序列化JSON
     * @param object
     * @return
     */
    public  static  String toJson(Object object){
        String jsonStr = null;

       try {
           jsonStr = objectMapper.writeValueAsString(object);
       }catch (JsonProcessingException e){
           e.printStackTrace();
       }
        return  jsonStr;
    }
}
