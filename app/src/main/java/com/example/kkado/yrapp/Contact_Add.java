package com.example.kkado.yrapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kkado.yrapp.entity.Person;

public class Contact_Add extends AppCompatActivity {
    EditText edit1, edit2, edit3;
    Button b1, b2;

    public static final String MyPREFERENCES = "myPrefs";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__add);

        // Create new "person" object
        final Person newContact = new Person();

        edit1= (EditText)findViewById(R.id.firstName);

        b1 = (Button)findViewById(R.id.floatingActionButton2);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newContact.setName(edit1.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();


            }
        }) ;
    }
}
