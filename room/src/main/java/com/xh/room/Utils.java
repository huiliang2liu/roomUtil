package com.xh.room;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {

    private static final List<String> ZUI = Collections.singletonList("com.zui.launcher");
    /**
     * description：小米设备的launcher
     */
    private static final List<String> XIAO_MI = Arrays.asList(
            "com.miui.miuilite",
            "com.miui.home",
            "com.miui.miuihome",
            "com.miui.miuihome2",
            "com.miui.mihome",
            "com.miui.mihome2"

    );
    /**
     * description：索尼设备的launcher
     */
    private static final List<String> SONY = Collections.singletonList("com.sonyericsson.home");
    /**
     * description：三星设备的launcher
     */
    private static final List<String> SAM_SUNG = Arrays.asList(
            "com.sec.android.app.launcher",
            "com.sec.android.app.twlauncher"
    );
    /**
     * description：oppo设备的launcher
     */
    private static final List<String> OPPO = Collections.singletonList("com.oppo.launcher");
    /**
     * description：vivo设备的launcher
     */
    private static final List<String> VIVO = Arrays.asList("com.bbk.launcher2", "com.vivo.launcher");
    /**
     * description：nova设备的launcher
     */
    private static final List<String> NOVA = Collections.singletonList("com.teslacoilsw.launcher");
    /**
     * description：htc设备的launcher
     */
    private static final List<String> HTC = Collections.singletonList("com.htc.launcher");
    /**
     * description：lg设备的launcher
     */
    private static final List<String> LGE = Arrays.asList(
            "com.lge.launcher",
            "com.lge.launcher2"
    );
    /**
     * description：华为设备的launcher
     */
    private static final List<String> HUA_WEI = Collections.singletonList("com.huawei.android.launcher");
    /**
     * description：asus设备的launcher
     */
    private static final List<String> ASUS = Collections.singletonList("com.asus.launcher");
    /**
     * description：apex设备的launcher
     */
    private static final List<String> APEX = Collections.singletonList("com.anddoes.launcher");
    /**
     * description：adw设备的launcher
     */
    private static final List<String> ADW = Arrays.asList(
            "org.adw.launcher",
            "org.adwfreak.launcher"
    );
    /**
     * description：everything设备的launcher
     */
    private static final List<String> EVERYTHING = Collections.singletonList("me.everything.launcher");

    private static final int DEFAULT_TYPE = -1;
    private static final int ZUI_TYPE = 0;
    private static final int XIAO_MI_TYPE = 1;
    private static final int SONY_TYPE = 2;
    private static final int SAM_SUNG_TYPE = 3;
    private static final int OPPO_TYPE = 4;
    private static final int VIVO_TYPE = 5;
    private static final int NOVA_TYPE = 6;
    private static final int HTC_TYPE = 7;
    private static final int LGE_TYPE = 8;
    private static final int HUA_WEI_TYPE = 9;
    private static final int ASUS_TYPE = 10;
    private static final int APEX_TYPE = 11;
    private static final int ADW_TYPE = 12;
    private static final int ZTE_TYPE = 13;
    private static final int EVERYTHING_TYPE = 14;
    private static final int HONOR_TYPE = 15;

    public static int ROOM_TYPE = DEFAULT_TYPE;

    public static boolean hasGms;
    public static boolean hasGooglePlay;

    protected static void init(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", PackageManager.GET_ACTIVITIES);
            hasGms = true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", PackageManager.GET_ACTIVITIES);
            hasGooglePlay = true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
            if (Build.BRAND.equalsIgnoreCase("HONOR")) {
                ROOM_TYPE = HONOR_TYPE;
                return;
            }
            ROOM_TYPE = HUA_WEI_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("HONOR")) {
            ROOM_TYPE = HONOR_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            ROOM_TYPE = XIAO_MI_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
            ROOM_TYPE = OPPO_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
            ROOM_TYPE = VIVO_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
            ROOM_TYPE = ZUI_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("ZTE")) {
            ROOM_TYPE = ZTE_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("LG")) {
            ROOM_TYPE = LGE_TYPE;
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("SAMSUNG")) {
            ROOM_TYPE = SAM_SUNG_TYPE;
            return;
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo == null)
            return;
        if (resolveInfo.activityInfo.name.toLowerCase().contains("resolver"))
            return;
        String packageName = resolveInfo.activityInfo.packageName;
        if (HUA_WEI.indexOf(packageName) >= 0) {
            if (Build.BRAND.equalsIgnoreCase("HONOR")) {
                ROOM_TYPE = HONOR_TYPE;
                return;
            }
            ROOM_TYPE = HUA_WEI_TYPE;
            return;
        }
        if (XIAO_MI.indexOf(packageName) >= 0) {
            ROOM_TYPE = XIAO_MI_TYPE;
            return;
        }
        if (OPPO.indexOf(packageName) >= 0) {
            ROOM_TYPE = OPPO_TYPE;
            return;
        }
        if (VIVO.indexOf(packageName) >= 0) {
            ROOM_TYPE = VIVO_TYPE;
            return;
        }
        if (SAM_SUNG.indexOf(packageName) >= 0) {
            ROOM_TYPE = SAM_SUNG_TYPE;
            return;
        }
        if (SONY.indexOf(packageName) >= 0) {
            ROOM_TYPE = SONY_TYPE;
            return;
        }
        if (NOVA.indexOf(packageName) >= 0) {
            ROOM_TYPE = NOVA_TYPE;
            return;
        }
        if (HTC.indexOf(packageName) >= 0) {
            ROOM_TYPE = HTC_TYPE;
            return;
        }
        if (ZUI.indexOf(packageName) >= 0) {
            ROOM_TYPE = ZUI_TYPE;
            return;
        }
        if (LGE.indexOf(packageName) >= 0) {
            ROOM_TYPE = LGE_TYPE;
            return;
        }
        if (ASUS.indexOf(packageName) >= 0) {
            ROOM_TYPE = ASUS_TYPE;
            return;
        }
        if (APEX.indexOf(packageName) >= 0) {
            ROOM_TYPE = APEX_TYPE;
            return;
        }
        if (ADW.indexOf(packageName) >= 0) {
            ROOM_TYPE = ADW_TYPE;
            return;
        }
        if (EVERYTHING.indexOf(packageName) >= 0) {
            ROOM_TYPE = EVERYTHING_TYPE;
            return;
        }
    }

    public static String room() {
        if (ROOM_TYPE == SAM_SUNG_TYPE)
            return "SamSung";
        if (ROOM_TYPE == HUA_WEI_TYPE) {
            if (Build.BRAND.equalsIgnoreCase("HONOR"))
                return "Honor";
            return "HuaWei";
        }
        if (ROOM_TYPE == XIAO_MI_TYPE)
            return "Xiaomi";
        if (ROOM_TYPE == OPPO_TYPE)
            return "OPPO";
        if (ROOM_TYPE == VIVO_TYPE)
            return "VIVO";
        if (ROOM_TYPE == SONY_TYPE)
            return "Sony";
        if (ROOM_TYPE == NOVA_TYPE)
            return "Nova";
        if (ROOM_TYPE == HTC_TYPE)
            return "HTC";
        if (ROOM_TYPE == LGE_TYPE)
            return "LGE";
        if (ROOM_TYPE == ASUS_TYPE)
            return "ASUS";
        if (ROOM_TYPE == APEX_TYPE)
            return "APEX";
        if (ROOM_TYPE == ADW_TYPE)
            return "ADW";
        if (ROOM_TYPE == ZTE_TYPE)
            return "ZTE";
        if (ROOM_TYPE == EVERYTHING_TYPE)
            return "Everything";
        if (ROOM_TYPE == ZUI_TYPE)
            return "zui";
        return "default";
    }

    public static boolean isSamSung() {
        return ROOM_TYPE == SAM_SUNG_TYPE;
    }

    public static boolean isHuaWei() {
        return ROOM_TYPE == HUA_WEI_TYPE;
    }

    public static boolean isHonor() {
        return ROOM_TYPE == HONOR_TYPE;
    }

    public static boolean isXiaoMi() {
        return ROOM_TYPE == XIAO_MI_TYPE;
    }

    public static boolean isOppo() {
        return ROOM_TYPE == OPPO_TYPE;
    }

    public static boolean isVivo() {
        return ROOM_TYPE == VIVO_TYPE;
    }

    public static boolean isSony() {
        return ROOM_TYPE == SONY_TYPE;
    }

    public static boolean isNova() {
        return ROOM_TYPE == NOVA_TYPE;
    }

    public static boolean isHtc() {
        return ROOM_TYPE == HTC_TYPE;
    }

    public static boolean isLge() {
        return ROOM_TYPE == LGE_TYPE;
    }

    public static boolean isAsus() {
        return ROOM_TYPE == ASUS_TYPE;
    }

    public static boolean isApex() {
        return ROOM_TYPE == APEX_TYPE;
    }

    public static boolean isAdw() {
        return ROOM_TYPE == ADW_TYPE;
    }

    public static boolean isZte() {
        return ROOM_TYPE == ZTE_TYPE;
    }

    public static boolean isEverything() {
        return ROOM_TYPE == EVERYTHING_TYPE;
    }

    public static boolean isZui() {
        return ROOM_TYPE == ZUI_TYPE;
    }

    public static boolean isHasGms() {
        return hasGms;
    }

    public static boolean isHasGooglePlay() {
        return hasGooglePlay;
    }
}
