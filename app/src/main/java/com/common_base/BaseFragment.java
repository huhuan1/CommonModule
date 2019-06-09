package com.common_base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common_utils.LoadingUtils;
import com.common_utils.LogUtils;
import com.example.administrator.commonmodule.R;
import com.trello.rxlifecycle2.components.RxFragment;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/5/3.
 */

public abstract class BaseFragment extends RxFragment {
    protected BaseActivity mActivity;
    public boolean isVisiable;
    public View loadingView, emptyView;

    private String test="aaaaaa";
    private String test1="gggg";
    private String test2="ssssssss";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisiable = true;
            onVisiable();
        } else {
            isVisiable = false;
            onUnVisiable();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);
        initData();
        bindToLife();
        return view;
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
            loadingView = LoadingUtils.showLoadingView(viewGroup, mActivity, "正在加载");
        }
    }

    protected void showLoadingView(ViewGroup viewGroup, String msg) {
        if (loadingView == null) {
            loadingView = LoadingUtils.showLoadingView(viewGroup, mActivity, msg);
        }
    }

    protected void hideLoadingView() {
        LoadingUtils.hideLoadingView(loadingView);
        loadingView = null;
    }

    protected void showEmptyView(ViewGroup viewGroup) {
        if (emptyView == null) {
            emptyView = LoadingUtils.showEmptyView(viewGroup, mActivity, "暂无数据", R.mipmap.ic_launcher);
        }
    }

    protected void showEmptyView(ViewGroup viewGroup, String msg, int imgRes) {
        if (emptyView == null) {
            emptyView = LoadingUtils.showEmptyView(viewGroup, mActivity, msg, imgRes);
        }
    }

    protected void hideEmptyView() {
        LoadingUtils.hideEmptyView(emptyView);
        emptyView = null;
    }

    public abstract void onVisiable();

    public abstract void onUnVisiable();

    public abstract int getFragmentLayout();

    protected abstract void initData();
}
