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
    EditText edtFirstName;
    EditText edtLastName;
    EditText edtBirthday;
    Spinner spnGender;
    EditText edtAddressCity;
    EditText edtAddressProvince;
    EditText edtAddressZip;
    EditText edtEmailAddress;
    EditText edtPhoneNumber;
    EditText edtAddressNumber;
    EditText edtAddressComplement;
    EditText edtLevel;
    Spinner spnType;
    EditText edtAddressStreet;


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
        TextView viewFirst = (TextView) myView.findViewById(R.id.firstName);
        TextView viewLast = (TextView) myView.findViewById(R.id.lastName);
        TextView viewBirthday = (TextView) myView.findViewById(R.id.birthday);
        TextView viewGender = (TextView) myView.findViewById(R.id.gender);
        TextView viewPhone = (TextView) myView.findViewById(R.id.phoneNumber);
        TextView viewEmail = (TextView) myView.findViewById(R.id.emailAddress);
        TextView viewType = (TextView) myView.findViewById(R.id.type);
        TextView viewLevel = (TextView) myView.findViewById(R.id.level);
        TextView viewParent = (TextView) myView.findViewById(R.id.parent);

        // setText
        viewFirst.setText(personView.getName());
        viewLast.setText(personView.getSurname());
        // Remember Birthday is a Date()
//        Date bDate = personView.getBirthday();
//        viewBirthday.setText(bDate.toString());
//        Log.d(TAG, "birthday set");
//        // Gender is Enum
//        viewGender.setText("a gender");
//        Log.d(TAG, "gender set");
//        viewPhone.setText(personView.getPhoneNumber());
//        viewEmail.setText(personView.getEmail());
//        // Person type is an enum
//        viewType.setText("a type");
//        Log.d(TAG, "person type set");
//        viewLevel.setText("a level");
//        viewParent.setText("a parent");
//        Log.d(TAG, "finished setting texts");
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

    public void returnToContacts() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new PersonFragment_Book()).commit();
    }
}
