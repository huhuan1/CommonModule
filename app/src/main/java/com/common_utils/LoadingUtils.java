package com.common_utils;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.commonmodule.R;

/**
 * 加载动画的工具类
 */

public class LoadingUtils {

    public static View getLoadingView(FragmentActivity context,String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View loadingView = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        ImageView iv = loadingView.findViewById(R.id.iv_dialog_loading);
        TextView textView = loadingView.findViewById(R.id.tv_dialog_loading);
        textView.setText(msg);
        loadingAnimator(iv);
        return loadingView;
    }

    public static View getEmptyView(FragmentActivity context, String msg, int imgRes) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View loadingView = inflater.inflate(R.layout.dialog_empty, null);// 得到加载view
        TextView textView =  loadingView.findViewById(R.id.tv_dialog_empty);// 提示文字
        textView.setText(msg);
        ImageView iv =  loadingView.findViewById(R.id.iv_dialog_empty);
        iv.setImageResource(imgRes);
        return loadingView;
    }

    public static FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        return lp;
    }

    public static View showLoadingView(ViewGroup viewGroup, FragmentActivity context, String msg) {
        View loadingView = LoadingUtils.getLoadingView(context, msg);
        viewGroup.addView(loadingView, LoadingUtils.getLayoutParams());
        return loadingView;
    }

    public static void hideLoadingView(View loadingView) {
        if (loadingView != null) {
            ((ViewGroup) loadingView.getParent()).removeView(loadingView);
        }
    }

    public static View showEmptyView(ViewGroup viewGroup, FragmentActivity context, String msg, int imgRes) {
        View emptyView = LoadingUtils.getEmptyView(context, msg,imgRes);

        viewGroup.addView(emptyView,LoadingUtils.getLayoutParams());

        return emptyView;
    }

    public static void hideEmptyView(final View emptyView) {
        if (emptyView != null) {
//            ((ViewGroup) emptyView.getParent()).removeView(emptyView);
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    ((ViewGroup) emptyView.getParent()).removeView(emptyView);
                }
            });
        }
    }

    private static void loadingAnimator(ImageView mImageView) {
        mImageView.setBackgroundResource(R.drawable.loadinganimator);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getBackground();
        // 动画是否正在运行
        if (animationDrawable.isRunning()) {
            //停止动画播放
            animationDrawable.stop();
        } else {
            //开始或者继续动画播放
            animationDrawable.start();
        }
    }

}
