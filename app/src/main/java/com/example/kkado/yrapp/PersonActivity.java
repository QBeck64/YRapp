package com.example.kkado.yrapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypePerson;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.dao.SqliteAdapter;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

    private ListView lvPerson;

    private List<Person> personList = new ArrayList<Person>();
    private ArrayAdapter<Person> arrayAdapterPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initializeResources();
        try {
            initializePersonList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initializeResources() {
        lvPerson = (ListView) findViewById(R.id.lvPerson);
    }

    private void initializePersonList() throws Exception {
        PersonDAO dao = new PersonDAO(this);
        personList.clear();
        Date data = new Date();
        Person person = new Person(0,"Stefano", "Nicotra", data, Gender.masculine,1,"nic17014@byui.edu", "11999999999", null, TypePerson.Leader );

        long save = dao.savePerson(person);

        if (save>0) {
            alert("Success");
        } else {
            alert("Error");
        }
        Person test = dao.selectId(save);
        personList = dao.select();
        arrayAdapterPerson = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, personList);
        lvPerson.setAdapter(arrayAdapterPerson);
    }

    private void alert(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

}



