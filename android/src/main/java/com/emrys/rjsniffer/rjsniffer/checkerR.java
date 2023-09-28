package com.emrys.rjsniffer.rjsniffer;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class checkerR {


    //
//
//
//	private native void helloLog(String logThis);
/*    private native boolean checkfopen(String filepath);
    private native boolean runsu(String command);
    private native boolean statfile(String filepath);
    private native boolean runls(String filepath, String filename, boolean exactMatch);
    private native boolean runpmlist(String packageName, boolean exactMatch);
    private native boolean checkifstream(String filepath);*/

    PackageManager pm;
    private ActivityManager manager;


    public boolean checkRootMethod0() {
       // Log.e("**********","c1");
        String buildTags = Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    public boolean checkRootMethod1() {
        //Log.e("**********","c2");
        try {
            File file = new File("/system/app/Superuser.apk");
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkRootMethod2() {
        //Log.e("**********","c3");
        ArrayList<String> result = new shellEx().executeCommand(shellEx.SHELL_CMD.check_su_binary);
        if (result != null) {
            return true;
        }
        return false;
    }

    public boolean checkRootMethod3() {
        //Log.e("**********","c4");
        try {
            File file = new File("/system/xbin/su");
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkRootMethod4() {
        //Log.e("**********","c5");
        try {
            File file = new File("/system/bin/su");
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkRootMethod5() {
        //Log.e("**********","c6");

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.contains("supersu") || packageInfo.packageName.contains("superuser")) {

                return true;
            }
        }

        return false;
    }





    public boolean checkRootMethod7() {
        //Log.e("**********","c8");
        ArrayList<String> result = new shellEx().executeCommand(shellEx.SHELL_CMD.check_daemon_su);
        if (result != null) {
            for (String tempString : result) {
                if (tempString.contains("daemonsu"))
                    return true;
            }
        }
        return false;
    }

    public boolean checkRootMethod8() {
        //Log.e("**********","c9");

        boolean result = new shellEx().executeCommandSU(shellEx.SHELL_CMD.run_su);
        return result;
    }

    public boolean checkRootMethod9() {
        //Log.e("***","C10");
        ArrayList<String> result = new shellEx().executeCommand(shellEx.SHELL_CMD.check_su);
        if (result != null) {
            for (String tempString : result) {
                if (tempString.endsWith("su"))
                    return true;
            }
        }
        return false;
    }

/*    public boolean checkRootMethod10() {
        //Log.e("***","C11");
        boolean returnValue = false;
        // Get currently running application processes
        List<RunningServiceInfo> list = manager.getRunningServices(300);
        if(list != null){
            String tempName;
            for(int i=0;i<list.size();++i){
                tempName = list.get(i).process;

                if(tempName.contains("supersu") || tempName.contains("superuser") || tempName.contains("magisk")){

                    returnValue = true;
                }
            }
        }
        return returnValue;
    }*/

/*    public boolean checkRootMethod11() {
        //Log.e("***","C12");
        boolean returnValue = false;
        // Get currently running application processes
        List<RunningTaskInfo> list = manager.getRunningTasks(300);
        if(list != null){
            String tempName;
            for(int i=0;i<list.size();++i){
                tempName = list.get(i).baseActivity.flattenToString();

                if(tempName.contains("supersu") || tempName.contains("superuser")){

                    returnValue = true;
                }
            }
        }
        return returnValue;
    }*/

    public boolean checkRootMethod12() {

        //Log.e("***","C13");
        boolean returnValue = false;
        // Get currently running application processes
        ProcessBuilder cmd;
        String result = null;

        try{
            String[] args = {"su"};
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();

            result = "";
            InputStream in = process.getInputStream();

            returnValue = true;
            in.close();
        } catch(IOException ex){
           // Log.e("***",ex.toString());
        }

        return returnValue;
    }

    public boolean checkRootMethod13() {
        //Log.e("***","C14");
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        List<String> commands = new ArrayList<String>();
        commands.add("sh");
        commands.add("-c");
        commands.add("ls -l /system/xbin/");
        ProcessBuilder pb = new ProcessBuilder(commands);
        try {
            Process prs = pb.start();
            prs.waitFor();
            is = prs.getInputStream();
            byte[] b = new byte[4096];
            int size = 0;
            baos = new ByteArrayOutputStream();
            while((size = is.read(b)) != -1){
                baos.write(b, 0, size);
            }
            String out = new String(baos.toByteArray());

            if (out.contains("daemonsu") || out.contains("magisk"))
                return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try {
                if(is != null) is.close();
                if(baos != null) baos.close();
            } catch (Exception ex){}
        }
        return false;
    }

    public boolean checkRootMethod14() {

        InputStream is = null;
        ByteArrayOutputStream baos = null;
        List<String> commands = new ArrayList<String>();
        commands.add("ls");
        commands.add("-l");
        commands.add("/system/xbin/");
        ProcessBuilder pb = new ProcessBuilder(commands);
        try {
            Process prs = pb.start();
            is = prs.getInputStream();
            byte[] b = new byte[4096];
            int size = 0;
            baos = new ByteArrayOutputStream();
            while((size = is.read(b)) != -1){
                baos.write(b, 0, size);
            }
            String out = baos.toString();

            if (out.contains("daemonsu") || out.contains("magisk"))
                return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try {
                if(is != null) is.close();
                if(baos != null) baos.close();
            } catch (Exception ex){}
        }
        return false;
    }




    /*public boolean checkRootMethodNative0() {

        boolean returnValue = false;
        returnValue = checkfopen("/system/xbin/su");


        return returnValue;
    }

    public boolean checkRootMethodNative1() {

        boolean returnValue = false;
        returnValue = runsu("/system/xbin/su");


        return returnValue;
    }

    public boolean checkRootMethodNative2() {

        boolean returnValue = false;
        returnValue = statfile("/system/xbin/daemonsu");

        return returnValue;
    }

    public boolean checkRootMethodNative3() {

        boolean returnValue = false;
        returnValue = runls("/system/xbin/", "su", true);

        return returnValue;
    }

    public boolean checkRootMethodNative4() {

        boolean returnValue = false;
        returnValue = runls("/system/bin/", "su", true);

        return returnValue;
    }

    public boolean checkRootMethodNative5() {

        boolean returnValue = false;
        returnValue = runls("/system/xbin/", "daemonsu", true);

        return returnValue;
    }

    public boolean checkRootMethodNative6() {

        boolean returnValue = false;
        returnValue = runls("/system/app/", "Superuser.apk", true);

        return returnValue;
    }

    public boolean checkRootMethodNative7() {

        boolean returnValue = false;
        returnValue = runls("/data/app/", "eu.chainfire.supersu", false);

        return returnValue;
    }

    public boolean checkRootMethodNative8() {

        boolean returnValue = false;
        returnValue = runls("/data/app/", "com.noshufou.android.su", false);

        return returnValue;
    }

    public boolean checkRootMethodNative9() {

        boolean returnValue = false;
        returnValue = runpmlist("eu.chainfire.supersu", true);

        return returnValue;
    }

    public boolean checkRootMethodNative10() {

        boolean returnValue = false;
        returnValue = runpmlist("com.noshufou.android.su", true);

        return returnValue;
    }

    public boolean checkRootMethodNative11() {

        boolean returnValue = false;
        returnValue = runpmlist("rootkeeper", false);
        if (returnValue == false) {
            returnValue = runpmlist("hidemyroot", false);
        }

        return returnValue;
    }

    public boolean checkRootMethodNative12() {

        boolean returnValue = false;
        returnValue = checkifstream("/system/xbin/su");


        return returnValue;
    }*/




}
