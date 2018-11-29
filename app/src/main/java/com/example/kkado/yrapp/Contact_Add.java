package com.example.kkado.yrapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypePerson;
import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.time.Month;
import java.util.Date;

public class Contact_Add extends AppCompatActivity {
    private static final String TAG = "ContactAdd";

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

    public void addNewContact(View v) throws Exception {
        // Declare a new Person object
        Person newContact = new Person();

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

        Log.d(TAG, "Save input to editText variables");

        // For numbers
        String tempV = editNumber.getText().toString();

        // Convert input to strings and store in Person object
        newContact.setName(editFirst.getText().toString());
        newContact.setSurname(editLast.getText().toString());
        newContact.setBirthday(new Date());
        newContact.setGender(Gender.masculine);
        newContact.setLevel(1);
        newContact.setIdPersonParent(null);
        newContact.setEmail(editEmail.getText().toString());
        newContact.setPhoneNumber(editPhone.getText().toString());
        newContact.setType(TypePerson.Leader);


        // Remember to set the person id to zero
        newContact.setIdPerson(0);

        Log.d(TAG, "Finish setting all variables inside person object");

        // Create DAO to store person object.
        PersonDAO dao = new PersonDAO(this);

        long save = dao.savePerson(newContact);
        Log.d(TAG, "Used savePerson method");

        if (save>0) {
            alert("Success");
        } else {
            alert("Error");
        }
        Log.d(TAG, "Finish saving new person to PersonDAO");

        // Using newly created ID from person, call person object and save into Address
        Person newPerson = dao.selectId(save);

        Log.d(TAG, "Create new Person to store in new Address");
        // Create a new Address Object
        Address newAddress = new Address();
        newAddress.setIdAddress(0);
        Log.d(TAG, "Create new address and set ID");

        // Fill the Address Object, including person
        newAddress.setNameAddress(editStreet.getText().toString());
        newAddress.setNumberAddress(Integer.parseInt(tempV));
        newAddress.setComplement(editCompl.getText().toString());
        newAddress.setCity(editCity.getText().toString());
        newAddress.setProvince(editProvince.getText().toString());
        newAddress.setCountry(editCountry.getText().toString());
        newAddress.setPostalCode(editZip.getText().toString());
        newAddress.setPerson(newPerson);
        newAddress.setIdPerson(newPerson.getIdPerson());



        Log.d(TAG, "Finish setting Address variables");
        // Create DAO to store address object.
        AddressDAO aDao = new AddressDAO(this);
        Log.d(TAG, "Create DAOAddress");

        long aSave = aDao.saveAddress(newAddress);
        Log.d(TAG, "Execute saveAddress");

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
