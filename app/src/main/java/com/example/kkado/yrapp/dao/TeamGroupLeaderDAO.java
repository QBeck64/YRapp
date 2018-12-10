package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.entity.TeamGroupLeader;
import com.example.kkado.yrapp.helper.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamGroupLeaderDAO {
    private final String TABLE = "TeamGroupLeader";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**
     * @param myContext
     */
    public TeamGroupLeaderDAO(Context myContext) {
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
    public boolean save(TeamGroupLeader item) {
        ContentValues cv = new ContentValues();

        cv.put("idPerson", item.getIdPerson());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));

        if (item.getIdTeamGroupLeader() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idTeamGroupLeader=?", new String[]{item.getIdTeamGroupLeader() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     *
     * @param item
     * @return
     */
    public long saveTeamGroupLeader(TeamGroupLeader item) {
        ContentValues cv = new ContentValues();

        cv.put("idTeamGroupLeader", item.getIdTeamGroupLeader());
        cv.put("idPerson", item.getIdPerson());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));

        if (item.getIdTeamGroupLeader() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idTeamGroupLeader=?", new String[]{item.getIdTeamGroupLeader() + ""}) ;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) ;
    }

    /**
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idTeamGroupLeader=?", new String[]{id + ""}) > 0;
    }

    /**
     * @return
     * @throws Exception
     */
    public List<TeamGroupLeader> select() throws Exception {
        List<TeamGroupLeader> teamGroupLeaderList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Invoicing", null);

        while (cursor.moveToNext()) {
            int idTeamGroupLeader = cursor.getInt(cursor.getColumnIndex("idTeamGroupLeader"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));

            teamGroupLeaderList.add(new TeamGroupLeader(idTeamGroupLeader, initialDate, finalDate, idPerson));
        }
        cursor.close();

        return teamGroupLeaderList;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public TeamGroupLeader selectId(long id) throws Exception {
        TeamGroupLeader teamGroupLeader = null;

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Invoicing", null);

        while (cursor.moveToNext()) {
            int idTeamGroupLeader = cursor.getInt(cursor.getColumnIndex("idTeamGroupLeader"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));

            teamGroupLeader = new TeamGroupLeader(idTeamGroupLeader, initialDate, finalDate, idPerson);
        }
        cursor.close();

        return teamGroupLeader;
    }
}
