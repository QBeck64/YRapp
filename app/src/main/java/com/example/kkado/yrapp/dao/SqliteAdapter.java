package com.example.kkado.yrapp.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kkado.yrapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class SqliteAdapter extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbyrapp.db";
    public static String LOCALDB;
    public static final int DATABASE_VERSION = 1;
    private Context myContext;
    private SQLiteDatabase mySQLiteDatabase;
    private boolean mNeedUpdate = false;

   
    public SqliteAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            LOCALDB = context.getApplicationInfo().dataDir + "/databases/";
        else
            LOCALDB = "/data/data/" + context.getPackageName() + "/databases/";
        this.myContext = context;
        LOCALDB = "/data/data/" + context.getPackageName() + "/databases/";
        copyDataBase();

        this.getReadableDatabase();
    }

    /**SqliteAdapter iniciates accesing data from different tables. Information gets display with update.
     * @throws IOException
     */
    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(LOCALDB + DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    /**SqliteAdapter Using CompetitionDAO and the implementation of boolean can get access to specific information related to 
    competition.
     * @return
     */
    private boolean checkDataBase() {
        File dbFile = new File(LOCALDB + DATABASE_NAME);
        return dbFile.exists();
    }

    /**SqliteAdapter uses copy data base 
     *
     */
    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    /**SqliteAdapter uses copy data base file
     * @throws IOException
     */
    private void copyDBFile() throws IOException {
        // InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
        InputStream mInput = myContext.getResources().openRawResource(R.raw.dbyrapp);
        OutputStream mOutput = new FileOutputStream(LOCALDB + DATABASE_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    /**Boolean uses methote to update openDataBase if information is deleted
     * @return
     * @throws SQLException
     */
    public boolean openDataBase() throws SQLException {
        mySQLiteDatabase = SQLiteDatabase.openDatabase(LOCALDB + DATABASE_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mySQLiteDatabase != null;
    }

    /**SqliteAdapter uses the SQLite toaccess information
     *
     */
    @Override
    public synchronized void close() {
        if (mySQLiteDatabase != null)
            mySQLiteDatabase.close();
        super.close();
    }

    /**SqliteAdapter access SQL data base
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    /**SqliteAdapter and onUpgrade access the data base
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
