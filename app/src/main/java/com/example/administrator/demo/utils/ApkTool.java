package com.example.administrator.demo.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.administrator.demo.bean.MyAppInfo;

import java.util.ArrayList;
import java.util.List;

public class ApkTool {
    static  String TAG = "ApkTool";  
    public static List<MyAppInfo> mLocalInstallApps = null;  
  
    public static List<MyAppInfo> scanLocalInstallAppList(PackageManager packageManager) {
        List<MyAppInfo> myAppInfos = new ArrayList<MyAppInfo>();
        try {  
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {  
                PackageInfo packageInfo = packageInfos.get(i);  
                //过滤掉系统app  
            if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                continue;
            }
                MyAppInfo myAppInfo = new MyAppInfo();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    String[] splitNames = packageInfo.splitNames;
                    for (String sp :splitNames) {
                        Log.e("应用名"," ：： " + sp);
                    }
                }else{
                    myAppInfo.setAppName(packageInfo.packageName);
                }

//                if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
//                    continue;
//                }
                myAppInfo.setImage(packageInfo.applicationInfo.loadIcon(packageManager));  
                myAppInfos.add(myAppInfo);  
            }  
        }catch (Exception e){  
            Log.e(TAG,"===============获取应用包信息失败");
        }  
        return myAppInfos;  
    }  
  
}  