package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.CompetitionParticipantDAO;
import com.example.kkado.yrapp.dao.InvoicingDAO;
import com.example.kkado.yrapp.dao.PeriodDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.dao.TeamGroupLeaderDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.CompetitionParticipant;
import com.example.kkado.yrapp.entity.Invoicing;
import com.example.kkado.yrapp.entity.Period;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.entity.TeamGroupLeader;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class PersonFragment_View extends Fragment {
    private static final String TAG = "ContactView";
    View myView;
    private Context context;
    TextView edtFirstName;
    TextView edtLastName;
    TextView edtBirthday;
    TextView spnGender;
    TextView edtAddressCity;
    TextView edtAddressProvince;
    TextView edtAddressZip;
    TextView edtEmailAddress;
    TextView edtPhoneNumber;
    TextView edtAddressNumber;
    TextView edtAddressComplement;
    TextView edtLevel;
    Spinner spnType;
    TextView edtAddressStreet;
    TextView edtPersonParent;

    Integer idPerson;
    AddressDAO addressDAO;
    PersonDAO personDAO;
    Person personView = new Person();
    Address addressView = new Address();

    /**
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_persons_view, container, false);

        Bundle data = getArguments();
        idPerson = data.getInt("personID");
        ImageButton btnReturn = (ImageButton) myView.findViewById(R.id.btnReturn);
        Button btEdit = myView.findViewById(R.id.btnEdit);
        Button btDelete = myView.findViewById(R.id.btnDelete);

        // Get/set person
        try {

            personDAO = getPersonView(idPerson);
            personView = personDAO.selectId(idPerson);
            if (personView.getIdPersonParent() > 0) {
                personView.setPersonParent(personDAO.selectId(personView.getIdPersonParent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Get/Set address
        try {
            addressDAO = getAddressView(idPerson);
            addressView = addressDAO.selectPersonId(idPerson);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (personView != null)
            displayPerson(personView);
        if (addressView != null)
            displayAddress(addressView);

        // Edit and delete buttons
        btDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteContact();
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editContact();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToContacts();
            }
        });
        return myView;
    }

    /**
     * @param dateText
     * @return
     */
    private Date ConvertStringToDate(String dateText) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = format.parse(dateText);
            System.out.println(date);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param id Person object id
     * @return Person
     * @throws Exception
     */
    private PersonDAO getPersonView(Integer id) throws Exception {
        // Create PersonDAO
        PersonDAO dao = new PersonDAO(context);
        return dao;
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    private AddressDAO getAddressView(Integer id) throws Exception {
        // create AddressDAO
        AddressDAO dao = new AddressDAO(context);
        return dao;
    }

    /**
     * @param personView
     */
    private void displayPerson(Person personView) {
        // find and set by id
        edtFirstName = (TextView) myView.findViewById(R.id.edtFirstName);
        edtLastName = (TextView) myView.findViewById(R.id.edtLastName);
        edtBirthday = (TextView) myView.findViewById(R.id.edtBirthday);
        spnGender = (TextView) myView.findViewById(R.id.spnGender);
        edtPhoneNumber = (TextView) myView.findViewById(R.id.edtPhoneNumber);
        edtEmailAddress = (TextView) myView.findViewById(R.id.edtEmailAddress);
        edtLevel = (TextView) myView.findViewById(R.id.edtLevel);
        edtPersonParent = (TextView) myView.findViewById(R.id.edtPersonParent);

        // setText
        edtFirstName.setText(personView.getName());
        edtLastName.setText(personView.getSurname());
        edtBirthday.setText(Util.ConvertDateToString(personView.getBirthday()));
        spnGender.setText(personView.getGender().getDescription());
        edtPhoneNumber.setText(personView.getPhoneNumber());
        edtEmailAddress.setText(personView.getEmail());
        edtLevel.setText(Integer.toString(personView.getLevel()));
        if (personView.getIdPersonParent() > 0)
            edtLastName.setText(personView.getPersonParent().toString());
    }

    /**
     * @param addressView
     */
    private void displayAddress(Address addressView) {
        //find and set id's
        edtAddressStreet = (TextView) myView.findViewById(R.id.edtAddressStreet);
        edtAddressNumber = (TextView) myView.findViewById(R.id.edtAddressNumber);
        edtAddressComplement = (TextView) myView.findViewById(R.id.edtAddressComplement);
        edtAddressCity = (TextView) myView.findViewById(R.id.edtAddressCity);
        edtAddressProvince = (TextView) myView.findViewById(R.id.edtAddressProvince);
        edtAddressZip = (TextView) myView.findViewById(R.id.edtAddressZip);

        //setText
        edtAddressStreet.setText(addressView.getNameAddress());
        edtAddressNumber.setText(Integer.toString(addressView.getNumberAddress()));
        edtAddressComplement.setText(addressView.getComplement());
        edtAddressCity.setText(addressView.getCity());
        edtAddressProvince.setText(addressView.getProvince());
        edtAddressZip.setText(addressView.getPostalCode());

        Log.d(TAG, "Successfully set address objects in View");
    }


    public void returnToContacts() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new PersonFragment_Book()).commit();
    }

    public void deleteContact() {
        if (validateIsCanDelete()) {
            addressDAO.delete(idPerson);
            personDAO.delete(idPerson);
            returnToContacts();
        }
    }

    private boolean validateIsCanDelete() {
        CompetitionParticipantDAO competitionParticipantDAO = new CompetitionParticipantDAO(context);
        CompetitionParticipant competitionParticipant = new CompetitionParticipant();
        InvoicingDAO invoicingDAO = new InvoicingDAO(context);
        Invoicing invoicing = new Invoicing();
        TeamGroupLeaderDAO teamGroupLeaderDAO = new TeamGroupLeaderDAO(context);
        TeamGroupLeader teamGroupLeader = new TeamGroupLeader();

        Boolean result = false;
        try {
            
            return competitionParticipantDAO.selectPersonId(idPerson) == null || invoicingDAO.selectPersonId(idPerson) == null || teamGroupLeaderDAO.selectPersonId(idPerson) == null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void editContact() {
        personView.setEmail(edtEmailAddress.getText().toString());
        personView.setPhoneNumber(edtPhoneNumber.getText().toString());
        if (!TextUtils.isEmpty(edtLevel.getText().toString())) {
            personView.setLevel(Integer.parseInt(edtLevel.getText().toString()));
        }

        addressView.setNameAddress(edtAddressStreet.getText().toString());
        if (!TextUtils.isEmpty(edtAddressNumber.getText().toString())) {
            addressView.setNumberAddress(Integer.parseInt(edtAddressNumber.getText().toString()));
        }
        addressView.setComplement(edtAddressComplement.getText().toString());
        addressView.setProvince(edtAddressProvince.getText().toString());
        addressView.setCity(edtAddressCity.getText().toString());
        addressView.setPostalCode(edtAddressZip.getText().toString());

        long personSave = personDAO.savePerson(personView);
        Log.d(TAG, "saving person, getting LOng back");

        if (personSave > 0) {
            Util.alert("Success", context);
        } else {
            Util.alert("Error", context);
        }

        long addressSave = addressDAO.saveAddress(addressView);

        Log.d(TAG, String.valueOf(addressSave));
        if (addressSave > 0) {
            Util.alert("Success", context);
        } else {
            Util.alert("Error", context);
        }
        Log.d(TAG, "saved address successfully, is " + addressSave);
    }
}
