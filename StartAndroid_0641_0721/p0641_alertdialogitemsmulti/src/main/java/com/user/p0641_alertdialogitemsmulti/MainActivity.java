package com.user.p0641_alertdialogitemsmulti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    final int DIALOG_ITEMS = 1;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;
    String data[] = {"one", "two", "three", "four"};
    boolean chkd[] = {false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }
    public void onclick(View v){
        switch (v.getId()){
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }
    protected Dialog onCreateDialog(int id){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id){
            case DIALOG_ITEMS:
                adb.setTitle(R.string.itmes);
                adb.setMultiChoiceItems(data,chkd,myItemMultiClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setMultiChoiceItems(cursor,DB.COLUMN_CHK,DB.COLUMN_TXT,myCursorMultiClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok,myBtnClickListener);
        return adb.create();
    }

    DialogInterface.OnMultiChoiceClickListener myItemMultiClickListener = new DialogInterface.OnMultiChoiceClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked){
            Log.d(LOG_TAG,"which = " + which + ", isChecked = " + isChecked);
        }
    };

    DialogInterface.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
            ListView lv = ((AlertDialog)dialogInterface).getListView();
            Log.d(LOG_TAG,"which = " + which + ", isChecked = " + isChecked);
            db.changeRec(which,isChecked);
            cursor.requery();
        }
    };

    Dialog.OnClickListener myBtnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            SparseBooleanArray sbArray = ((AlertDialog)dialogInterface).getListView().getCheckedItemPositions();
            for (int j = 0; j < sbArray.size(); j++) {
                int key = sbArray.keyAt(j);
                if(sbArray.get(key))
                    Log.d("qwe", "checked: " + key);
            }
        }
    };
    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }
}
