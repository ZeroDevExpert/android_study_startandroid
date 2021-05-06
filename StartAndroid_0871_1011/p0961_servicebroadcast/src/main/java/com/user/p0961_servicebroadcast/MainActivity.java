package com.user.p0961_servicebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";

    public final static String BROADCAST_ACTION = "com.user.p0961_servicebroadcast";

    TextView tvTask1, tvTask2, tvTask3;
    BroadcastReceiver br;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTask1 = (TextView)findViewById(R.id.tvTask1);
        tvTask1.setText("Task1");
        tvTask2 = (TextView)findViewById(R.id.tvTask2);
        tvTask2.setText("Task2");
        tvTask3 = (TextView)findViewById(R.id.tvTask3);
        tvTask3.setText("Task3");

        br = new BroadcastReceiver(){
            public void onReceive(Context context, Intent intent){
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS,0);
                Log.d(LOG_TAG,"onRecieve: task = " + task + ", status = " + status);

                if(status == STATUS_START){
                    switch (task){
                        case TASK1_CODE:
                            tvTask1.setText("task1 start");
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("task2 start");
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("task3 start");
                            break;
                    }
                }

                if(status == STATUS_FINISH){
                    int res = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task){
                        case TASK1_CODE:
                            tvTask1.setText("task1 finish. Result = " + res);
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("task2 finish. Result = " + res);
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("task3 finish. Result = " + res);
                            break;
                    }
                }
            }
        };
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, intFilt);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(br);
    }

    public void onclick(View v){
        Intent intent;

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7).putExtra(PARAM_TASK, TASK1_CODE);
        startService(intent);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4).putExtra(PARAM_TASK, TASK2_CODE);
        startService(intent);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 6).putExtra(PARAM_TASK, TASK3_CODE);
        startService(intent);
    }
}
