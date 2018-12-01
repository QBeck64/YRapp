package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

public class Contact_View extends AppCompatActivity {
    static final String TAG = "ContactView";

    Integer idPerson;
    Person personView = new Person();
    Address addressView = new Address();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__view);

        // Get/set intent which started the activity
        Intent intent = getIntent();
        idPerson = intent.getIntExtra("PersonId", 0);
        Log.d(TAG, "ID number is " + idPerson);

        try {
            personView = getPersonView(idPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            addressView = getAddressView(idPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView mText = (TextView) findViewById(R.id.firstName);
        mText.setText("");
    }

    private Person getPersonView(Integer id) throws Exception {
        // Create PersonDAO
        PersonDAO dao = new PersonDAO(this);

        return dao.selectId(id);
    }

    private Address getAddressView(Integer id) throws Exception {
        // create AddressDAO
        AddressDAO dao = new AddressDAO(this);

        return dao.selectId(id);
    }
}
