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
import com.example.kkado.yrapp.Enum.TypeAddress;
import com.example.kkado.yrapp.Enum.TypePerson;
import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.time.Month;
import java.util.Date;

/**
 *
 */
public class Contact_Add extends AppCompatActivity {
    private static final String TAG = "ContactAdd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__add);

    }

    public void returnButton(View v) {
        Intent myIntent = new Intent(this, Contact_Book.class);
        startActivity(myIntent);
    }

    public void addNewContact(View v) throws Exception {
        // Save edit text fields to new Person  and Address Object
        Person newContact;
        Address newAddress;
        newContact = setContactInfo();
        Log.d(TAG, "Successfully created contact");
        newAddress = setAddressInfo();
        Log.d(TAG, "Successfully created address");

        // Save both objects to their respective databases
        saveNewContact(newContact, newAddress);
        Log.d(TAG, "Successfully saved to both databases");
    }

    private Person setContactInfo() {
        Person contactPerson = new Person();

        EditText editFirst = (EditText)findViewById(R.id.firstName);
        EditText editLast = (EditText)findViewById(R.id.lastName);
        // Birthday
        // Gender
        EditText editPhone = (EditText)findViewById(R.id.phoneNumber);
        EditText editEmail = (EditText)findViewById(R.id.emailAddress);
        // Type
        // Level
        // Parent

        // Convert input to strings and store in Person object
        contactPerson.setName(editFirst.getText().toString());
        contactPerson.setSurname(editLast.getText().toString());
        contactPerson.setBirthday(new Date());
        contactPerson.setGender(Gender.masculine);
        contactPerson.setPhoneNumber(editPhone.getText().toString());
        contactPerson.setEmail(editEmail.getText().toString());
        contactPerson.setType(TypePerson.Leader);
        contactPerson.setLevel(1);
        contactPerson.setIdPersonParent(null);
        contactPerson.setIdPerson(0);

        return contactPerson;
    }

    private Address setAddressInfo() {
        Address contactAddress = new Address();

        // Set editText values
        EditText editStreet = (EditText)findViewById(R.id.streetAddress);
        EditText editNumber = (EditText)findViewById(R.id.addressNumber);
        EditText editCompl = (EditText)findViewById(R.id.addressCompliment);
        EditText editCity = (EditText)findViewById(R.id.addressCity);
        EditText editProvince = (EditText)findViewById(R.id.addressProvince);
        EditText editZip = (EditText)findViewById(R.id.addressZip);
        EditText editCountry = (EditText)findViewById(R.id.addressCountry);

        // Fill the Address Object, including person
        contactAddress.setIdAddress(0);
        contactAddress.setType(TypeAddress.Avenue);
        contactAddress.setNameAddress(editStreet.getText().toString());
        // Convert number to string
        String tempNum = editNumber.getText().toString();
        contactAddress.setNumberAddress(Integer.parseInt(tempNum));
        contactAddress.setComplement(editCompl.getText().toString());
        contactAddress.setCity(editCity.getText().toString());
        contactAddress.setProvince(editProvince.getText().toString());
        contactAddress.setCountry(editCountry.getText().toString());
        contactAddress.setPostalCode(editZip.getText().toString());

        return contactAddress;
    }

    private void saveNewContact(Person newContact, Address newAddress) {
        // Create DAO to store person object.
        PersonDAO dao = new PersonDAO(this);
        // Save will now represent the newly created id fro the saved person
        long personSave = dao.savePerson(newContact);

        if (personSave>0) {
            alert("Success");
        } else {
            alert("Error");
        }

        Log.d(TAG,"Saved Person successfully");
        // Save to newAddress the new id respresenting new contact
        newAddress.setIdPerson((int) personSave);
        // Create DAO to store address object.
        AddressDAO aDao = new AddressDAO(this);
        Log.d(TAG, "Created new addressDAO");
        long addressSave = aDao.saveAddress(newAddress);
        Log.d(TAG, "Execute saveAddress");

        if (addressSave > 0) {
            alert("Success");
        } else {
            alert("Error");
        }
        Log.d(TAG, "saved address successfully");
    }

    private void alert(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
