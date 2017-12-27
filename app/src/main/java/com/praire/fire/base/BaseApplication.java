package com.praire.fire.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;

import java.lang.ref.WeakReference;
import java.util.Stack;


public abstract  class BaseApplication extends Application {

    private Stack<Activity> activities;

    private static boolean created = false;

    @Override
    public void onCreate() {
        super.onCreate();
        if(created){
            return;
        }
        activities = new Stack< >();
        create();
        created = true;
    }

    protected abstract void create() ;

    public int getActivityCount(){
        return activities.size();
    }

    public void pushActivity(Activity activity){
        if(activities.contains(activity)){
            activities.remove(activity);
        }
        activities.push(activity);
    }

    public void finishActivity(Activity activity){
        activities.remove(activity);
    }

    public void showTips(Activity activity) {
        final  WeakReference<Activity> wf = new WeakReference<Activity>(activity);
        AlertDialog alertDialog = new AlertDialog.Builder(activity).setTitle("提醒")
                .setMessage("是否退出程序")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Activity activity = wf.get();
                        if(null != activity){
                            activity.finish();
                            wf.clear();
                        }
                        exit();
                    }

                }).setNegativeButton("取消",

                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).create(); // 创建对话框
        alertDialog.show(); // 显示对话框
    }

    public void exit(){
        activities.clear();
        android.os.Process.killProcess(android.os.Process
                .myPid());
        System.exit(0);
    }

}
