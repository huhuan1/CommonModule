package com.common_network;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/4/4.
 */

public class NetReconnectFunc1 implements Function<Observable<? extends Throwable>, Observable<?>> {
    private final int maxRetries = 3;
    private final int retryDelayMillis = 300;
    private int retryCount;


    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        if (++retryCount <= maxRetries) {
            return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
        }
        return observable;
    }
}
