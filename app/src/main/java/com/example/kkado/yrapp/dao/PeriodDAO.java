package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.entity.Period;
import com.example.kkado.yrapp.helper.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodDAO {
    private final String TABLE = "Period";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**PeriodDAO access information using data source to identify completition.
     * @param myContext
     */
    public PeriodDAO(Context myContext) {
        initializeDataBase(myContext);
    }

    /**PeriodDAO iniciates accesing data from different tables. Information gets display with update. 
     * @param myContext
     */
    private void initializeDataBase(Context myContext) {
        dbHelper = new SqliteAdapter(myContext);

        try {
            dbHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mySQLiteDatabase = dbHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    /**PeriodDAO  and the implementation of boolean can get access to specific information related to 
    competition. 
     * @param item
     * @return
     */
    public boolean save(Period item) {
        ContentValues cv = new ContentValues();
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));


        if (item.getIdPeriod() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idPeriod=?", new String[]{item.getIdPeriod() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**Long contributes to saveinformation dercribed on SaveCompetition, such as goal
     * @param item
     * @return
     */
    public long savePeriod(Period item) {
        ContentValues cv = new ContentValues();

        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));


        if (item.getIdPeriod() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idPeriod=?", new String[]{item.getIdPeriod() + ""});
        else
            return mySQLiteDatabase.insert(TABLE, null, cv);
    }

    /**Boolean  uses a delete methote to update mySQLiteDatabase if information is deleted. 
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idPeriod=?", new String[]{id + ""}) > 0;
    }

    /**PeriodDAO access list using strings describing goal,description, and completion.
     * @return
     * @throws Exception
     */
    public List<Period> select() throws Exception {
        List<Period> periodList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Period", null);

        while (cursor.moveToNext()) {
            int idPeriod = cursor.getInt(cursor.getColumnIndex("idPeriod"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));


            periodList.add(new Period(idPeriod, initialDate, finalDate));
        }
        cursor.close();

        return periodList;
    }

    /**SelectId is use as identifier to CompetitionDAO, and accessing data base.
     * @param id
     * @return
     * @throws Exception
     */
    public Period selectId(long id) throws Exception {
        Period period = null;
        String[] params = new String[]{String.valueOf(id)};
        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Period WHERE idPeriod = ? ", params);

        while (cursor.moveToNext()) {
            int idPeriod = cursor.getInt(cursor.getColumnIndex("idPeriod"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));


            period = new Period(idPeriod, initialDate, finalDate);
        }
        cursor.close();

        return period;
    }

    /**selectIdPeriod is use as identifier to CompetitionDAO, and accessing data base.
     *
     * @param initialDate
     * @param finalDate
     * @return
     * @throws Exception
     */
    public int selectIdPeriod(Date initialDate, Date finalDate) throws Exception {
        int idPeriod = 0;

        String[] params = new String[]{String.valueOf(Util.ConvertDateToString(initialDate)), String.valueOf(Util.ConvertDateToString(finalDate))};
        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Period WHERE initialDate = ? and finalDate = ?", params);

        while (cursor.moveToNext()) {
            idPeriod = cursor.getInt(cursor.getColumnIndex("idPeriod"));
        }
        cursor.close();

        return idPeriod;
    }

}
