package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.entity.CompetitionParticipant;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionParticipantDAO {

    private final String TABLE = "CompetitionParticipant";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;
    private Context myContext;

    /**
     * CompetitionParticipantDAO uses data base to iniciate getting information
     *
     * @param myContext
     */
    public CompetitionParticipantDAO(Context myContext) {
        initializeDataBase(myContext);
        this.myContext = myContext;
    }

    /**
     * CompetitionParticipantDAO integrates data to the data base, using exceptions and invocating SQLite
     *
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
     * CompetitionParticipantDAO using  boolean integrates information and updates respective tables.
     *
     * @param item
     * @return
     */
    public boolean save(CompetitionParticipant item) {
        ContentValues cv = new ContentValues();

        cv.put("idCompetition", item.getIdCompetition());
        cv.put("idParticipant", item.getIdParticipant());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));
        cv.put("prizeGiven", item.isPrizeGiven());


        if (item.getIdCompetition() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idCompetitionParticipant=?", new String[]{item.getIdCompetitionParticipant() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     * CompetitionParticipantDAO uses Long to update and save Id's in the data base
     *
     * @param item
     * @return
     */
    public long saveCompetitionParticipant(CompetitionParticipant item) {
        ContentValues cv = new ContentValues();

        cv.put("idCompetitionParticipant", item.getIdCompetitionParticipant());
        cv.put("idCompetition", item.getIdCompetition());
        cv.put("idParticipant", item.getIdParticipant());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));
        cv.put("prizeGiven", item.isPrizeGiven());


        if (item.getIdCompetition() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idCompetitionParticipant=?", new String[]{item.getIdCompetitionParticipant() + ""});
        else
            return mySQLiteDatabase.insert(TABLE, null, cv);
    }

    /**
     * Boolean contrinutes to delition of information fromdatabase
     *
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idCompetitionParticipant=?", new String[]{id + ""}) > 0;
    }

    /**
     * CompetitionParticipantDAO integrates participants list to database
     *
     * @return
     * @throws Exception
     */
    public List<CompetitionParticipant> select() throws Exception {
        List<CompetitionParticipant> competitionParticipantList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Competition", null);

        while (cursor.moveToNext()) {
            int idCompetitionParticipant = cursor.getInt(cursor.getColumnIndex("idCompetitionParticipant"));
            int idParticipant = cursor.getInt(cursor.getColumnIndex("idParticipant"));
            int idCompetition = cursor.getInt(cursor.getColumnIndex("idCompetition"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));
            boolean prizeGiven = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("prizeGiven")));

            PersonDAO personDAO = new PersonDAO(myContext);
            Person person = personDAO.selectId(idParticipant);

            CompetitionDAO competitionDAO = new CompetitionDAO(myContext);
            Competition competition = competitionDAO.selectId(idCompetition);

            competitionParticipantList.add(new CompetitionParticipant(idCompetitionParticipant, idParticipant, idCompetition, initialDate, finalDate, prizeGiven, person, competition));
        }
        cursor.close();

        return competitionParticipantList;
    }

    /**
     * CCompetition pulls infromation using queries and strings from CompetitionParticipantDAO
     *
     * @param id
     * @return
     * @throws Exception
     */
    public CompetitionParticipant selectId(long id) throws Exception {
        CompetitionParticipant competitionParticipant = null;

        String[] params = new String[]{String.valueOf(id)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Competition WHERE idCompetitionParticipant = ? ", params);

        while (cursor.moveToNext()) {
            int idCompetitionParticipant = cursor.getInt(cursor.getColumnIndex("idCompetitionParticipant"));
            int idParticipant = cursor.getInt(cursor.getColumnIndex("idParticipant"));
            int idCompetition = cursor.getInt(cursor.getColumnIndex("idCompetition"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));
            boolean prizeGiven = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("prizeGiven")));

            PersonDAO personDAO = new PersonDAO(myContext);
            Person person = personDAO.selectId(idParticipant);

            CompetitionDAO competitionDAO = new CompetitionDAO(myContext);
            Competition competition = competitionDAO.selectId(idCompetition);

            competitionParticipant = new CompetitionParticipant(idCompetitionParticipant, idParticipant, idCompetition, initialDate, finalDate, prizeGiven, person, competition);
        }
        cursor.close();

        return competitionParticipant;
    }

    public CompetitionParticipant selectPersonId(long idPerson) throws Exception {
        CompetitionParticipant competitionParticipant = null;

        String[] params = new String[]{String.valueOf(idPerson)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM CompetitionParticipant WHERE idParticipante = ? ", params);

        while (cursor.moveToNext()) {
            int idCompetitionParticipant = cursor.getInt(cursor.getColumnIndex("idCompetitionParticipant"));
            int idParticipant = cursor.getInt(cursor.getColumnIndex("idParticipant"));
            int idCompetition = cursor.getInt(cursor.getColumnIndex("idCompetition"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));
            boolean prizeGiven = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("prizeGiven")));

            PersonDAO personDAO = new PersonDAO(myContext);
            Person person = personDAO.selectId(idParticipant);

            CompetitionDAO competitionDAO = new CompetitionDAO(myContext);
            Competition competition = competitionDAO.selectId(idCompetition);

            competitionParticipant = new CompetitionParticipant(idCompetitionParticipant, idParticipant, idCompetition, initialDate, finalDate, prizeGiven, person, competition);
        }
        cursor.close();

        return competitionParticipant;
    }

}
