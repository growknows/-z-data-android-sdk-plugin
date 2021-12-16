package com.zalldata.analytics.android.plugin.utils;

import com.zalldata.analytics.android.plugin.Logger;

import java.lang.reflect.Field;
import java.net.URLClassLoader;

public class VersionUtils {
    // 是否打开 TV 开关
    public static boolean isAndroidTv;
    // 卓尔埋点 SDK 版本号
    public static String zallSDKVersion = "";

    /**
     * 是否是 TV 版本
     * @return true 是，false 不是
     */
    public static boolean isTvVersion() {
        return isAndroidTv && zallSDKVersion.endsWith("tv");
    }

    /**
     * 读取卓尔 Android 埋点 SDK 版本号
     * @param urlClassLoader ClassLoader
     */
    public static void loadAndroidSDKVersion(URLClassLoader urlClassLoader) {
        try {
            Class zallDataAPI = urlClassLoader.loadClass("com.zalldata.analytics.android.sdk.ZallDataAPI");
            Field versionField = zallDataAPI.getDeclaredField("VERSION");
            versionField.setAccessible(true);
            zallSDKVersion = (String) versionField.get(null);
            Logger.info("卓尔埋点 SDK 版本号:" + zallSDKVersion);
        } catch(Throwable throwable) {
            Logger.info("卓尔埋点 SDK 版本号读取失败，reason: " + throwable.getMessage());
        }
    }
}
