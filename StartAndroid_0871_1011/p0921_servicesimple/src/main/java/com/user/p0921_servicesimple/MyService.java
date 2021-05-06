package com.user.p0921_servicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    final String LOG_TAG = "myLog";

    public void onCreate(){
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(LOG_TAG, "onStartCommand");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
    }

    public IBinder onBind(Intent intent){
        Log.d(LOG_TAG,"onBind");
        return null;
    }

    void someTask(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 10; i++){
                    Log.d(LOG_TAG,"i = " + i);
                    try{

                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                stopSelf();
                Log.d(LOG_TAG,"onTrigger stopSelf method");
            }
        });
        t.start();
    }
}
