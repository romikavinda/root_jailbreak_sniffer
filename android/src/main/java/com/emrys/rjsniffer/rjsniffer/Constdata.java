package com.emrys.rjsniffer.rjsniffer;

public final class Constdata {

    public static final String[] rootsAppPackage = {
            "com.noshufou.android.su",
            "com.noshufou.android.su.elite",
            "eu.chainfire.supersu",
            "com.koushikdutta.superuser",
            "com.thirdparty.superuser",
            "com.yellowes.su",
            "com.topjohnwu.magisk",
    };

    public static final String[] dangerousListApps ={
            "com.koushikdutta.rommanager",
            "com.koushikdutta.rommanager.license",
            "com.dimonvideo.luckypatcher",
            "com.chelpus.lackypatch",
            "com.ramdroid.appquarantine",
            "com.ramdroid.appquarantinepro",
            "com.android.vending.billing.InAppBillingService.COIN",
            "com.chelpus.luckypatcher"
    };

    public static final String[] rootCloakingApps ={
            "com.devadvance.rootcloak",
            "com.devadvance.rootcloakplus",
            "de.robv.android.xposed.installer",
            "com.saurik.substrate",
            "com.zachspong.temprootremovejb",
            "com.amphoras.hidemyroot",
            "com.amphoras.hidemyrootadfree",
            "com.formyhm.hiderootPremium",
            "com.formyhm.hideroot",

    };

    public static final String[] superUserPath = {
            "/data/local/su",
            "/data/local/bin/su",
            "/data/local/xbin/su",
            "/sbin/su",
            "/su/bin/su",
            "/system/bin/su",
            "/system/bin/.ext/su",
            "/system/bin/failsafe/su",
            "/system/sd/xbin/su",
            "/system/usr/we-need-root/su",
            "/system/xbin/su",
            "/cache/su",
            "/data/su",
            "/dev/su",
            "/data/adb/magisk",
            "/data/adb/.magisk",
            "/data/adb/magisk/busybox",
            "/data/adb/.magisk/busybox",
            "/system/xbin/daemonsu",
            "/data/adb/magisk.img",
            "/data/adb/magisk",
            "/data/adb/.magisk",
    };

/*    public static final String[] superUserPath = {
            "/sbin/.magisk",
            "/sbin/magisk",
            "/data/local/",
            "/data/magisk",
            "/data/zygisk",
            "/sbin/zygisk",
            "/data/local/bin/",
            "/data/local/xbin/",
            "/sbin/",
            "/su/bin/",
            "/system/bin/",
            "/system/bin/.ext/",
            "/system/bin/failsafe/",
            "/system/sd/xbin/",
            "/system/usr/we-need-root/",
            "/system/xbin/",
            "/cache",
            "/data",
            "/dev",
            "/data/adb/magisk",
            "/data/adb/.magisk",
            "/data/adb/magisk/busybox",
            "/data/adb/.magisk/busybox",
            "/system/xbin/daemonsu",
            "/data/adb/magisk.img",
            "/data/adb/magisk",
            "/data/adb/.magisk",

    };*/

    public static final String[] notWritablePath ={
            "/system",
            "/system/bin",
            "/system/sbin",
            "/system/xbin",
            "/vendor/bin",
            "/sbin",
            "/etc",
    };
}
