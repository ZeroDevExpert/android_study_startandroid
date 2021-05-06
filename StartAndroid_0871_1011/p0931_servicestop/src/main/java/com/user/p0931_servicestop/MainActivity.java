package com.user.p0931_servicestop;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View v){
        startService(new Intent(this, MyService.class).putExtra("time",7));
        startService(new Intent(this, MyService.class).putExtra("time",2));
        startService(new Intent(this, MyService.class).putExtra("time",4));
    }
}
