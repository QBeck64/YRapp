package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.kkado.yrapp.entity.CompetitionParticipant;
import com.example.kkado.yrapp.helper.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompetitionParticipantDAO {

    private final String TABLE = "CompetitionParticipant";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**
     * @param myContext
     */
    public CompetitionParticipantDAO(Context myContext) {
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
    public boolean save(CompetitionParticipant item) {
        ContentValues cv = new ContentValues();

        cv.put("idCompetitionParticipant", item.getIdCompetitionParticipant());
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
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idCompetitionParticipant=?", new String[]{id + ""}) > 0;
    }

    /**
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

            competitionParticipantList.add(new CompetitionParticipant(idCompetitionParticipant, idParticipant, idCompetition, initialDate, finalDate, prizeGiven));
        }
        cursor.close();

        return competitionParticipantList;
    }

}
