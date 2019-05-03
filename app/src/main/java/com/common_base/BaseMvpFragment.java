package com.common_base;

/**
 * Created by Administrator on 2019/5/3.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
    P mPresenter;


    @Override
    public void onStart() {
        super.onStart();
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }

    //实例化mPresenter的接口，由子类去实例化具体的Presenter
    abstract P initPresenter();
}
