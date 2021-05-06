package com.user.p0541_customadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        boxAdapter = new BoxAdapter(this,products);
        ListView lvMain = (ListView)findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
    }

    void fillData(){
        for (int i = 0; i < 20; i++) {
            products.add(new Product("Product " + i,i*1000, R.drawable.ic_launcher_background,false));
        }
    }

    public void showResult(View v) {
        String result = "Tovaru v korzine: ";
        for(Product p : boxAdapter.getBox()){
            if(p.box){
                result += "\n" + p.name;
            }
            Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        }
    }
}
