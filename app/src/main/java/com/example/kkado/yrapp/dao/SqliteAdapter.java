package com.example.kkado.yrapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 */
public class SqliteAdapter extends SQLiteOpenHelper {
public static final String DATABASE_NAME = "db_yrapp";
public static String LOCALDB="/data/data/com.example.kkado.yrapp/datadases/";
public static final int DATABASE_VERSION=1;
private Context myContext;
private SQLiteDatabase mySQLiteDatabase;


    /**
     *
     * @param context
     */
    public SqliteAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     *
     */
    public void openDataBase(){
        String dbPath = myContext.getDatabasePath(DATABASE_NAME).getPath();
        if(mySQLiteDatabase!=null && mySQLiteDatabase.isOpen()){
            return;
        }
        mySQLiteDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     *
     */
    public void closeDataBase(){

        if(mySQLiteDatabase!=null){
            mySQLiteDatabase.close();
        }
    }


}
