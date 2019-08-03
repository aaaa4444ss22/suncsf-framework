package cn.suncsf.framework.core.utils;

import cn.suncsf.framework.core.common.KeyValueStr;
import cn.suncsf.framework.core.utils.extr.BasicAuthInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.BasicAuthenticator;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp工具类
 */
public class OkHttpHelper {
    private static OkHttpHelper instance;
    private OkHttpClient okHttpClient;
    private ObjectMapper objectMapper;

    public OkHttpHelper() {
		this(null);
    }

	public OkHttpHelper(KeyValueStr keyPass) {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.connectTimeout(2, TimeUnit.MINUTES)
				.readTimeout(2, TimeUnit.MINUTES);
		if(keyPass != null){
			okHttpClient = builder.addInterceptor(new BasicAuthInterceptor(keyPass.getsKey(),keyPass.getsValue()))
					.build();
		}else {
			okHttpClient = builder.build();
		}
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}


    public static OkHttpHelper getInstance() {
		return getInstance(null);
    }

	public static OkHttpHelper getInstance(KeyValueStr keyPass) {
		if (instance == null) {
			synchronized (OkHttpHelper.class) {
				if (instance == null) {
					instance = new OkHttpHelper(keyPass);
				}
			}
		}
		return instance;
	}


    public String get(String url) {
        return get(url, null);
    }


    public String get(String url, Map<String, String> params) {
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
        Request request = new Request.Builder().url(url + sb.toString()).get().build();
        Call call = okHttpClient.newCall(request);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * POST请求
     *
     * @param url    请求地址
     * @param params 参数
     * @return content结果
     */
    public String post(String url, Map<String, String> params) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                String value = entry.getValue();
                if (StringUtils.isEmpty(value)) {
                    value = "";
                }
                formBodyBuilder.add(entry.getKey(), value);
            }
        }
        Request request = new Request.Builder().url(url).post(formBodyBuilder.build()).build();
        Call call = okHttpClient.newCall(request);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        if (null != t) {
            try {
                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                        , objectMapper.writeValueAsBytes(t));

                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                try {
                    return call.execute().body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


}
