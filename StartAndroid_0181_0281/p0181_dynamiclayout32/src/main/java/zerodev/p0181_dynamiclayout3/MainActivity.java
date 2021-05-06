package zerodev.p0181_dynamiclayout3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    SeekBar sb;
    Button btn1,btn2;
    LinearLayout.LayoutParams lParams1,lParams2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find all elements
        sb = (SeekBar)findViewById(R.id.sbWeight);
        sb.setOnSeekBarChangeListener(this);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        lParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
        lParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        //int leftValue = progress;
        //int rightValue = seekBar.getMax() - progress;
        lParams1.weight = progress;
        lParams2.weight = seekBar.getMax() - progress;
        btn1.setText(String.valueOf(progress));
        btn2.setText(String.valueOf(seekBar.getMax() - progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar){

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){

    }
}
