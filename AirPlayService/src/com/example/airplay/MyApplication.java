package com.example.airplay;

import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.example.airplay.util.Constants;

public class MyApplication extends Application implements IPlayService {
    private static MyApplication mMyApplication;
    private TransportState mState;

    public MyApplication() {
        // TODO Auto-generated constructor stub
        this.mState = TransportState.STOPPED;
    }

    //单例模式中获取唯一的MyApplication实例   
    public static MyApplication getInstance() {
        if (null == mMyApplication) {
            synchronized (MyApplication.class) {
                if (null == mMyApplication)
                    mMyApplication = new MyApplication();
            }
        }
        return mMyApplication;
    }

    /**
     * 
     * Indicates whether the specified action can be used as an intent. This
     * 
     * method queries the package manager for installed packages that can
     * 
     * respond to an intent with the specified action. If no suitable package is
     * 
     * found, this method returns false.
     * 
     * 
     * 
     * @param context
     *            The application's environment.
     * 
     * @param action
     *            The Intent action to check for availability.
     * 
     * 
     * 
     * @return True if an Intent with the specified action can be sent and
     * 
     *         responded to, false otherwise.
     */

    public static boolean isIntentAvailable(Context context, String action) {
        //java.lang.NullPointerException
        //at android.content.ContextWrapper.getPackageManager(ContextWrapper.java:86)


        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;

    }

    public void setPlayerState(TransportState state) {
        this.mState = state;
    }

    @Override
    public TransportState getPlayerState() {
        // TODO Auto-generated method stub
        return this.mState;
    }

}
