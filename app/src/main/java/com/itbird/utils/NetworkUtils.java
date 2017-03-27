package com.itbird.utils;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * 网络工具类
 * Created by itbird on 2016/6/6
 */
public final class NetworkUtils {

    /**
     * 由于4.0有开启背景数据控制，因此需要传activity的上下文，不能用app的上下文
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo[] info = getAllNetworkInfo(context);
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i] != null && info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    private static NetworkInfo[] getAllNetworkInfo(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return NetWordUtilsMore21.getAllNetworkInfo(context);
        } else {
            return NetWordUtilsLess21.getAllNetworkInfo(context);
        }
    }
}