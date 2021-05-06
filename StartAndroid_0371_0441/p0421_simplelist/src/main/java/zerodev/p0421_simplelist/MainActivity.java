package zerodev.p0421_simplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String name[] = {"Ivan","Maria","Petr","Anton","Dasha","Boris","Kostja","Igor","Anna","Denis","Andrey"};
//    String[] name2 = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        for (int i = 0; i < name2.length; i++) {
            name2[i] = name[i] + "<---" + Integer.toString(i);
        }*/

        ListView lvMain = (ListView)findViewById(R.id.lvMain);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.my_list_item,name);
        lvMain.setAdapter(adapter);
    }
}
