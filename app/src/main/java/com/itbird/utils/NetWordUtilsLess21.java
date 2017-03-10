package com.itbird.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWordUtilsLess21 {

    public static NetworkInfo[] getAllNetworkInfo(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getAllNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

}
