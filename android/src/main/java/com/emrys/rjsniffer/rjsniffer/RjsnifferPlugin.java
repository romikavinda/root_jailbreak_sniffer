package com.emrys.rjsniffer.rjsniffer;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.emrys.rjsniffer.rjsniffer.Constdata.superUserPath;
import static com.emrys.rjsniffer.rjsniffer.Native.AntiFridaNativeLoader_checkFridaByPort;
import static com.emrys.rjsniffer.rjsniffer.Native.isMagiskPresentNative;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;

import androidx.annotation.NonNull;

import com.scottyab.rootbeer.RootBeer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;



/** RjsnifferPlugin */
public class RjsnifferPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private static final String PLATFORM_CHANNEL = "com.emrys.rjsniffer/epic";
  private Context context;
  private boolean bServiceBound;
  private  IIsolatedService serviceBinder;

  private final String ONEPLUS = "oneplus";
  private final String MOTO = "moto";
  private final String XIAOMI = "Xiaomi";



  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), PLATFORM_CHANNEL);
    channel.setMethodCallHandler(this);
    context = flutterPluginBinding.getApplicationContext();
    Intent intent = new Intent(context.getApplicationContext(),Sniffer.class);
    context.getApplicationContext().bindService(intent, mIsolatedServiceConnection, BIND_AUTO_CREATE);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {

    Intent intent = new Intent(context.getApplicationContext(),Sniffer.class);
    context.getApplicationContext().bindService(intent, mIsolatedServiceConnection, BIND_AUTO_CREATE);

    if (call.method.equals("runprog")) {

      boolean detected = false;

      RootBeer rootBeer = new RootBeer(context);

      if(Build.BRAND.contains(ONEPLUS) || Build.BRAND.contains(MOTO) || Build.BRAND.contains(XIAOMI)){

        if(rootBeer.isRooted()){
          detected = true;
        }

      }else{
        if(rootBeer.isRootedWithBusyBoxCheck()){
          detected = true;
        }
      }



      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
      {


        try {
          detected = serviceBinder.isMagiskPresent();
        } catch (RemoteException e) {
          throw new RuntimeException(e);
        }


      }
      else
      {

        detected = isMagisk();
      }


      try {

        if (isPathExist("su")
                || isSUExist()
                || isTestBuildKey()
                || isHaveRootHideApps()
                || isHaveDangerousApps()
                || isHaveRootManagementApps()
                || isHaveDangerousProperties()
                || isHaveReadWritePermission()) {

          detected = true;

        }
      }catch(Exception e){
        e.printStackTrace();
      }



      try {

        if (checkRootMethod7() || checkRootMethod8() || checkRootMethod9()) {

          detected = true;

        }
      }catch(Exception e){

        e.printStackTrace();
      }


      result.success(detected);

    }else if (call.method.equals("runprog2")) {
      boolean detected = false;

      if(Emulate.isEmulator() || Emulate.isEmulator2()){
        detected = true;
      }

      result.success(detected);
    }else if (call.method.equals("runprog3")) {


      if(Settings.Secure.getInt(context.getContentResolver(), Settings.Global.ADB_ENABLED, 0) == 1 || AntiFridaNativeLoader_checkFridaByPort()) {
        result.success(true);
      }else{
        result.success(false);
      }


    }
  }


  private boolean isMagisk(){

    boolean isMagiskPresent = false;

    try{

      final String[] blackListedMountPaths = { "magisk", "core/mirror", "core/img",
              "/su/bin/",
              "/system/bin/failsafe/",
              "/system/usr/we-need-root/",
              "/su",} ;

      File file = new File("/proc/self/mounts");
      FileInputStream fis = new FileInputStream(file);
      BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
      String str;
      int count =0;
      while((str = reader.readLine()) != null && (count==0)){
        for(String path:blackListedMountPaths){
          if(str.contains(path)){
            count++;
            break;
          }
        }
      }
      reader.close();
      fis.close();

      if(count > 0){

        isMagiskPresent = true;
      }if(count > 0) {
        isMagiskPresent = isMagiskPresentNative();

      }

    }catch (Exception e){

    }
    return isMagiskPresent;
  }



