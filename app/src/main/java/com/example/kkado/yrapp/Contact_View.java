package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Contact_View extends AppCompatActivity {
    static final String TAG = "ContactView";

    Integer idPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__view);

        // Get/set intent which started the activity
        Intent intent = getIntent();
        idPerson = intent.getIntExtra("PersonId",0 );
        Log.d(TAG, "ID number is " + idPerson);
    }
}
