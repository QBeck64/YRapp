package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.entity.Invoicing;
import com.example.kkado.yrapp.entity.Period;
import com.example.kkado.yrapp.entity.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoicingDAO {
    private final String TABLE = "Invoicing";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    private Context myContext;

    /**InvoicingDAO access information using data source to identify completition.  
     * @param myContext
     */
    public InvoicingDAO(Context myContext) {
        initializeDataBase(myContext);  this.myContext = myContext;
    }

    /**InvoicingDAO iniciates accesing data from different tables. Information gets display with update
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

    /**InvoicingDAO and the implementation of boolean can get access to specific information related to 
    competition. 
     * @param item
     * @return
     */
    public boolean save(Invoicing item) {
        ContentValues cv = new ContentValues();

        cv.put("invoicing", item.getInvoicing());
        cv.put("idPerson", item.getIdPerson());
        cv.put("idPeriod", item.getIdPeriod());


        if (item.getIdInvoicing() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idInvoicing=?", new String[]{item.getIdInvoicing() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**Long contributes to saveinformation dercribed on SaveCompetition, such as goal
     *
     * @param item
     * @return
     */
    public long saveInvoicing(Invoicing item) {
        ContentValues cv = new ContentValues();

        cv.put("invoicing", item.getInvoicing());
        cv.put("idPerson", item.getIdPerson());
        cv.put("idPeriod", item.getIdPeriod());


        if (item.getIdInvoicing() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idInvoicing=?", new String[]{item.getIdInvoicing() + ""}) ;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) ;
    }

    /**Boolean  uses a delete methote to update mySQLiteDatabase if information is deleted
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idInvoicing=?", new String[]{id + ""}) > 0;
    }

    /** access list using strings describing goal,description, and completion.
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

            PersonDAO personDAO = new PersonDAO(myContext);
            Person person = personDAO.selectId(idPerson);

            PeriodDAO periodDAO = new PeriodDAO(myContext);
            Period period = periodDAO.selectId(idPeriod);

            invoicingList.add(new Invoicing(idInvoicing, invoicing, idPerson, idPeriod, period, person));
        }
        cursor.close();

        return invoicingList;
    }

    /**InvoicingDAO SelectId is use as identifier to CompetitionDAO, and accessing data base.
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

            PersonDAO personDAO = new PersonDAO(myContext);
            Person person = personDAO.selectId(idPerson);

            PeriodDAO periodDAO = new PeriodDAO(myContext);
            Period period = periodDAO.selectId(idPeriod);

            invoicing = new Invoicing(idInvoicing, invoice, idPerson, idPeriod, period, person);
        }
        cursor.close();

        return invoicing;
    }

    public Invoicing selectPersonId(long idPersonIndex) throws Exception {
        Invoicing invoicing = null;
        String[] params = new String[]{String.valueOf(idPersonIndex)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Invoicing WHERE idPerson = ? ", params);

        while (cursor.moveToNext()) {
            int idInvoicing = cursor.getInt(cursor.getColumnIndex("idInvoicing"));
            float invoice = cursor.getFloat(cursor.getColumnIndex("invoicing"));
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            int idPeriod = cursor.getInt(cursor.getColumnIndex("idPeriod"));

            PersonDAO personDAO = new PersonDAO(myContext);
            Person person = personDAO.selectId(idPerson);

            PeriodDAO periodDAO = new PeriodDAO(myContext);
            Period period = periodDAO.selectId(idPeriod);

            invoicing = new Invoicing(idInvoicing, invoice, idPerson, idPeriod, period, person);
        }
        cursor.close();

        return invoicing;
    }

}
