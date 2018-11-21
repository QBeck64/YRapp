package com.example.kkado.yrapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

public class Contact_Add extends AppCompatActivity {
    EditText editFirst, editLast, editDate, editEmail, editPhone,
        editStreet, editCity, editProvince, editZip;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__add);

    }

    public void newContacts() {
        // Declare a new Person object
        final Person newContact = new Person();

        // Convert input to strings and store in Person object

        newContact.setName(editFirst.getText().toString());
        newContact.setSurname(editLast.getText().toString());
        // I don't understand how to get the "Date" and put it in
        newContact.setEmail(editEmail.getText().toString());
        newContact.setPhoneNumber(editPhone.getText().toString());

        // Create a new Address Object
        final Address newAddress = new Address();

        // Fill the Address Object, including person
        newAddress.setNameAddress(editStreet.getText().toString());
        newAddress.setCity(editCity.getText().toString());
        newAddress.setProvince(editProvince.getText().toString());
        newAddress.setPostalCode(editZip.getText().toString());
        newAddress.setPerson(newContact);

        // Create DAO to store person object.
        PersonDAO dao = new PersonDAO(this);

        boolean save = dao.save(newContact);

        if (save) {
            alert("Success");
        } else {
            alert("Error");
        }


    }
    private void alert(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
