package zerodev.p0411_layoutinflaterlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String[] name = {"Ivan","Maria","Petr","Anton","Dasha","Boris","Kostya","Igor"};
    String[] position = {"Programmer","buhgalter","Programmer","Programmer","Buhgalter","Director","Programmer","Security"};
    int[] salary = {1300,1000,1300,1300,1000,1500,1300,8000};

    int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        for (int i = 0; i < name.length; i++) {
            Log.d("myLogs","i = " + i);
            View item = ltInflater.inflate(R.layout.activity_item,linearLayout,false);

            TextView tvName = (TextView)item.findViewById(R.id.tvName);
            tvName.setText(name[i]);

            TextView tvPosition = (TextView)item.findViewById(R.id.tvPosition);
            tvPosition.setText("Dolzhnost: " + position[i]);

            TextView tvSalary = (TextView)item.findViewById(R.id.tvSalary);
            tvSalary.setText("Oklad: " + salary[i]);

            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linearLayout.addView(item);
        }
    }
}
