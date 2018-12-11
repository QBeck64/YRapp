package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Contact_Edit extends AppCompatActivity {

    Integer idPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__edit);

        // Get/set intent which started the activity
        Intent intent = getIntent();
        idPerson = intent.getIntExtra("PersonId", 0);
    }

    public void returnToContacts() {
        Intent myIntent = new Intent(this, Contact_Book.class);
        startActivity(myIntent);
    }
}
