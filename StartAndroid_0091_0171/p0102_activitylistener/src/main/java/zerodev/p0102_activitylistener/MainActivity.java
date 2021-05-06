package zerodev.p0102_activitylistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    TextView text;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.tvOut);
        btnOk = (Button)findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }
    public void onClick(View v){
     switch (v.getId()){
         case R.id.btnOk:
             text.setText("Pressed OK");
             break;
         case R.id.btnCancel:
             text.setText("Pressed Cancel");
             break;
     }
    }
}
