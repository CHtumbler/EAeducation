package com.eaeducation.ixuexue.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.eaeducation.ixuexue.MyApplication;
import com.google.gson.Gson;


/**
 * Created by cuihao on 2017/7/18.
 */

public class APISP {

    private static APISP sInstance;

    private static SharedPreferences sSharedPreferences;

    public static Gson gson = new Gson();

    private APISP() {
        if (sSharedPreferences == null) {
            sSharedPreferences = MyApplication.getApplication().getSharedPreferences("api", Context.MODE_PRIVATE);
        }
    }

    public synchronized static APISP getInstance() {
        if (sInstance == null) {
            sInstance = new APISP();
        }
        return sInstance;
    }

    public void edit(String key, String value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putString(key, value).commit();
        }
    }

    public void edit(int key, String value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putString(String.valueOf(key), value).commit();
        }
    }

    public void edit(String key, int value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putInt(key, value).commit();
        }
    }

    public void edit(String key, float value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putFloat(key, value).commit();
        }
    }

    public void edit(String key, boolean value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putBoolean(key, value).commit();
        }
    }

    public void edit(String key, long value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putLong(key, value).commit();
        }
    }

    public String getValue(String key) {
        if (sSharedPreferences != null) {
            return sSharedPreferences.getString(key, null);
        }
        return null;
    }

    public String getValue(int key) {
        if (sSharedPreferences != null) {
            return sSharedPreferences.getString(String.valueOf(key), null);
        }
        return null;
    }

    public int getIntValue(String key) {
        if (sSharedPreferences != null) {
            return sSharedPreferences.getInt(key, -1);
        }
        return -1;
    }

    public float getFloatValue(String key) {
        if (sSharedPreferences != null) {
            return sSharedPreferences.getFloat(key, 0f);
        }
        return 0f;
    }

    public boolean getBooleanValue(String key) {
        if (sSharedPreferences != null) {
            return sSharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    public long getLongValue(String key) {
        if (sSharedPreferences != null) {
            return sSharedPreferences.getLong(key, 0L);
        }
        return 0L;
    }

    /**
     * 清空缓存，只建议再注销登录时使用
     */
    public void clearAll() {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().clear().commit();
        }
    }

    public void clear(String key) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().remove(key).commit();
        }
    }
}
