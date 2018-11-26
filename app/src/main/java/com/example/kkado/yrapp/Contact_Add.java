package com.example.kkado.yrapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.time.Month;
import java.util.Date;

public class Contact_Add extends AppCompatActivity {
    EditText editFirst;
    EditText editLast;
    EditText editDate;
    EditText editEmail;
    EditText editPhone;
    EditText editStreet;
    EditText editNumber;
    EditText editCompl;
    EditText editCity;
    EditText editProvince;
    EditText editCountry;
    EditText editZip;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__add);

    }

    // Return button, will cancel data inputs and return to contact book
    public void returnButton(View v) {
        Intent myIntent = new Intent(this, Contact_Book.class);
        startActivity(myIntent);
    }

    public void addNewContact(View v) {
        // Declare a new Person object
        final Person newContact = new Person();

        // Set editText values
        editFirst = (EditText)findViewById(R.id.firstName);
        editLast = (EditText)findViewById(R.id.lastName);
        editEmail = (EditText)findViewById(R.id.emailAddress);
        editPhone = (EditText)findViewById(R.id.phoneNumber);
        editStreet = (EditText)findViewById(R.id.streetAddress);
        editNumber = (EditText)findViewById(R.id.addressNumber);
        editCompl = (EditText)findViewById(R.id.addressCompliment);
        editCity = (EditText)findViewById(R.id.addressCity);
        editProvince = (EditText)findViewById(R.id.addressProvince);
        editCountry = (EditText)findViewById(R.id.addressCountry);
        editZip = (EditText)findViewById(R.id.addressZip);

        // For numbers
        String tempV = editNumber.getText().toString();

        // Convert input to strings and store in Person object
        newContact.setName(editFirst.getText().toString());
        newContact.setSurname(editLast.getText().toString());
        newContact.setEmail(editEmail.getText().toString());
        newContact.setPhoneNumber(editPhone.getText().toString());

        // Remember to set the person id to zero
        newContact.setIdPerson(0);

        // Create DAO to store person object.
        PersonDAO dao = new PersonDAO(this);

        long save = dao.savePerson(newContact);

        if (save > 0) {
            alert("Success");
        } else {
            alert("Error");
        }

        // Create a new Address Object
        final Address newAddress = new Address();
        newAddress.setIdAddress(0);

        // Fill the Address Object, including person
        newAddress.setNameAddress(editStreet.getText().toString());
        newAddress.setNumberAddress(Integer.parseInt(tempV));
        newAddress.setComplement(editCompl.getText().toString());
        newAddress.setCity(editCity.getText().toString());
        newAddress.setProvince(editProvince.getText().toString());
        newAddress.setCountry(editCountry.getText().toString());
        newAddress.setPostalCode(editZip.getText().toString());
        newAddress.setPerson(newContact);

        // Create DAO to store address object.
        AddressDAO aDao = new AddressDAO(this);

        long aSave = aDao.saveAddress(newAddress);

        if (aSave > 0) {
            alert("Success");
        } else {
            alert("Error");
        }


    }
    private void alert(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
