package zerodev.p0191_simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    EditText et1,et2;
    Button btnAdd,btnSub,btnMul,btnDiv;
    TextView tvResult;
    String oper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.etNum1);
        et2 = (EditText)findViewById(R.id.etNum2);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMul = (Button)findViewById(R.id.btnMul);
        btnDiv = (Button)findViewById(R.id.btnDiv);

        tvResult = (TextView)findViewById(R.id.tvResult);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        float num1 = 0;
        float num2 = 0;
        float result = 0;
        if(TextUtils.isEmpty(et1.getText().toString()) || TextUtils.isEmpty(et2.getText().toString())){
            return;
        }

        num1 = Float.parseFloat(et1.getText().toString());
        num2 = Float.parseFloat(et2.getText().toString());
        switch(v.getId()){
            case R.id.btnAdd:
                oper = "+";
                result = num1 + num2;
                break;
            case R.id.btnSub:
                oper = "-";
                result = num1 - num2;
                break;
            case R.id.btnMul:
                oper = "*";
                result = num1*num2;
                break;
            case R.id.btnDiv:
                oper = "/";
                if(num2 == 0){
                    Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1/num2;
                break;
            default:
                break;
        }
        tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,MENU_RESET_ID,0,"RESET");
        menu.add(0,MENU_QUIT_ID,0,"QUIT");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case MENU_RESET_ID:
                et1.setText("");
                et2.setText("");
                tvResult.setText("");
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
