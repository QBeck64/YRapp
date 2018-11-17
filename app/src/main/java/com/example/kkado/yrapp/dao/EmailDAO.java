package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypeEmail;
import com.example.kkado.yrapp.entity.Email;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailDAO {
    private final String TABLE = "Email";
    private DbGateway gw;

    public EmailDAO(Context ctx) {
        gw = DbGateway.getInstance(ctx);
    }

    private int idEmail;
    private TypeEmail type;
    private String address;
    private Person person;

    /**
     * @param email
     * @return
     */
    public boolean save(Email email) {
        ContentValues cv = new ContentValues();
        cv.put("type", email.getType().hashCode());
        cv.put("address", email.getAddress());
        cv.put("personId", email.getPerson().getIdPerson());

        if (email.getIdEmail() > 0)
            return gw.getDatabase().update(TABLE, cv, "idEmail=?", new String[]{email.getIdEmail() + ""}) > 0;
        else
            return gw.getDatabase().insert(TABLE, null, cv) > 0;
    }

    public boolean delete(int id) {
        return gw.getDatabase().delete(TABLE, "idPerson=?", new String[]{id + ""}) > 0;
    }

    /**
     * @return
     * @throws Exception
     */
    public List<Person> select() throws Exception {
        List<Person> PersonList = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Person", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPerson"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String surname = cursor.getString(cursor.getColumnIndex("surname"));
            Date birthday = Util.ConvertStringToDate(cursor.getString(cursor.getColumnIndex("birthday")));
            Gender gender = Gender.getGenderDescription(cursor.getString(cursor.getColumnIndex("gender")));

            PersonList.add(new Person(id, name, surname, birthday, gender));
        }
        cursor.close();
        return PersonList;
    }

}
