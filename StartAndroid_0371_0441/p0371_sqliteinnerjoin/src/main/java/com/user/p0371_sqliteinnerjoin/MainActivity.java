package com.user.p0371_sqliteinnerjoin;

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

    // table 1
    int[] position_id = {1,2,3,4};
    String[] position_name = {"Director","Programer","Buhgalter","Ohrana"};
    int[] position_salary = {15000,13000,10000,8000};
    // table 2
    String[] people_name = {"Ivan","Maria","Petr","Anton","Dasha","Boris","Kostja","Igor"};
    int[] people_posid = {2,3,2,2,3,1,2,4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connect to bd
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        //cursor
        Cursor c;
        //info by vacancy
        Log.d(LOG_TAG,"---Table position---");
        c = db.query("position",null,null,null,null,null,null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG,"--- ---");

        //info by people
        Log.d(LOG_TAG,"---Table people---");
        c = db.query("people",null,null,null,null,null,null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG,"--- ---");

        //info with concatenate people and vacancy table
        //rawQuery
        Log.d(LOG_TAG,"---INNER JOIN with rawQuery---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                    + "from people as PL "
                    + "inner join position as PS "
                    + "on PL.posid = PS.id "
                    + "where salary > ?";
        c = db.rawQuery(sqlQuery,new String[]{"12000"});
        logCursor(c);
        c.close();
        Log.d(LOG_TAG,"--- ---");

        //info with concatenate people and vacancy table
        //query
        Log.d(LOG_TAG,"---INNER JOIN with query---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String columns[] = {"PL.name as Name","PS.name as Position","salary as Salary"};
        String selection = "salary < ?";
        String[] selectionArg = {"12000"};
        c = db.query(table,columns,selection,selectionArg,null,null,null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG,"--- ---");
        dbh.close();
    }

    void logCursor(Cursor c){
        if(c != null){
            if(c.moveToFirst()){
                String str;
                do{
                    str = "";
                    for(String cn: c.getColumnNames()){
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG,str);
                }while (c.moveToNext());
            }
        }else{
            Log.d(LOG_TAG,"Cursor is null");
        }
    }

    class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context){
            super(context,"myDb",null,1);
        }
        public void onCreate(SQLiteDatabase db){
            Log.d(LOG_TAG,"-----onCreate db-----");
            ContentValues cv = new ContentValues();
            //create vacancy db
            db.execSQL("create table position (id integer primary key, name text, salary integer);");
            //fill vacancy db
            for(int i=0;i<position_id.length;i++){
                cv.clear();
                cv.put("id",position_id[i]);
                cv.put("name",position_name[i]);
                cv.put("salary",position_salary[i]);
                db.insert("position",null,cv);
            }
            //create people db
            db.execSQL("create table people (id integer primary key autoincrement,name text, posid integer);");
            //fill people db
            for(int i=0;i < people_name.length;i++){
                cv.clear();
                cv.put("name",people_name[i]);
                cv.put("posid",people_posid[i]);
                db.insert("people",null,cv);
            }
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){

        }
    }
}
