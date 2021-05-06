package com.user.p0841_handlerrunnable;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int counter;

    final String LOG_TAG = "myLogs";
    final int max = 100;

    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = new Handler();
        pbCount = (ProgressBar)findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);

        tvInfo = (TextView)findViewById(R.id.tvInfo);
        chbInfo = (CheckBox)findViewById(R.id.chbInfo);
        chbInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    tvInfo.setVisibility(View.VISIBLE);
                    h.post(showInfo);
                }else{
                    tvInfo.setVisibility(View.GONE);
                    h.removeCallbacks(showInfo);
                }
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(counter = 1; counter < max; counter++){
                        TimeUnit.MILLISECONDS.sleep(100);
                        h.post(updateProgress);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            pbCount.setProgress(counter);
        }
    };
    Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.d(LOG_TAG, "showInfo");
            tvInfo.setText("Count = " + counter);
            h.postDelayed(showInfo,1000);
        }
    };
}
