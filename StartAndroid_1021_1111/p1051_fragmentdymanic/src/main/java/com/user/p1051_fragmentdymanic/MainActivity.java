package com.user.p1051_fragmentdymanic;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    Fragment1 frag1;
    Fragment2 frag2;
    FragmentTransaction fTrans;
    CheckBox chbStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new Fragment1();
        frag2 = new Fragment2();
        chbStack = (CheckBox)findViewById(R.id.chbStack);
    }

    public void onclick(View v){
        fTrans = getFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.btnAdd:
                fTrans.add(R.id.frgmCont, frag1);
                fTrans.add(R.id.frgmCont, frag2);
                break;
            case R.id.btnRemove:
                fTrans.remove(frag1);
                fTrans.remove(frag2);
                break;
            case R.id.btnReplace:
                fTrans.replace(R.id.frgmCont,frag2);
                break;
            default:
                break;
        }
        if(chbStack.isChecked()) fTrans.addToBackStack(null);
        fTrans.commit();
    }
}
