package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class PersonFragment_View extends Fragment {
    private static final String TAG = "ContactAdd";
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
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_person_view, container, false);

        Bundle data = getArguments();
        idPerson = data.getInt("personID");

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

        displayPerson(personView);
        displayAddress(addressView);


        return myView;
    }


    /**
     *
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
     *
     * @return
     */




    /**
     *
     */



    /**
     *
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
     *
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
     *
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
        edtBirthday.setText(personView.getBirthday().toString());
        spnGender.setText(personView.getGender().getDescription());
        edtPhoneNumber.setText(personView.getPhoneNumber());
        edtEmailAddress.setText(personView.getEmail());
        edtLevel.setText(personView.getLevel());
        edtPersonParent.setText(personView.getPersonParent().toString());
    }

    /**
     *
     * @param addressView
     */
    private void displayAddress(Address addressView) {
        //find and set id's
        TextView viewStreet = (TextView) myView.findViewById(R.id.addressStreet);
        TextView viewNumber = (TextView) myView.findViewById(R.id.addressNumber);
        TextView viewCompl = (TextView) myView.findViewById(R.id.addressCompliment);
        TextView viewCity = (TextView) myView.findViewById(R.id.addressCity);
        TextView viewProvince = (TextView) myView.findViewById(R.id.addressProvince);
        TextView viewZip = (TextView) myView.findViewById(R.id.addressZip);
        TextView viewCountry = (TextView) myView.findViewById(R.id.addressCountry);

        //setText
        viewStreet.setText(addressView.getNameAddress());
//        viewNumber.setText(addressView.getNumberAddress());
//        viewCompl.setText(addressView.getComplement());
//        viewCity.setText(addressView.getCity());
//        viewProvince.setText(addressView.getProvince());
//        viewZip.setText(addressView.getPostalCode());
//        viewCountry.setText(addressView.getCountry());
        Log.d(TAG, "Successfully set address objects in View");
    }

    public void createNewPerson(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new PersonFragment()).commit();
    }

    public void returnToContacts(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new PersonFragment_Book()).commit();
    }
}
