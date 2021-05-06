package com.user.p0971_servicebindclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("com.user.p0972_servicebindservice.MyService");
        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(LOG_TAG,"MainActivity onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(LOG_TAG,"MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onclickStart(View v){
        startService(intent);
    }
    public void onclickStop(View v){
        stopService(intent);
    }
    public void onclickBind(View v){
        bindService(intent, sConn, 0);
    }
   public void onclickUnbind(View v){
        if(!bound) return;
        unbindService(sConn);
        bound = false;
    }
    protected void onDestroy(){
       super.onDestroy();
       onclickUnbind(null);
    }
}
