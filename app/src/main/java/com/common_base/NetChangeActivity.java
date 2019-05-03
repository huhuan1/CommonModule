package com.common_base;

import android.content.IntentFilter;
import android.os.Bundle;

import com.common_broadcast.NetBroadCastReceiver;
import com.common_constants.Constants;
import com.common_interfaces.NetChangeDeal;
import com.common_utils.ToastUtil;

/**
 * Created by Administrator on 2019/5/3.
 */
//在BaseActivity的基础上，增加网络变化监听
public abstract class NetChangeActivity extends BaseActivity implements NetChangeDeal {
    NetBroadCastReceiver netBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netChangeDeal=this;
        //实例化IntentFilter对象
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netBroadCastReceiver = new NetBroadCastReceiver();
        //注册广播接收
        registerReceiver(netBroadCastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (netBroadCastReceiver != null) {
            unregisterReceiver(netBroadCastReceiver);
        }
    }

    @Override
    public void onNetChange(int netState) {
        if (netState == Constants.NETWORK_NONE) {
//            sendEmptyMessage(HandlerConstant.NETWORK_NONE);
            ToastUtil.showToast(this, "没有网络连接");
        } else if (netState == Constants.NETWORK_MOBILE) {
//            sendEmptyMessage(HandlerConstant.NETWORK_MOBILE);
            ToastUtil.showToast(this, "移动网络已连接");
        } else if (netState == Constants.NETWORK_WIFI) {
//            sendEmptyMessage(HandlerConstant.NETWORK_WIFI);
            ToastUtil.showToast(this, "WIFI已连接");
        } else if (netState == Constants.NETWORK_ETHERNET) {
//            sendEmptyMessage(HandlerConstant.NETWORK_ETHERNET);
            ToastUtil.showToast(this, "以太网已连接");
        }
    }
}
