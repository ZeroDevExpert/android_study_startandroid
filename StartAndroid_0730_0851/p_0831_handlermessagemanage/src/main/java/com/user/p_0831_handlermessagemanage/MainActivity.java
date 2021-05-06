package com.user.p_0831_handlermessagemanage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Handler h;

    Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Log.d(LOG_TAG, "what = " + message.what);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = new Handler(hc);
        sendMessages();
    }

    void sendMessages(){
        Log.d(LOG_TAG,"send messages");
        h.sendEmptyMessageDelayed(1,1000);
        h.sendEmptyMessageDelayed(2,2000);
        h.sendEmptyMessageDelayed(3,3000);
        h.removeMessages(2);
    }

}
