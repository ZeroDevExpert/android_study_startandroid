package com.user.p0501_simpleadaptercustom2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int load[] = {41,48,22,35,30,67,51,88};
        ArrayList<Map<String,Object>> data = new ArrayList<Map<String, Object>>(load.length);
        Map<String,Object> m;
        for (int i = 0; i < load.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT,"Day " + (i + 1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB,load[i]);
            m.put(ATTRIBUTE_NAME_LL,load[i]);
            data.add(m);
        }
        String[] from = {ATTRIBUTE_NAME_TEXT,ATTRIBUTE_NAME_PB,ATTRIBUTE_NAME_LL};
        int[] to = {R.id.tvLoad,R.id.pbLoad,R.id.llLoad};

        SimpleAdapter sAdapter = new SimpleAdapter(this,data,R.layout.item,from,to);
        sAdapter.setViewBinder(new MyViewBinder());

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder{
        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);

        @Override
        public boolean setViewValue(View v, Object data, String textRepresentation){
            int i = 0;
            switch (v.getId()){
                case R.id.llLoad:
                    i = ((Integer) data).intValue();
                    if(i < 40) v.setBackgroundColor(green); else
                        if(i < 60) v.setBackgroundColor(orange); else
                            v.setBackgroundColor(red);
                    return true;
                case R.id.pbLoad:
                    i = ((Integer) data).intValue();
                    ((ProgressBar)v).setProgress(i);
                    return true;
            }
            return false;
        }
    }
}
