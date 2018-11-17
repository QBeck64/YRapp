package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDAO {
    private final String TABLE = "Person";
    private SqliteAdapter dbHelper;
    private SQLiteDatabase mySQLiteDatabase;

    /**
     * @param myContext
     */
    public PersonDAO(Context myContext) {
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
     * @param person
     * @return
     */
    public boolean save(Person person) {
        ContentValues cv = new ContentValues();
        cv.put("name", person.getName());
        cv.put("surname", person.getSurname());
        cv.put("birthday", Util.ConvertDateToString(person.getBirthday()));
        cv.put("gender", person.getGender().getId());

        if (person.getIdPerson() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idPerson=?", new String[]{person.getIdPerson() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return mySQLiteDatabase.delete(TABLE, "idPerson=?", new String[]{id + ""}) > 0;
    }

    /**
     * @return
     * @throws Exception
     */
    public List<Person> select() throws Exception {
        List<Person> personList = new ArrayList<>();

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Person", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPerson"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String surname = cursor.getString(cursor.getColumnIndex("surname"));
            Date birthday = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("birthday")));
            Gender gender = Gender.getGenderDescription(cursor.getString(cursor.getColumnIndex("gender")));

            personList.add(new Person(id, name, surname, birthday, gender));
        }
        cursor.close();

        return personList;
    }
}
