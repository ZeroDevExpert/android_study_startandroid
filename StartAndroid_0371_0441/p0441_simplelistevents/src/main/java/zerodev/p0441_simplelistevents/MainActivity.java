package zerodev.p0441_simplelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView)findViewById(R.id.lvMain);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.names,android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Log.d(LOG_TAG,"itemClick: position = " + position + "," +
                        " id = " + id);
            }
        });

        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG,"itemSelect: position = " + position + ", id = " +id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG,"itemSelect: nothing");
            }
        });

        //sadfasf

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollstate) {
                Log.d(LOG_TAG,"scrollState = " + scrollstate);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
/*                Log.d(LOG_TAG,"scroll: firstVisibleItem = " + firstVisibleItem + "," +
                        " visibleItemCount: " + visibleItemCount + ", " +
                        "totalItemCount: " + totalItemCount);*/
            }
        });
    }
}
