package com.user.p0381_sqlitetransaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG,"---- onCreate Activity -----");
        dbHelper = new DBHelper(this);
        myActions();
    }

    void myActions(){
        try{
            db = dbHelper.getWritableDatabase();
            delete(db,"mytable");
            db.beginTransaction();
            insert(db,"mytable","val1");

    /*        Log.d(LOG_TAG,"create DBHELPER");
            DBHelper dbh2 = new DBHelper(this);
            Log.d(LOG_TAG,"get_db");
            SQLiteDatabase db2 = dbh2.getWritableDatabase();
            read(db2,"mytable");
            dbh2.close();
*/
            insert(db,"mytable","val3");
            read(db,"mytable");
            db.setTransactionSuccessful();
        }catch(Exception e){
            Log.d(LOG_TAG,e.getClass() + " error: " + e.getMessage());
        }finally {
            db.endTransaction();
            dbHelper.close();
        }
    }

    void insert(SQLiteDatabase db, String table, String value){
        Log.d(LOG_TAG,"---- Insert in table " + table + " value = " + value);
        ContentValues cv = new ContentValues();
        cv.put("val",value);
        db.insert("mytable",null,cv);
    }
    void read(SQLiteDatabase db, String table){
        Log.d(LOG_TAG,"---- Read table " + table);
        Cursor c = db.query(table,null,null,null,null,null,null);
        if(c != null){
            Log.d(LOG_TAG,"---- Record count = " + c.getCount());
            if(c.moveToFirst()){
                do{
                    Log.d(LOG_TAG,c.getString(c.getColumnIndex("val")));
                } while(c.moveToNext());
            }
            c.close();
        }
    }
    void delete(SQLiteDatabase db, String table){
        Log.d(LOG_TAG,"Delete all from  table " + table);
        db.delete(table,null,null);
    }

    class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context){
            super(context, "myDb", null,1);
        }
        public void onCreate(SQLiteDatabase db){
            Log.d(LOG_TAG,"----onCreate database----");
            db.execSQL("create table mytable (id integer primary key autoincrement, val text);");
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        }
    }
}
