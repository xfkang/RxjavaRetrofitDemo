package com.itbird.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class NetWordUtilsMore21 {

    public static NetworkInfo[] getAllNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Network[] networks = cm.getAllNetworks();
            if (networks == null || networks.length == 0) {
                return null;
            }
            NetworkInfo[] result = new NetworkInfo[networks.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = cm.getNetworkInfo(networks[i]);
            }
            return result;
        } catch (Exception e) {
            return null;
        } catch (NoSuchMethodError error) {
            return NetWordUtilsLess21.getAllNetworkInfo(context);
        }
    }

}

