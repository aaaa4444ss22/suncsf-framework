package cn.suncsf.framework.core.utils;

/**
 * @author sunchao
 * @version 1.0.0
 * @date 2019/9/25 15:39
 * @create 2019/9/25 15:39
 * @description
 */

import cn.suncsf.framework.core.common.KeyValueStr;
import cn.suncsf.framework.core.utils.extr.BasicAuthInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * OKHttp工具类
 */
public class OkHttpUtil {


    //    private Method method;
    private KeyValueStr basic;
    private Map<String, String> heads;

    private static ObjectMapper objectMapper;

    private OkHttpClient client;
    private OkHttpClient.Builder okClient;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private OkHttpUtil(Builder builder) {
        this.basic = builder.basic;
        this.heads = builder.heads;
//        this.method = builder.method;

        okClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);

        if (this.basic != null) {
            client = okClient.addInterceptor(new BasicAuthInterceptor(basic.getsKey(), basic.getsValue()))
                    .build();
        } else {
            client = okClient.build();
        }
    }

    public String get(String url, Map<String, String> params) {
        try {
            Response response = getResponse(url,params);
            if(response != null){
                return response.body().toString();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String get(String url) {
        return get(url, null);
    }
    public Response getResponse(String url)  throws IOException{
        return getResponse(url, null);
    }
    public Response getResponse(String url, Map<String, String> params) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            sb.append("?");
            for (Map.Entry<String, String> entry : entrySet) {
                sb.append(entry.getKey());
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        Request.Builder builder = new Request.Builder();
        if (heads != null && heads.size() > 0) {
            for (Map.Entry<String, String> item : heads.entrySet()) {
                builder = builder.addHeader(item.getKey(), item.getValue());
            }
        }
        Request request = builder
                .addHeader("Content-Type", "application/json")
                .url(url + sb.toString())
                .get()
                .build();
        Call call = client.newCall(request);
        return call.execute();
    }
    /**
     * POST 传输JSON请求
     *
     * @param url 请求地址
     * @param t   泛型对象
     * @param <T> 类型
     * @return content结果
     */
    public <T> String post(String url, T t) {
        try {
            Response response = postResponse(url,t);
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String post(String url) {
        return post(url, null);
    }
    public Response postResponse(String url) throws IOException{
        return postResponse(url, null);
    }
    /**
     * POST 传输JSON请求
     *
     * @param url 请求地址
     * @param t   泛型对象
     * @param <T> 类型
     * @return content结果
     */
    public <T> Response postResponse(String url, T t) throws IOException {
        Request.Builder builder = new Request.Builder();
        if (heads != null) {
            for (Map.Entry<String, String> item : heads.entrySet()) {
                builder = builder.addHeader(item.getKey(), item.getValue());
            }
        }
        Request.Builder requestBuilder = builder
                .url(url);
        Request request = null;
        if (null != t) {
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                    , objectMapper.writeValueAsBytes(t));
            request = requestBuilder.post(requestBody)
                    .build();
        } else {
            request = requestBuilder.build();
        }
        Call call = client.newCall(request);
        return call.execute();
    }

    public static class Builder {
        //        private Method method;
        private KeyValueStr basic;
        private Map<String, String> heads;

        public Builder setBasicAccount(KeyValueStr basic) {
            this.basic = basic;
            return this;
        }

        public Builder setHeads(Map<String, String> heads) {
            this.heads = heads;
            return this;
        }

        public OkHttpUtil build() {
            return new OkHttpUtil(this);
        }
    }


}
