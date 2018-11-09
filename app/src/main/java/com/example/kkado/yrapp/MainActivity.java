package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Making commit for team 2nd change Elisangela
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, TemplateActivity.class);
        startActivity(intent);
    }
}
