package com.user.p0281_intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etFName,etLName;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFName = (EditText) findViewById(R.id.etFName);
        etLName = (EditText) findViewById(R.id.etLName);
        btnSubmit = (Button)findViewById(R.id.btnSubmit1);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this,ViewActivity.class);
        intent.putExtra("fname",etFName.getText().toString());
        intent.putExtra("lname",etLName.getText().toString());
        startActivity(intent);
    }
}
