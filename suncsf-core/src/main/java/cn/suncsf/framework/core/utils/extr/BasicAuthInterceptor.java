package cn.suncsf.framework.core.utils.extr;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/8/3 9:44
 * @create 2019/8/3 9:44
 * @description
 */
public class BasicAuthInterceptor implements Interceptor {
    private String credentials;


    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }
}
