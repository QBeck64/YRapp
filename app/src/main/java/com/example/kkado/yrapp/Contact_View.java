package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypePerson;
import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.util.Date;

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

        // Get/set person
        try {
            personView = getPersonView(idPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get/Set address
        try {
            addressView = getAddressView(idPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "about to display peron");
        // Display Person and Address details
        displayPerson(personView);
        Log.d(TAG, "about to display address");
        displayAddress(addressView);
    }

    public void returnButton(View v) {
        Intent myIntent = new Intent(this, Contact_Book.class);
        startActivity(myIntent);
    }

    private Person getPersonView(Integer id) throws Exception {
        // Create PersonDAO
        PersonDAO dao = new PersonDAO(this);
        return dao.selectId(id);
    }

    private Address getAddressView(Integer id) throws Exception {
        // create AddressDAO
        AddressDAO dao = new AddressDAO(this);
        return dao.selectPersonId(id);
    }

    private void displayPerson(Person personView) {
        // find and set by id
        TextView viewFirst = (TextView) findViewById(R.id.firstName);
        TextView viewLast = (TextView) findViewById(R.id.lastName);
        TextView viewBirthday = (TextView) findViewById(R.id.birthday);
        TextView viewGender = (TextView) findViewById(R.id.gender);
        TextView viewPhone = (TextView) findViewById(R.id.phoneNumber);
        TextView viewEmail = (TextView) findViewById(R.id.emailAddress);
        TextView viewType = (TextView) findViewById(R.id.type);
        TextView viewLevel = (TextView) findViewById(R.id.level);
        TextView viewParent = (TextView) findViewById(R.id.parent);

        // setText
        viewFirst.setText(personView.getName());
        viewLast.setText(personView.getSurname());
        // Remember Birthday is a Date()
        Date bDate = personView.getBirthday();
        viewBirthday.setText(bDate.toString());
        Log.d(TAG, "birthday set");
        // Gender is Enum
        viewGender.setText("a gender");
        Log.d(TAG, "gender set");
        viewPhone.setText(personView.getPhoneNumber());
        viewEmail.setText(personView.getEmail());
        // Person type is an enum
        viewType.setText("a type");
        Log.d(TAG, "person type set");
        viewLevel.setText("a level");
        viewParent.setText("a parent");
        Log.d(TAG, "finished setting texts");
    }

    private void displayAddress(Address addressView) {
        TextView viewStreet = (TextView) findViewById(R.id.streetAddress);
        TextView viewNumber = (TextView) findViewById(R.id.addressNumber);
        TextView viewCompl = (TextView) findViewById(R.id.addressCompliment);
        TextView viewCity = (TextView) findViewById(R.id.addressCity);
        TextView viewProvince = (TextView) findViewById(R.id.addressProvince);
        TextView viewZip = (TextView) findViewById(R.id.addressZip);
        TextView viewCountry = (TextView) findViewById(R.id.addressCountry);
    }
}
