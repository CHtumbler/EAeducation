package com.eaeducation.manto.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.eaeducation.manto.MyApplication;


/**
 * Created by Cuihao on 2017/3/30.
 */

public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getApplication().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isAvailable());
    }
}
