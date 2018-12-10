package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.helper.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionDAO {
    private final String TABLE = "Competition";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**
     * @param myContext
     */
    public CompetitionDAO(Context myContext) {
        initializeDataBase(myContext);
    }

    /**
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

    /**
     * @param item
     * @return
     */
    public boolean save(Competition item) {
        ContentValues cv = new ContentValues();
        cv.put("idCompetition", item.getIdCompetition());
        cv.put("description", item.getDescription());
        cv.put("goal", item.getGoal());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));


        if (item.getIdCompetition() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idCompetition=?", new String[]{item.getIdCompetition() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     *
     * @param item
     * @return
     */
    public long saveCompetition(Competition item) {
        ContentValues cv = new ContentValues();
        cv.put("description", item.getDescription());
        cv.put("goal", item.getGoal());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));


        if (item.getIdCompetition() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idCompetition=?", new String[]{item.getIdCompetition() + ""});
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) ;
    }

    /**
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idCompetition=?", new String[]{id + ""}) > 0;
    }

    /**
     * @return
     * @throws Exception
     */
    public List<Competition> select() throws Exception {
        List<Competition> competitionList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Competition", null);

        while (cursor.moveToNext()) {
            int idCompetition = cursor.getInt(cursor.getColumnIndex("idCompetition"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String goal = cursor.getString(cursor.getColumnIndex("goal"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));


            competitionList.add(new Competition(idCompetition, description, goal, initialDate, finalDate));
        }
        cursor.close();

        return competitionList;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Competition selectId(long id) throws Exception {
        Competition competition = null;
        String[] params = new String[]{String.valueOf(id)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Competition WHERE idCompetition = ? ", params);

        while (cursor.moveToNext()) {
            int idCompetition = cursor.getInt(cursor.getColumnIndex("idCompetition"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String goal = cursor.getString(cursor.getColumnIndex("goal"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));


            competition = new Competition(idCompetition, description, goal, initialDate, finalDate);
        }
        cursor.close();

        return competition;
    }

}
