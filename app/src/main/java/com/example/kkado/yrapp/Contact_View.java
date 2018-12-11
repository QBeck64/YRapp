package com.example.kkado.yrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypePerson;
import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.util.Date;

/**
 * Contact_View will receieve the id of a person object saved in the PersonDAO database.
 * The id is found in the intent sent from Contact_Book. Id is used to fetch person object and address object
 * from their respective databases. The values of each object are then set to TextView, that correlate with TextView
 * fields in the Activity. A Return button is also contained to return to Contact_Book.
 */
public class Contact_View extends AppCompatActivity {
    static final String TAG = "ContactView";

    Integer idPerson;
    AddressDAO addressDAO;
    PersonDAO personDAO;
    Person personView = new Person();
    Address addressView = new Address();

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__view);

        // Get/set intent which started the activity
        Intent intent = getIntent();
        idPerson = intent.getIntExtra("PersonId", 0);
        // Get/set person
        try {
            personDAO = getPersonView(idPerson);
            personView = personDAO.selectId(idPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Get/Set address
        try {
            addressDAO = getAddressView(idPerson);
            addressView = addressDAO.selectId(idPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        displayPerson(personView);
        //displayAddress(addressView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editContact:
                toContactEdit();
                return true;
            case R.id.deleteContact:
                personDAO.delete(idPerson);
                addressDAO.delete(idPerson);
                returnToContacts();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void returnToContacts() {
        Intent myIntent = new Intent(this, Contact_Book.class);
        startActivity(myIntent);
    }

    public void toContactEdit() {
        Intent newIntent = new Intent(this, Contact_Edit.class);
        newIntent.putExtra("PersonId", idPerson);
        startActivity(newIntent);
    }

    /**
     *
     * @param v
     */
    public void returnButton(View v) {
       returnToContacts();
    }

    /**
     *
     * @param id Person object id
     * @return Person
     * @throws Exception
     */
    private PersonDAO getPersonView(Integer id) throws Exception {
        // Create PersonDAO
        PersonDAO dao = new PersonDAO(this);
        return dao;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    private AddressDAO getAddressView(Integer id) throws Exception {
        // create AddressDAO
        AddressDAO dao = new AddressDAO(this);
        return dao;
    }

    /**
     *
     * @param personView
     */
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
//        viewFirst.setText(personView.getName());
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

    /**
     *
     * @param addressView
     */
    private void displayAddress(Address addressView) {
        //find and set id's
        TextView viewStreet = (TextView) findViewById(R.id.addressStreet);
        TextView viewNumber = (TextView) findViewById(R.id.addressNumber);
        TextView viewCompl = (TextView) findViewById(R.id.addressCompliment);
        TextView viewCity = (TextView) findViewById(R.id.addressCity);
        TextView viewProvince = (TextView) findViewById(R.id.addressProvince);
        TextView viewZip = (TextView) findViewById(R.id.addressZip);
        TextView viewCountry = (TextView) findViewById(R.id.addressCountry);

        //setText
        viewStreet.setText(addressView.getNameAddress());
        viewNumber.setText(addressView.getNumberAddress());
        viewCompl.setText(addressView.getComplement());
        viewCity.setText(addressView.getCity());
        viewProvince.setText(addressView.getProvince());
        viewZip.setText(addressView.getPostalCode());
        viewCountry.setText(addressView.getCountry());
        Log.d(TAG, "Successfully set address objects in View");
    }
}
