package com.user.p0901_asynctaskstatus;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    MyTask mt;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.tvInfo);
    }

    public void onclick(View v){
        switch (v.getId()){
            case R.id.btnStart:
                startTask();
                break;
            case R.id.btnStatus:
                showStatus();
                break;
            default:
                break;
        }
    }

    public void startTask(){
        mt = new MyTask();
        mt.execute();
        mt.cancel(false);
    }

    public void showStatus(){
        if(mt!=null){
            Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class MyTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params){
            try{
                for(int i=0;i<5;i++){
                    if(isCancelled()) return null;
                    TimeUnit.SECONDS.sleep(1);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            tvInfo.setText("End");
        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
            tvInfo.setText("Cancel");
        }
    }
}
