package cn.suncsf.framework.core.utils;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


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

    /**
     * 反序列化
     * @param json
     * @param valueType
     * @param <T>
     * @return
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    public static <T> T toEntity(String json,Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {

        return objectMapper.readValue(json,valueType);

    }

    /**
     * 获取ObjectMapper
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
