package com.emrys.rjsniffer.rjsniffer;

import android.os.Build;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Emulate {

    private static final ArrayList<String> paths = new ArrayList<>(Arrays.asList(
            "/dev/socket/genyd",
            "/dev/socket/baseband_genyd",
            "/dev/socket/qemud",
            "/dev/qemu_pipe",
            "ueventd.android_x86.rc",
            "x86.prop",
            "ueventd.ttVM_x86.rc",
            "init.ttVM_x86.rc",
            "fstab.ttVM_x86",
            "fstab.vbox86",
            "init.vbox86.rc",
            "ueventd.vbox86.rc",
            "fstab.andy",
            "ueventd.andy.rc",
            "fstab.nox",
            "init.nox.rc",
            "ueventd.nox.rc"));



    public static boolean isEmulator(){
        return Build.MANUFACTURER.contains("Genymotion")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.toLowerCase().contains("droid4x")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.HARDWARE == "goldfish"
                || Build.HARDWARE == "vbox86"
                || Build.HARDWARE.toLowerCase().contains("nox")
                || Build.FINGERPRINT.startsWith("generic")
                || Build.PRODUCT == "sdk"
                || Build.PRODUCT == "google_sdk"
                || Build.PRODUCT == "sdk_x86"
                || Build.PRODUCT == "vbox86p"
                || Build.PRODUCT.toLowerCase().contains("nox")
                || Build.BOARD.toLowerCase().contains("nox")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
    }

    public static boolean isEmulator2(){

        try {

            for (int i = 0; i < paths.size(); i++) {
                File file = new File(paths.get(i));
                if(file.exists()){
                    return true;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }




}
