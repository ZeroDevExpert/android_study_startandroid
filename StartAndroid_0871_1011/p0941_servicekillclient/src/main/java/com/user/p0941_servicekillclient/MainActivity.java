package com.user.p0941_servicekillclient;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity{

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onСlickStart(View v){
        startService(new Intent("com.user.p0942servicekillserver.MyService").putExtra("name","value"));
        Log.d(LOG_TAG,"pressed button");
    }
}