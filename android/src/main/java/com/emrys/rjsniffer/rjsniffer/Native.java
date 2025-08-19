package com.emrys.rjsniffer.rjsniffer;

class Native {

    static {
        System.loadLibrary("rjsniffer");
    }

    static native boolean isMagiskPresentNative();
    static native boolean AntiFridaNativeLoader_checkFridaByPort();

}
