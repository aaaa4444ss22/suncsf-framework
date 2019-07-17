package cn.suncsf.framework.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	        okHttpClient=new OkHttpClient.Builder()
	                .connectTimeout(2, TimeUnit.MINUTES)
	                .readTimeout(2, TimeUnit.MINUTES)
	                .build();
	        
	        objectMapper = new ObjectMapper();
	        objectMapper.setSerializationInclusion(Include.NON_NULL);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	    }

	   public static OkHttpHelper getInstance() {
	        if (instance == null) {
	            synchronized (OkHttpHelper.class) {
	                if (instance == null) {
	                    instance = new OkHttpHelper();
	                }
	            }
	        }
	        return instance;
	    }

		/**
		 * һ���get���� ����һ�����������ϣ������url��Ȼ��ȡ�ķ��ص�String��
		 */
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
	     * һ���post���� ����һ�����������ϣ������url�ͷ�װ������Map��Ȼ��ȡ�ķ��ص�String��
	     */
	    public String post(String url, Map<String, String> params) {
	        FormBody.Builder formBodyBuilder = new FormBody.Builder();
	        if (params != null && params.size() > 0) {
	            Set<Map.Entry<String, String>> entrySet = params.entrySet();
	            for (Map.Entry<String, String> entry : entrySet) {
	            	String value = entry.getValue();
	            	if(StringUtils.isEmpty(value)) {
	            		value ="";
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
	    public  <T> String post(String url,T t ) {
	    	if(null != t) {
	    		try {
					RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
							,objectMapper.writeValueAsBytes(t) );
					
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
