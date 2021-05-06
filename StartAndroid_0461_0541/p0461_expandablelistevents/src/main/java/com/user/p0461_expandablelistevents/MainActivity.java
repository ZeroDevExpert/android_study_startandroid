package com.user.p0461_expandablelistevents;

import android.app.ExpandableListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.tvInfo);
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();
        elvMain = (ExpandableListView)findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        // press to element
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(LOG_TAG,"onChildClick groupPosition = " + groupPosition + " id = " + id);
                tvInfo.setText(ah.getGroupChildText(groupPosition,childPosition));
                return false;
            }
        });

        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d(LOG_TAG,"onGroupCollapse groupPosition = " + groupPosition);
                tvInfo.setText("Svernuli " + ah.getGroupText(groupPosition));
            }
        });

        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d(LOG_TAG,"Razvernuli " + ah.getGroupText(groupPosition));
                tvInfo.setText("Razvernuli " + ah.getGroupText(groupPosition));
            }
        });
        elvMain.expandGroup(2);
    }
}
