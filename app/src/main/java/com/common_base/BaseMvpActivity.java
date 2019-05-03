package com.common_base;

import android.os.Bundle;

/**
 * Created by Administrator on 2019/5/3.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    //实例化mPresenter的接口，由子类去实例化具体的Presenter
    abstract P initPresenter();
}
