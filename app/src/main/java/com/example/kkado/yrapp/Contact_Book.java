package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact_Book activity displays a listview of all the Person objects from the PersonDAO database.
 * All contacts listed are clickable and, when clicked, send the clicked Person object in
 * an Intent to Contact_View. A createContact button is used to pause and send an empty intent to start the
 * Contact_Add activity.
 */
public class Contact_Book extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final static String TAG = "ContactBook";

    private ListView contactView;
    private List<Person> contacts = new ArrayList<>();
    private ArrayAdapter<Person> arrayAdapterContact;

    /**
     * On creation of Contact_Book, contactView is set to the ListView in the activity. contactView is then initialized
     * so all Person objects saved in the PersonDAO are shown.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__book);

        // Initialize resources
        contactView = (ListView)findViewById(R.id.contactView);

        try {
            initializeContactList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *PersonDAO object created and "contacts", ArrayList<Person> is filled with PersonDAO. Use ArrayAdapter to set list in
     * contactView and use onItemClickListener, for when a contact is selected.
     * @throws Exception
     */
    private void initializeContactList() throws Exception {
        PersonDAO dao = new PersonDAO(this);
        contacts.clear();
        contacts = dao.select();

        arrayAdapterContact = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, contacts);
        contactView.setAdapter(arrayAdapterContact);
        contactView.setOnItemClickListener(this);
    }

    /**
     * Selected Person is set to newly created Person object. New intent is created and will be used
     * to start the Contact_View activity. Within the intent, the id of the person object will be sent over.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Person newPerson = (Person) parent.getItemAtPosition(position);
        Log.d(TAG, "Item clicked, item person saved to new person object");

        // Create intent and send clicked person id to Contact_View
        Intent clickIntent = new Intent(this, PersonFragment.class);
        clickIntent.putExtra("PersonId", newPerson.getIdPerson());
        Log.d(TAG, "Created new intent and sent  " + newPerson.getName() + "'s information to Contact_View");
        startActivity(clickIntent);
        Log.d(TAG, "executed startActivity");
    }

    /**
     * Will create new intent to start Contact_Add.
     * @param v
     */
    public void createContact(View v) {
        Intent myIntent = new Intent(this, Contact_Add.class);
        startActivity(myIntent);
    }
}
