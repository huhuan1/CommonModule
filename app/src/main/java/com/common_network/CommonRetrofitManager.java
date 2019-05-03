package com.common_network;

/**
 * Created by Administrator on 2019/5/3.
 */

//其他的组件，要用retrofit时，需要实现这个接口（传入各自的ApiService,不实现此接口，需要每次都传入ApiService）
public interface CommonRetrofitManager {
    <T> T getApiService(Class<T> cls);
}
