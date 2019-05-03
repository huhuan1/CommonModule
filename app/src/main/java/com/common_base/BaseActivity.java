package com.common_base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.common_interfaces.NetChangeDeal;
import com.common_utils.LoadingUtils;
import com.common_utils.LogUtils;
import com.example.administrator.commonmodule.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity extends RxAppCompatActivity {
    //网络监听的回调对象
    public static NetChangeDeal netChangeDeal;
    public View loadingView, emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initData();
        bindToLife();
    }

    void bindToLife() {
        // 当执行onDestory()时， 自动解除订阅
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d("Unsubscribing subscription from onCreate()");
                    }
                })
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        LogUtils.d("Started in onCreate(), running until onDestory():" + num);
                    }
                });
    }

    protected abstract void initData();

    protected abstract int setLayoutId();


    ObservableTransformer applySchedulers() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void showLoadingView(ViewGroup viewGroup) {
        if (loadingView == null) {
            loadingView = LoadingUtils.showLoadingView(viewGroup, BaseActivity.this, "正在加载");
        }
    }

    protected void showLoadingView(ViewGroup viewGroup, String msg) {
        if (loadingView == null) {
            loadingView = LoadingUtils.showLoadingView(viewGroup, BaseActivity.this, msg);
        }
    }

    protected void hideLoadingView() {
        LoadingUtils.hideLoadingView(loadingView);
        loadingView = null;
    }

    protected void showEmptyView(ViewGroup viewGroup) {
        if (emptyView == null) {
            emptyView = LoadingUtils.showEmptyView(viewGroup, BaseActivity.this, "暂无数据", R.mipmap.ic_launcher);
        }
    }

    protected void showEmptyView(ViewGroup viewGroup, String msg, int imgRes) {
        if (emptyView == null) {
            emptyView = LoadingUtils.showEmptyView(viewGroup, BaseActivity.this, msg, imgRes);
        }
    }

    protected void hideEmptyView() {
        LoadingUtils.hideEmptyView(emptyView);
        emptyView = null;
    }

}
