package cn.hotwoo.alien.servicelife.model.server;

import cn.hotwoo.alien.servicelife.config.API;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by linlongxin on 2016/2/28.
 */
public class ServiceAPIModule {

    private static ServiceAPIModule instance;

    private static OkHttpClient okHttpClient;

    private static Retrofit retrofit;

    private ServiceAPIModule() {
    }

    public static ServiceAPIModule getInstance() {
        if (instance == null) {
            synchronized (ServiceAPIModule.class) {
                if (instance == null) {
                    instance = new ServiceAPIModule();
                }
            }
        }
        return instance;
    }

    public ServiceAPI providerServiceAPI(OkHttpClient okHttpClient) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASEURL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(ServiceAPI.class);
    }

    public ServiceAPI providerServiceAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASEURL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providerOkHttpClient())
                    .build();
        }
        return retrofit.create(ServiceAPI.class);
    }

    public OkHttpClient providerOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return okHttpClient;
    }
}
