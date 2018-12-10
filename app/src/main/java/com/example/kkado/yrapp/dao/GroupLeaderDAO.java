package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.entity.GroupLeader;
import com.example.kkado.yrapp.helper.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupLeaderDAO {
    private final String TABLE = "GroupLeader";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**
     * @param myContext
     */
    public GroupLeaderDAO(Context myContext) {
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
    public boolean save(GroupLeader item) {
        ContentValues cv = new ContentValues();

        cv.put("groupName", item.getGroupName());
        cv.put("idPersonLeader", item.getIdPersonLeader());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));


        if (item.getIdGroupLeader() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idGroupLeader=?", new String[]{item.getIdGroupLeader() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     *
     * @param item
     * @return
     */
    public long saveGroupLeader(GroupLeader item) {
        ContentValues cv = new ContentValues();

        cv.put("idGroupLeader", item.getIdGroupLeader());
        cv.put("groupName", item.getGroupName());
        cv.put("idPersonLeader", item.getIdPersonLeader());
        cv.put("initialDate", Util.ConvertDateToString(item.getInitialDate()));
        cv.put("finalDate", Util.ConvertDateToString(item.getFinalDate()));


        if (item.getIdGroupLeader() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idGroupLeader=?", new String[]{item.getIdGroupLeader() + ""}) ;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) ;
    }

    /**
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idPeriod=?", new String[]{id + ""}) > 0;
    }

    /**
     * @return
     * @throws Exception
     */
    public List<GroupLeader> select() throws Exception {
        List<GroupLeader> groupLeaderList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM GroupLeader", null);

        while (cursor.moveToNext()) {
            int idGroupLeader = cursor.getInt(cursor.getColumnIndex("idGroupLeader"));
            String groupName = cursor.getString(cursor.getColumnIndex("groupName"));
            int idPersonLeader = cursor.getInt(cursor.getColumnIndex("idPersonLeader"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));

            groupLeaderList.add(new GroupLeader(idGroupLeader, groupName, idPersonLeader, initialDate, finalDate));
        }
        cursor.close();

        return groupLeaderList;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public GroupLeader selectId(long id) throws Exception {
        GroupLeader groupLeader = null;

        String[] params = new String[]{String.valueOf(id)};
        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM GroupLeader WHERE idCompetition = ? ", params);

        while (cursor.moveToNext()) {
            int idGroupLeader = cursor.getInt(cursor.getColumnIndex("idGroupLeader"));
            String groupName = cursor.getString(cursor.getColumnIndex("groupName"));
            int idPersonLeader = cursor.getInt(cursor.getColumnIndex("idPersonLeader"));
            Date initialDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("initialDate")));
            Date finalDate = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("finalDate")));

            groupLeader = new GroupLeader(idGroupLeader, groupName, idPersonLeader, initialDate, finalDate);
        }
        cursor.close();

        return groupLeader;
    }
}
