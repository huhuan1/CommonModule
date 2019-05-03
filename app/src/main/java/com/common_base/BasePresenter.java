package com.common_base;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2019/5/2.
 */

public class BasePresenter<V extends BaseView> {
    WeakReference<V> mView;

    public void attachView(V mView){
        this.mView=new WeakReference(mView);
    }
    public boolean isViewAttached() {
        return mView != null;
    }
    public void detachView() {
        this.mView = null;
    }

}
