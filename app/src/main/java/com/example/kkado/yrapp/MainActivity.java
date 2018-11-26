package com.example.kkado.yrapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kkado.yrapp.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonLis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Making commit for team 2nd change Elisangela
        buttonLis=(Button) findViewById(R.id.buttonLis);
        buttonLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLisActivity();
            }
        });
    }

    private void buttonLisActivity() {
        Intent intent = new Intent(this, PersonActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Contact_Book.class);
        startActivity(intent);
    }

}
