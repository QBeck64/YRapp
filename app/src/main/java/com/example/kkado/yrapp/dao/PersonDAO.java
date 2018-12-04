package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypePerson;
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
        cv.put("level", person.getLevel());
        cv.put("email", person.getEmail());
        cv.put("phoneNumber", person.getPhoneNumber());
        cv.put("idPersonParent", person.getIdPersonParent());
        cv.put("type", person.getType().getDescription());

        if (person.getIdPerson() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idPerson=?", new String[]{person.getIdPerson() + ""}) > 0;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv) > 0;
    }

    /**
     *
     * @param person
     * @return
     */
    public long savePerson(Person person) {
        ContentValues cv = new ContentValues();

        cv.put("name", person.getName());
        cv.put("surname", person.getSurname());
        cv.put("birthday", Util.ConvertDateToString(person.getBirthday()));
        cv.put("gender", person.getGender().getId());
        cv.put("level", person.getLevel());
        cv.put("email", person.getEmail());
        cv.put("phoneNumber", person.getPhoneNumber());
        cv.put("idPersonParent", person.getIdPersonParent());
        cv.put("type", person.getType().getDescription());

        if (person.getIdPerson() > 0)
            return mySQLiteDatabase.update(TABLE, cv, "idPerson=?", new String[]{person.getIdPerson() + ""}) ;
        else
            return mySQLiteDatabase.insert(TABLE, null, cv);
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

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Person ORDER BY surname DESC", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPerson"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String surname = cursor.getString(cursor.getColumnIndex("surname"));
            Date birthday = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("birthday")));
            Gender gender = Gender.getGenderDescription(cursor.getString(cursor.getColumnIndex("gender")));
            int level = cursor.getInt(cursor.getColumnIndex("level"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            int idPersonParent = cursor.getInt(cursor.getColumnIndex("idPersonParent"));
            TypePerson type = TypePerson.getTypePersonDescription(cursor.getString(cursor.getColumnIndex("type")));

            personList.add(new Person(id, name, surname, birthday, gender, level, email, phoneNumber, idPersonParent, type));
        }
        cursor.close();

        return personList;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Person selectId(long id) throws Exception {
        Person person = null;

        String[] params = new String[]{String.valueOf(id)};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Person WHERE idPerson = ? ", params);

        while (cursor.moveToNext()) {
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String surname = cursor.getString(cursor.getColumnIndex("surname"));
            Date birthday = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("birthday")));
            Gender gender = Gender.getGenderDescription(cursor.getString(cursor.getColumnIndex("gender")));
            int level = cursor.getInt(cursor.getColumnIndex("level"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            int idPersonParent = cursor.getInt(cursor.getColumnIndex("idPersonParent"));
            TypePerson type = TypePerson.getTypePersonDescription(cursor.getString(cursor.getColumnIndex("type")));

            person = new Person(idPerson, name, surname, birthday, gender, level, email, phoneNumber, idPersonParent, type);
        }
        cursor.close();

        return person;
    }

    public List<Person> selectName(String pName) throws Exception {
        List<Person> personList = new ArrayList<>();
        Person person = null;

        String[] params = new String[]{pName};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Person WHERE name = ? ", params);

        while (cursor.moveToNext()) {
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String surname = cursor.getString(cursor.getColumnIndex("surname"));
            Date birthday = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("birthday")));
            Gender gender = Gender.getGenderDescription(cursor.getString(cursor.getColumnIndex("gender")));
            int level = cursor.getInt(cursor.getColumnIndex("level"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            int idPersonParent = cursor.getInt(cursor.getColumnIndex("idPersonParent"));
            TypePerson type = TypePerson.getTypePersonDescription(cursor.getString(cursor.getColumnIndex("type")));

            person = new Person(idPerson, name, surname, birthday, gender, level, email, phoneNumber, idPersonParent, type);
            personList.add(person);
        }
        cursor.close();

        return personList;
    }

    public List<Person> selectFullName(String pName, String pSurname) throws Exception {
        List<Person> personList = new ArrayList<>();
        Person person = null;

        String[] params = new String[]{pName, pSurname};

        Cursor cursor = mySQLiteDatabase.rawQuery("SELECT * FROM Person WHERE name = ? AND surname = ?", params);

        while (cursor.moveToNext()) {
            int idPerson = cursor.getInt(cursor.getColumnIndex("idPerson"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String surname = cursor.getString(cursor.getColumnIndex("surname"));
            Date birthday = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("birthday")));
            Gender gender = Gender.getGenderDescription(cursor.getString(cursor.getColumnIndex("gender")));
            int level = cursor.getInt(cursor.getColumnIndex("level"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            int idPersonParent = cursor.getInt(cursor.getColumnIndex("idPersonParent"));
            TypePerson type = TypePerson.getTypePersonDescription(cursor.getString(cursor.getColumnIndex("type")));

            person = new Person(idPerson, name, surname, birthday, gender, level, email, phoneNumber, idPersonParent, type);
            personList.add(person);
        }
        cursor.close();

        return personList;
    }
}
