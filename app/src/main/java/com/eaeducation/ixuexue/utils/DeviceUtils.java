package com.eaeducation.ixuexue.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public abstract class DeviceUtils {

    /**
     * 当前手机时区名称。如：GMT+0800
     *
     * @param context
     * @return
     */
    public static String getTimeZoneName(Context context) {
        String timeZoneDisplayName = "GMT+0800";
        try {
            // GMT+0800
            timeZoneDisplayName = TimeZone.getDefault().getDisplayName(true,
                    TimeZone.SHORT);
            long offset = TimeZone.getDefault().getRawOffset();
            int zone = (int) (offset / (60 * 60 * 1000));
            zone = zone * 100;
            if (zone >= 0) {
                timeZoneDisplayName = String.format("GMT+%04d", Math.abs(zone));
            } else {
                timeZoneDisplayName = String.format("GMT-%04d", Math.abs(zone));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeZoneDisplayName;
    }

    /**
     * 当前手机时区编号。如：Asia/Shanghai
     *
     * @param context
     * @return
     */
    public static String getTimeZoneID(Context context) {
        String timeZoneID = "Asia/Shanghai";
        try {
            // Asia/Shanghai
            timeZoneID = TimeZone.getDefault().getID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeZoneID;
    }

    /**
     * 获取手机语言信息(例如：en、zh) <br>
     * (设置成简体中文的时候，getLanguage()返回的是zh,getCountry()返回的是cn)
     *
     * @param context
     * @return
     */
    public static String getLanguage(Context context) {
        // 获取系统当前使用的语言
        String language = Locale.getDefault().getLanguage();
        return language;
    }

    /**
     * 获取手机国家信息(例如：EN、CN) <br>
     * (设置成简体中文的时候，getLanguage()返回的是zh,getCountry()返回的是cn)
     *
     * @param context
     * @return
     */
    public static String getCountry(Context context) {
        // 获取区域
        String country = Locale.getDefault().getCountry();
        return country;
    }

    /**
     * 获取当前设备的唯一标识字符串，自己组装的
     *
     * @param context
     * @return
     */
    public static String getUUID(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        final String DeviceId, SimSerialNumber, AndroidId;
//        DeviceId = "" + tm.getDeviceId();
        DeviceId = "";
//        SimSerialNumber = "" + tm.getSimSerialNumber();
        SimSerialNumber = "";
        AndroidId = ""
                + Secure.getString(
                context.getContentResolver(),
                Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(AndroidId.hashCode(),
                ((long) DeviceId.hashCode() << 32) | SimSerialNumber.hashCode());
        return deviceUuid.toString();
    }

    /**
     * 获取系统唯一标识码(IMEI)<br>
     * 获取机串IMEI: 仅仅只对Android手机有效
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
//        String DeviceId = tm.getDeviceId();
        String DeviceId = "";
        if (DeviceId == null) {
            return "";
        } else {
            return DeviceId;
        }
    }

    /**
     * 获取系统的ANDROID_ID
     */
    public static String getANDROID_ID(Context context) {
        String ANDROID_ID = Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
        if (ANDROID_ID == null) {
            return "";
        } else {
            return ANDROID_ID;
        }
    }

    /**
     * @param context
     * @return String
     * @Description: 获取卡串IMSI
     */
    public static String getIMSI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
//        String id = tm.getSubscriberId();
        String id = "";
        if (id == null) {
            return "";
        } else {
            return id;
        }
    }

    /**
     * @return String
     * @Description: 获取手机型号
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * @return String
     * @Description: 获取系统版本
     */
    public static String getOS() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取用于显示的版本号(显示如：1.0.0)
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    /**
     * 获取用于升级的版本号(内部识别号)
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 检测该包名所对应的应用是否存在（eg.com.org）
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isPackageExists(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 获取应用程序的完整包名
     *
     * @param context
     * @return eg. com.xxx.xxx
     */
    public static String getAppPackageName(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    /**
     * 获取当前实例所在的父包名
     *
     * @param context
     * @return eg. com.xxx.xxx
     */
    public static String getPackageNameClass(Context context) {
        if (context == null || "".equals(context)) {
            return "";
        }
        return context.getPackageName();
    }

    /**
     * 检测该包名所对应类是否存在（eg.com.org.MainActivity）
     *
     * @param className
     * @return
     */
    public static boolean isClassExists(String className) {
        if (className == null || "".equals(className)) {
            return false;
        }
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isVersionCompat(int VersionCode) {
        int currentVersion = Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(
                    getAppPackageName(context), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取手机的IMSI码,并判断是中国移动\中国联通\中国电信 <BR>
     * 需要加入权限 android.permission.READ_PHONE_STATE <BR>
     * <a href="http://www.open-open.com/lib/view/open1331537862874.html"
     * >参考资料</a>
     *
     * @return
     */
    public static String getNetworkOperators(Context context) {
        String strOperators = "unknown";
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        // 返回唯一的用户ID;就是这张卡的编号神马的
//        String IMSI = telephonyManager.getSubscriberId();
        String IMSI = "";
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        if (IMSI != null) {
            if (IMSI.equals("46000") || IMSI.equals("46002")
                    || IMSI.equals("46007")) {
                strOperators = "中国移动";
            } else if (IMSI.equals("46001")) {
                strOperators = "中国联通";
            } else if (IMSI.equals("46003")) {
                strOperators = "中国电信";
            }
        }
        return strOperators;
    }

    /**
     * 获取当前网络类型 <a
     * href="http://blog.csdn.net/shakespeare001/article/details/7505932"
     * >参考资料</a>
     *
     * @return
     */
    public static String getNetworkType(Context context) {
        String strNetworkType = "unknown";
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return strNetworkType;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            strNetworkType = "WIFI";
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            switch (networkInfo.getSubtype()) {
                case TelephonyManager.NETWORK_TYPE_CDMA:// 网络类型为CDMA
                case TelephonyManager.NETWORK_TYPE_EDGE:// 网络类型为EDGE
                case TelephonyManager.NETWORK_TYPE_GPRS:// 网络类型为GPRS
                    strNetworkType = "2G";
                    break;
                case TelephonyManager.NETWORK_TYPE_EVDO_0:// 网络类型为EVDO0
                case TelephonyManager.NETWORK_TYPE_EVDO_A:// 网络类型为EVDOA
                case TelephonyManager.NETWORK_TYPE_HSDPA:// 网络类型为HSDPA
                case TelephonyManager.NETWORK_TYPE_HSPA:// 网络类型为HSPA
                case TelephonyManager.NETWORK_TYPE_HSUPA:// 网络类型为HSUPA
                case TelephonyManager.NETWORK_TYPE_UMTS:// 网络类型为UMTS
                    strNetworkType = "3G";
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    strNetworkType = "4G";
                    break;
            }
        }
        return strNetworkType;
    }

    /**
     * 获取Wifi网络下的IP地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddressByWifi(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "."
                    + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
        } catch (Exception ex) {
        }
        return "0.0.0.0";
    }

    /**
     * 获取GPRS移动网络或以太网下的IP地址
     *
     * @return
     */
    public static String getLocalIpAddressByGPRS() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return "0.0.0.0";
    }


    /**
     * 判断是否具有ROOT权限
     *
     * @return
     */
    public static boolean checkPermissionRoot() {
        boolean res = false;
        try {
            res = !((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists()));
        } catch (Exception e) {

        }
        return res;
    }

    /**
     * 获取设备名称
     *
     * @return
     */
    public static String getDevicesName() {
        return Build.DEVICE;
    }

    /**
     * 获取网络连接方式wifi或者ETHERNET
     *
     * @param context
     * @return
     */
    public static String getNetworkTypeWifiOrBrand(Context context) {
        String strNetworkType = "neterror";
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return strNetworkType;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            strNetworkType = "WIFI";
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            strNetworkType = "MOBILE";
        } else {
            strNetworkType = "ETHERNET";
        }
        return strNetworkType;
    }
}
