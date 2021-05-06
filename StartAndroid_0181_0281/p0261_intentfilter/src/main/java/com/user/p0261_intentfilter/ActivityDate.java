package com.user.p0261_intentfilter;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class ActivityDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
        String time = sdf.format(new Date(System.currentTimeMillis()));

        TextView tvDate = (TextView)findViewById(R.id.tvDate);
        tvDate.setText(time);
    }
}
