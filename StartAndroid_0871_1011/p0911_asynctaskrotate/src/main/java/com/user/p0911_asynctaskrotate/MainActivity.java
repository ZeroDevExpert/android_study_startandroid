package com.user.p0911_asynctaskrotate;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.textclassifier.TextClassification;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    final String LOG_TAG = "qwe";

    MyTask mt;
    TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "create mainactivity: " + this.hashCode());
        tv = (TextView)findViewById(R.id.tvInfo);

        mt = (MyTask) getLastNonConfigurationInstance();
        if(mt == null){
            mt = new MyTask();
            mt.execute();
        }
        mt.link(this);
        Log.d(LOG_TAG,"create myTask: " + mt.hashCode());
    }

    public Object onRetainNonConfigurationInstance(){
        mt.unLink();
        return mt;
    }

    static class MyTask extends AsyncTask<String,Integer,Void>{

        MainActivity activity;

        void link(MainActivity act){
            activity = act;
        }
        void unLink(){
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params){
            try{
                for(int i=0;i<10;i++){
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("qwe","i = " + i + ", MyTask is "
                        + this.hashCode() + ", MainActivity: " + activity.hashCode());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            activity.tv.setText("i = " + values[0]);
        }
    }

}
