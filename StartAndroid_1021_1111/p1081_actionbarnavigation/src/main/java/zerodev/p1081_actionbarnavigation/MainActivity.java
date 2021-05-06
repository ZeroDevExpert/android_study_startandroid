package zerodev.p1081_actionbarnavigation;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

    final String LOG_TAG = "myLogs";
    String data [] = new String[] {"one", "two", "three"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getActionBar();
//        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        try {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        } catch (NullPointerException e){
            Log.d(LOG_TAG,"Exceprion null pointer");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try {
            bar.setListNavigationCallbacks(adapter, this);
        }catch (NullPointerException e){
            Log.d(LOG_TAG,"exception");
        }
//        ActionBar.Tab tab = bar.newTab();
//        tab.setText("tab1");
//        tab.setTabListener(this);
//        bar.addTab(tab);
//
//        tab = bar.newTab();
//        tab.setText("tab2");
//        tab.setTabListener(this);
//        bar.addTab(tab);

//        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//            @Override
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//                Log.d(LOG_TAG,"onTabSelected: " + tab.getText());
//            }
//
//            @Override
//            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//                Log.d(LOG_TAG,"onTabUnselected: " + tab.getText());
//            }
//
//            @Override
//            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//                Log.d(LOG_TAG,"onTabReselect: " + tab.getText());
//            }
//        };
//
//        for(int i=0;i<3;i++){
//            bar.addTab(
//                    bar.newTab().setText("Tab " + (i + 1)).setTabListener(tabListener)
//            );
//        }

    }
//
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){
//        Log.d(LOG_TAG,"reselected tab: " + tab.getText());
//    }
//
//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
//        Log.d(LOG_TAG, "selected tab: " + tab.getText());
//    }
//
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){
//        Log.d(LOG_TAG, "unselected tab: " + tab.getText());
//    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId){
        Log.d(LOG_TAG,"selected position = " + itemPosition + ", id = " + itemId + ", " + data[itemPosition]);
        return false;
    }
}
