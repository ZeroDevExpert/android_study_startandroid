package com.user.p0261_intentfilter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btntime = (Button)findViewById(R.id.btnTime);
        Button btndate = (Button)findViewById(R.id.btnDate);
        btntime.setOnClickListener(this);
        btndate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.btnTime:
                intent = new Intent("com.user.intent.action.showtime");
                startActivity(intent);
                break;
            case R.id.btnDate:
                intent = new Intent("com.user.intent.action.showdate");
                startActivity(intent);
                break;
        }
    }
}
