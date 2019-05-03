package com.common_interfaces;

import com.common_beans.BaseBean;

/**
 * Created by Administrator on 2019/5/3.
 */

public interface NetCallBack<T extends BaseBean> {
    void onSuccess(T baseBean);

    void onFail();
}
