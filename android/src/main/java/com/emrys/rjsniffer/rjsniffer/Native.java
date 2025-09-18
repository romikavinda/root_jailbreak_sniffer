package com.emrys.rjsniffer.rjsniffer;

class Native {

    static {
        System.loadLibrary("rjsniffer-lib");
    }

    static native boolean isMagiskPresentNative();
    static native boolean AntiFridaNativeLoader_checkFridaByPort();

}
