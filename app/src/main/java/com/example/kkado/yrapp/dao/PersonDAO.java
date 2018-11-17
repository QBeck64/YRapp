package com.example.kkado.yrapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDAO {
    private final String TABLE = "Person";
    private DbGateway gw;

    public PersonDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    /**
     *
     * @param person
     * @return
     */
    public boolean save(Person person){
        ContentValues cv = new ContentValues();
        cv.put("name", person.getName());
        cv.put("surname", person.getSurname());
        cv.put("birthday", Util.ConvertDateToString(person.getBirthday()));
        cv.put("gender", person.getGender().hashCode());

        if(person.getIdPerson() > 0)
            return gw.getDatabase().update(TABLE, cv, "idPerson=?", new String[]{ person.getIdPerson() + "" }) > 0;
        else
            return gw.getDatabase().insert(TABLE, null, cv) > 0;
    }

    public boolean delete(int id){
        return gw.getDatabase().delete(TABLE, "idPerson=?", new String[]{ id + "" }) > 0;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public List<Person> select() throws Exception {
        List<Person> personList = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Person", null);
        while(cursor.moveToNext()){
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
