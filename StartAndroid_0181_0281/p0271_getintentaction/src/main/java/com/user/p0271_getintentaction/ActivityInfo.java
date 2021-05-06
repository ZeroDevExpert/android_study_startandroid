package com.user.p0271_getintentaction;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class ActivityInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String action = intent.getAction();
        String format = "", textinfo = "";
        if (action.equals("com.user.intent.action.showtime")){
            format = "HH:mm:ss";
            textinfo = "Time: ";
        }
        else if(action.equals("com.user.intent.action.showdate")){
            format = "dd.MM.yyyy";
            textinfo = "Date: ";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String datetime = sdf.format(new Date(System.currentTimeMillis()));
        TextView tvDate = (TextView)findViewById(R.id.tvinfo);
        tvDate.setText(textinfo + datetime);
    }
}
