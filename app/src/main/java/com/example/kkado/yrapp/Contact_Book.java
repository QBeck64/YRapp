package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class Contact_Book extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final static String TAG = "ContactBook";

    private ListView contactView;
    private List<Person> contacts = new ArrayList<>();
    private ArrayAdapter<Person> arrayAdapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__book);

        // Initialize resources
        contactView = (ListView)findViewById(R.id.contactView);

        try {
            initializeContactList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeContactList() throws Exception {
        PersonDAO dao = new PersonDAO(this);
        contacts.clear();
        contacts = dao.select();

        arrayAdapterContact = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, contacts);
        contactView.setAdapter(arrayAdapterContact);
        contactView.setOnItemClickListener(this);
    }
    public void createContact(View v) {
        Intent myIntent = new Intent(this, Contact_Add.class);
        startActivity(myIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "Hey you clicked me, stop it!!");
        Person newPP = (Person) parent.getItemAtPosition(position);

        Log.d(TAG, "Person name is " + newPP.getName());
    }
}