/*  @Override
  protected void onStart() {
    super.onStart();
    Intent intent = new Intent(context.getApplicationContext(),Sniffer.class);
        *//*Binding to an isolated service *//*
    context.getApplicationContext().bindService(intent, mIsolatedServiceConnection, BIND_AUTO_CREATE);
  }*/


  private ServiceConnection mIsolatedServiceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
      serviceBinder = IIsolatedService.Stub.asInterface(iBinder);
      bServiceBound = true;

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
      bServiceBound = false;

    }
  };


  private boolean isPathExist(String ext){
    for(String path : superUserPath){
      File file = new File(path, ext);
      if(file.exists()){
        return true;
      }
    }
    return false;
  }

  private boolean isSUExist(){
    Process process = null;
    try{
      process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which","su"});
      BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
      if(in.readLine() != null){
        return true;
      }
      return false;
    }catch (Exception e){
      return false;
    }finally {
      if(process != null){
        process.destroy();
      }
    }
  }


  private boolean isTestBuildKey(){
    String buildTags = Build.TAGS;
    if(buildTags != null && buildTags.contains("test-keys")){
      return true;
    }
    return false;
  }

  private boolean isHaveDangerousApps(){
    ArrayList<String> packages = new ArrayList<String>();
    packages.addAll(Arrays.asList(Constdata.dangerousListApps));
    return isAnyPackageFromListInstalled(packages);
  }

  private boolean isHaveRootManagementApps(){
    ArrayList<String> packages = new ArrayList<>();
    packages.addAll(Arrays.asList(Constdata.rootsAppPackage));
    return isAnyPackageFromListInstalled(packages);
  }

  private boolean isHaveRootHideApps(){
    ArrayList<String> packages = new ArrayList<>();
    packages.addAll(Arrays.asList(Constdata.rootCloakingApps));
    return isAnyPackageFromListInstalled(packages);
  }


  //check dangerous properties
  private boolean isHaveDangerousProperties(){
    final Map<String,String> dangerousProps = new HashMap<>();
    dangerousProps.put("ro.debuggable", "1");
    dangerousProps.put("ro.secure","0");

    boolean result = false;
    String[] lines = commander("getprop");
    if(lines == null){
      return false;
    }
    for(String line : lines){
      for(String key : dangerousProps.keySet()){
        if(line.contains(key)){
          String badValue = dangerousProps.get(key);
          badValue = "["+badValue+"]";
          if(line.contains(badValue)){
            result =  true;
          }
        }
      }
    }
    return result;
  }


  private boolean isHaveReadWritePermission(){
    Boolean result = false;
    String[] lines = commander("mount");

    for(String line : lines){
      String[] args = line.split(" ");
      if(args.length < 4){
        continue;
      }
      String mountPoint = args[1];
      String mountOptions = args[3];

      for(String path : Constdata.notWritablePath){
        if(mountPoint.equalsIgnoreCase(path)){
          for(String opt : mountOptions.split(",")){
            if(opt.equalsIgnoreCase("rw")){
              result = true;
              break;
            }
          }
        }
      }
    }

    return result;
  }

  private String[] commander(String command){
    try{
      InputStream inputStream = Runtime.getRuntime().exec(command).getInputStream();
      if(inputStream == null){
        return  null;
      }
      String propVal = new Scanner(inputStream).useDelimiter("\\A").next();
      return propVal.split("\n");
    }catch(Exception e ){
      e.printStackTrace();
      return null;
    }
  }

  private boolean isAnyPackageFromListInstalled(ArrayList<String> pkg){
    boolean result = false;
    PackageManager pm =context.getPackageManager();
    for(String packageName : pkg){
      try{
        pm.getPackageInfo(packageName,0);
        result = true;
      }catch(Exception e){

      }
    }
    return result;
  }

  public boolean checkRootMethod7() {
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


    boolean result = new shellEx().executeCommandSU(shellEx.SHELL_CMD.run_su);
    return result;
  }

  public boolean checkRootMethod9() {

    ArrayList<String> result = new shellEx().executeCommand(shellEx.SHELL_CMD.check_su);
    if (result != null) {
      for (String tempString : result) {
        if (tempString.endsWith("su"))
          return true;
      }
    }
    return false;
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
