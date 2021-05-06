package com.user.p0811_handlersimplemessage;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public String LOG_TAG = "myLogs";

    final int STATUS_NONE = 0;
    final int STATUS_CONNECTING = 1;
    final int STATUS_CONNECTED = 2;

    Handler h;
    TextView tvStatus;
    Button btnConnect;
    ProgressBar pbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView)findViewById(R.id.tvStatus);
        btnConnect = (Button)findViewById(R.id.btnConnect);
        pbConnect = (ProgressBar)findViewById(R.id.pbConnect);
        h = new Handler(){
            @Override
            public void handleMessage(android.os.Message msg){
                switch (msg.what){
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(false);
                        tvStatus.setText("Connecting...");
                        pbConnect.setVisibility(View.VISIBLE);
                        break;
                    case STATUS_CONNECTED:
                        pbConnect.setVisibility(View.GONE);
                        tvStatus.setText("Connected");
                        break;
                }
            };
        };
        h.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View v){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    h.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(2);

                    h.sendEmptyMessage(STATUS_CONNECTED);
                    TimeUnit.SECONDS.sleep(3);

                    h.sendEmptyMessage(STATUS_NONE);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
