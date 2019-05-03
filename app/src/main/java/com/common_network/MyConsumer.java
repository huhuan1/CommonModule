package com.common_network;

import android.content.Context;

import com.common_utils.ToastUtil;
import com.common_exceptions.ExceptionHandle;

import io.reactivex.functions.Consumer;

/**
 * Created by admin on 2017/12/21.
 */
//rxjava网络请求错误,统一处理
public abstract class MyConsumer implements Consumer<Throwable> {
    Context context;

    public MyConsumer(Context context) {
        this.context = context;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {





    }

    public  void onError(ExceptionHandle.ResponeThrowable responeThrowable){
        ToastUtil.showToast(context,responeThrowable.message);
    };
    public abstract void onE(Context context);
}
