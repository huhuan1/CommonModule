package com.common_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.common_base.BaseActivity;
import com.common_interfaces.NetChangeDeal;
import com.common_utils.NetUtil;

public class NetBroadCastReceiver extends BroadcastReceiver {
    public NetChangeDeal netChangeDeal;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            netChangeDeal= BaseActivity.netChangeDeal;
            if (netChangeDeal != null) {
                // 接口回调传过去状态的类型
                netChangeDeal.onNetChange(NetUtil.getNetWorkState(context));
            }
        }
    }
}
