package zerodev.p0141_menuadv;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    CheckBox chb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
        chb = (CheckBox)findViewById(R.id.chbExtMenu);
    }

    //create menu
    public boolean onCreateOptionsMenu(Menu menu){
/*
        menu.add(0,1,0,"add");
        menu.add(0,2,0,"edit");
        menu.add(0,3,3,"delete");
        menu.add(1,4,1,"copy");
        menu.add(1,5,2,"paste");
        menu.add(1,6,4,"exit");
*/
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //refresh menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.setGroupVisible(R.id.group1,chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        StringBuilder sb = new StringBuilder();
        sb.append("Item Menu");
        sb.append("\r\n group ID: " + String.valueOf(item.getGroupId()));
        sb.append("\r\n item ID: " + String.valueOf(item.getItemId()));
        sb.append("\r\n order: " + String.valueOf(item.getOrder()));
        sb.append("\r\n title: " + item.getTitle());
        tv.setText(sb.toString());
        return super.onOptionsItemSelected(item);
    }

}
