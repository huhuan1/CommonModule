package com.common_network;

import com.common_exceptions.ExceptionHandle;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2019/5/3.
 */

public abstract class MyObservable<P> implements Observer<P> {


    @Override
    public void onError(Throwable e) {
       onFaiure(ExceptionHandle.handleException(e));
    }

    abstract void onFaiure(ExceptionHandle.ResponeThrowable e);
}
