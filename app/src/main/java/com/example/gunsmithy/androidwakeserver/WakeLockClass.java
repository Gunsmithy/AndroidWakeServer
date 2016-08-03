package com.example.gunsmithy.androidwakeserver;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;

/**
 * Created by Dylan Kauling on 2016-08-02.
 */
public class WakeLockClass {

    PowerManager.WakeLock fullWakeLock;
    PowerManager.WakeLock partialWakeLock;
    Context mContext;

    public WakeLockClass(Context passedContext){
        mContext = passedContext;
        createWakeLocks();
    }

    // Called from onCreate
    public void createWakeLocks(){
        PowerManager powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        fullWakeLock = powerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "Loneworker - FULL WAKE LOCK");
        partialWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Loneworker - PARTIAL WAKE LOCK");
    }

    // Called whenever we need to wake up the device
    public void wakeDevice() {
        fullWakeLock.acquire();
        KeyguardManager keyguardManager = (KeyguardManager) mContext.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
        keyguardLock.disableKeyguard();
        fullWakeLock.release();
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://sensorian.io/"));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }

}
