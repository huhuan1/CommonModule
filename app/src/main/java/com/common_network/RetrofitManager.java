package com.common_network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/15.
 */
public class RetrofitManager {
    private static volatile Retrofit retrofit;

    public static Retrofit createRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(NetAddress.PATH)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(GetOkHttpClient.getOkhttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
