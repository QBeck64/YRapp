package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Contact_Book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__book);
    }

    public void onButtonClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), Contact_Add.class);
        startActivity(myIntent);
    }
}
