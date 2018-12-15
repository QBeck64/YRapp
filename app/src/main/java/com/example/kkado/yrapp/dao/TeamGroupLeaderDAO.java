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

    /**TeamGroupLeaderDAO access information using data source to identify completition.  
     * @param myContext
     */
    public TeamGroupLeaderDAO(Context myContext) {
        initializeDataBase(myContext);
    }

    /**TeamGroupLeaderDAO iniciates accesing data from different tables. Information gets display with update. 
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

    /**Using CompetitionDAO and the implementation of boolean can get access to specific information related to 
    competition.
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

    /**Long contributes to saveinformation dercribed on SaveCompetition, such as goal
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

    /**Boolean  uses a delete methote to update mySQLiteDatabase if information is deleted. 
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idTeamGroupLeader=?", new String[]{id + ""}) > 0;
    }

    /**CompetitionDAO access list using strings describing goal,description, and completion.
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

    /**SelectId is use as identifier to CompetitionDAO, and accessing data base.
     *
     * @param id
     * @return
     * @throws Exception
     */
    public TeamGroupLeader selectId(long id) throws Exception {
        TeamGroupLeader teamGroupLeader = null;
        String[] params = new String[]{String.valueOf(id)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM TeamGroupLeader WHERE idTeamGroupLeader = ? ", params);

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

    public TeamGroupLeader selectPersonId(long idPersonIndex) throws Exception {
        TeamGroupLeader teamGroupLeader = null;
        String[] params = new String[]{String.valueOf(idPersonIndex)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM TeamGroupLeader WHERE idPerson = ? ", params);


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
