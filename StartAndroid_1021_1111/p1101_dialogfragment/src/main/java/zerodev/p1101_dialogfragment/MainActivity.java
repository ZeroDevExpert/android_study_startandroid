package zerodev.p1101_dialogfragment;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DialogFragment dlg1;
    DialogFragment dlg2;
    DialogFragment dlg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlg1 = new Dialog1();
        dlg2 = new Dialog2();
        dlg3 = new Dialog3();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnDlg1:
                dlg1.show(getFragmentManager(), "dlg1");
                break;
            case R.id.btnDlg2:
                dlg2.show(getFragmentManager(), "dlg2");
                break;
            case R.id.btnDlg3:
                dlg3.show(getFragmentManager(), "dlg3");
                break;
            default:
                break;
        }
    }
}
