package com.user.p0241_twoactivitystate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final String TAG = "States";
    Button btnActTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnActTwo = (Button)findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);
        Log.d(TAG,"MainActivity: onCreate()");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"MainActivity: onRestart()");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"MainActivity: onStart()");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"MainActivity: onResume()");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"MainActivity: onPause()");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"MainActivity: onStop()");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"MainActivity: onDestroy()");
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this,ActivityTwo.class);
        startActivity(intent);
    }
}
