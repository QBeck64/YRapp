package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.entity.Invoicing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoicingDAO {
    private final String TABLE = "Invoicing";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**
     * @param myContext
     */
    public InvoicingDAO(Context myContext) {
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
    public boolean save(Invoicing item) {
        ContentValues cv = new ContentValues();

        cv.put("idInvoicing", item.getIdInvoicing());
        cv.put("invoicing", item.getInvoicing());
        cv.put("idPerson", item.getIdPerson());
        cv.put("idPeriod", item.getIdPeriod());


        if (item.getIdInvoicing() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idInvoicing=?", new String[]{item.getIdInvoicing() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     *
     * @param item
     * @return
     */
    public long saveInvoicing(Invoicing item) {
        ContentValues cv = new ContentValues();

        cv.put("idInvoicing", item.getIdInvoicing());
        cv.put("invoicing", item.getInvoicing());
        cv.put("idPerson", item.getIdPerson());
        cv.put("idPeriod", item.getIdPeriod());


        if (item.getIdInvoicing() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idInvoicing=?", new String[]{item.getIdInvoicing() + ""}) ;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) ;
    }

    /**
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idInvoicing=?", new String[]{id + ""}) > 0;
    }

    /**
     * @return
     * @throws Exception
     */
    public List<Invoicing> select() throws Exception {
        List<Invoicing> invoicingList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Invoicing", null);

        while (cursor.moveToNext()) {
            int idInvoicing = cursor.getInt(cursor.getColumnIndex("idInvoicing"));
            float invoicing = cursor.getFloat(cursor.getColumnIndex("invoicing"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            int idPeriod = cursor.getInt(cursor.getColumnIndex("idPeriod"));

            invoicingList.add(new Invoicing(idInvoicing, invoicing, idPerson, idPeriod));
        }
        cursor.close();

        return invoicingList;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Invoicing selectId(long id) throws Exception {
        Invoicing invoicing = null;
        String[] params = new String[]{String.valueOf(id)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Invoicing WHERE idInvoicing = ? ", params);

        while (cursor.moveToNext()) {
            int idInvoicing = cursor.getInt(cursor.getColumnIndex("idInvoicing"));
            float invoice = cursor.getFloat(cursor.getColumnIndex("invoicing"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            int idPeriod = cursor.getInt(cursor.getColumnIndex("idPeriod"));

            invoicing = new Invoicing(idInvoicing, invoice, idPerson, idPeriod);
        }
        cursor.close();

        return invoicing;
    }
}
