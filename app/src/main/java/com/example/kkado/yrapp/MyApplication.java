package com.example.kkado.yrapp;

import android.app.Application;

import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    List<Person> contactBookList = new ArrayList<>();

    private void initializeContactList() throws Exception {
        PersonDAO dao = new PersonDAO(this);
        // Clear as precautionary
        contactBookList.clear();
        // Fill contactBookLsit with the Person DAO
        contactBookList = dao.select();

    }
}


