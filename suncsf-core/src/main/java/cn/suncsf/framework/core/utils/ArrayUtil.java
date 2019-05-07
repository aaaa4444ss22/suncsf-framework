package cn.suncsf.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    private static Logger logger = LoggerFactory.getLogger(ArrayUtil.class);
    public static <T> List<T> append(List<T> array,T item,int index) throws  Exception{
        List<T> list = null;
        try {
            list = ObjectUtil. deepCopy(array);
            if(index< 0 || index > array.size()){
                logger.error("索引超出范围！");
                throw new Exception("索引超出范围！");
            }
            list.add(index,item);
        }catch (IOException e){
            logger.error(e.getMessage());
        }catch (ClassNotFoundException e){
            logger.error(e.getMessage());
        }finally {
            if(list == null){
                list = new ArrayList<>();
            }
        }
        return list;
    }

}
