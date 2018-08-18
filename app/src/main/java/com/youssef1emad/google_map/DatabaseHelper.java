/**

package com.youssef1emad.google_map;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.util.ArrayList;

 public class DatabaseHelper extends SQLiteDatabase {
    public final String DBNAME = "stors.db";
    public final String DBLOCATION = Environment.getDataDirectory()+"/data/com.youssef1emad.google_map/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper (Context context){
        super(context,DBNAME,null,1);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db){

    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion , int newVersion){

    }
    public void openDatabase (){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase(){
        if(mDatabase !=null){
            mDatabase.close();
        }
    }
    public ArrayList get_All_Titles(){
        ArrayList arrayList = new ArrayList();
        openDatabase();
        Cursor res = mDatabase.rawQuery("select * from all_stores" ,null);
        res.moveToFirst();
        while (!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex("comment")));
            res.moveToNext();

        }
        res.close();
        closeDatabase();
        return arrayList;
    }
    public String get_full_story (String comment){
        String full_story;
        openDatabase();
        Cursor res = mDatabase.rawQuery("select * from all_stores where comment like'"+comment + "'",null);
                res.moveToFirst();
        full_story = res.getString(res.getColumnIndex("photo"));
        res.close();
        closeDatabase();
        return full_story;
    }

    public DatabaseHelper(Context mContext, SQLiteDatabase mDatabase) {
        super();
        this.mContext = mContext;
        this.mDatabase = mDatabase;
    }
    /*
    DatabaseHelper() {
        super();
    }

}
**/