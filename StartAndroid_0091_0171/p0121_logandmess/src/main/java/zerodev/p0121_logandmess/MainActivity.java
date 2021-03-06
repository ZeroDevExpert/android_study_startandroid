package zerodev.p0121_logandmess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    private static final String TAG = "MyLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"находим все View-елементы");
        tvOut = (TextView)findViewById(R.id.tvOut);
        btnOk = (Button)findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        Log.d(TAG,"присваиваем обработчик кнопкам");
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Log.d(TAG,"find all elements by id");
        switch (v.getId()){
            case R.id.btnOk:
                Log.d(TAG,"OK button");
                tvOut.setText("Pressed OK");
                Toast.makeText(this,"Нажата кнопка ОК",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCancel:
                Log.d(TAG,"CANCEL button");
                tvOut.setText("Pressed CANCEL");
                Toast.makeText(this,"Нажата кнопка CANCEL",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
