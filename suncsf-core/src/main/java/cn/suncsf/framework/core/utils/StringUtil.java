package cn.suncsf.framework.core.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author sunchao
 * @version 1.0.0
 * @date 2019/6/26 11:54
 * @create 2019/6/26 11:54
 * @description 字符串操作
 */
public class StringUtil {

    /**
     * 拆解MD5密码
     * @param m 原密码
     * @param isline 是否加入 -
     * @param isbig 是否大写
     * @param size m.length/size=0
     * @return
     */
    public static String md5Formatter(String m,boolean isline,boolean isbig,final int size){

        String str = null;
//        if(m.length()/size > 0){
//            return str;
//        }
        String ps = DigestUtils.md5Hex(m);
        if(isline){
            StringBuilder builder = new StringBuilder(ps.length()+(ps.length()/2-1));
            for (int i = 0; i < ps.length(); i += size) {
                builder.append(ps.substring(i,i+size));
                if(i+size < ps.length()){
                    builder.append("-");
                }
            }
            ps = builder.toString();
        }
        if(isbig){
            str = ps.toUpperCase();
        }else {
            str = ps.toLowerCase();
        }
        return  str;
    }

    /**
     * 如果Strong 为 "" 则返回Null
     * @param str
     * @return
     */
    public static String getStringIsNullToBlank(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        return str;
    }
}
