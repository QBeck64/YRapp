package com.example.kkado.yrapp;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypeAddress;
import com.example.kkado.yrapp.Enum.TypePerson;
import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.kkado.yrapp.LevelUpdaterService.startActionUpdateCg;

/**
 *
 */
public class PersonFragment extends Fragment {
    private static final String TAG = "ContactAdd";
    View myView;
    private Context context;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText edtFirstName;
    EditText edtLastName;
    TextView edtBirthday;
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
    TextView mDatePicker;


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
        myView = inflater.inflate(R.layout.fragment_person, container, false);

        LoadDropDown();
        createDateListener();

        Button bt = myView.findViewById(R.id.btnSave);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNewPerson();
            }
        });
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
     * Using the TextView "birthday" field in the activity, DatePicker is created. the dialog box will appear to the
     * user with the format, "MM/dd/yyyy". An OnSetListener set the data into the private Date object, "bDate". It is
     * important to remember that after setting the birthday to "bDate", you must update the TextView in the activity
     * to reflect the selected birthday.
     */
    private void createDateListener() {
        Log.d(TAG, "Starting createDateListener");
        mDatePicker = (TextView) myView.findViewById(R.id.edtBirthday);
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int year = cal.get(Calendar.YEAR);
                Log.d(TAG, "finished with calendar");

                DatePickerDialog dialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        month,day,year);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            /**
             * Save selected date to private "bDate" and update/set TextView field to show
             * selected date.
             * @param datePicker
             * @param year
             * @param month
             * @param day
             */
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Date bDate = new Date(year - 1900, month, day);
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String test = format.format(bDate);
                mDatePicker.setText(test);
            }
        };
    }

    /**
     *
     * @return
     */
    private Person setPersonInfo() {
        setScreenComponents();
        String name = edtFirstName.getText().toString();
        String surname = edtLastName.getText().toString();
        String dateBirthday = edtBirthday.getText().toString();
        Date birthday = ConvertStringToDate(dateBirthday);
        Gender gender = Gender.getGenderDescription(spnGender.getSelectedItem().toString());
        Integer level = Integer.parseInt(edtLevel.getText().toString());
        String email = edtEmailAddress.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();
        Integer idPersonParen = null;
        TypePerson type = TypePerson.getTypePersonDescription(spnType.getSelectedItem().toString());

        Person person = new Person(name, surname, birthday, gender, level, email, phoneNumber, idPersonParen, type);

        return person;
    }

    /**
     *
     * @param idPerson
     * @return
     */
    private Address setAddressInfo(int idPerson) {

        TypeAddress type = TypeAddress.Boulevard;
        String nameAddress = edtAddressStreet.getText().toString();
        int numberAddress = Integer.parseInt(edtAddressNumber.getText().toString());
        String complement = edtAddressComplement.getText().toString();
        String province = edtAddressProvince.getText().toString();
        String city = edtAddressCity.getText().toString();
        String postalCode = edtAddressZip.getText().toString();

        Address contactAddress = new Address(type, nameAddress, numberAddress, complement, province, city, postalCode, idPerson);

        return contactAddress;
    }

    /**
     *
     */
    private void saveNewPerson() {
        // Create DAO to store person object.
        PersonDAO personDAO = new PersonDAO(context);
        // Save will now represent the newly created id fro the saved person
        Person newPerson = setPersonInfo();

        long personSave = personDAO.savePerson(newPerson);

        if (personSave > 0) {
            Util.alert("Success", context);
        } else {
            Util.alert("Error", context);
        }
        Log.d(TAG, "Saved Person successfully");

        // Create DAO to store address object.
        AddressDAO addressDAO = new AddressDAO(context);

        // Save to newAddress the new id respresenting new contact
        Address newAddress = setAddressInfo((int) personSave);

        Log.d(TAG, "Created new addressDAO");
        long addressSave = addressDAO.saveAddress(newAddress); //Currently returning -1

        if (addressSave > 0) {
            Util.alert("Success", context);
        } else {
            Util.alert("Error", context);
        }
        Log.d(TAG, "saved address successfully");

        try {
            Person person = personDAO.selectId(personSave);
            startActionUpdateCg(context, person.getIdPersonParent());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    private void LoadDropDown() {
        Spinner mySpinnerGender = (Spinner) myView.findViewById(R.id.spnGender);
        mySpinnerGender.setAdapter(new ArrayAdapter<Gender>(context, android.R.layout.simple_spinner_dropdown_item, Gender.values()));
    }

    /**
     *
     */
    private void setScreenComponents() {
        edtFirstName = (EditText) myView.findViewById(R.id.edtFirstName);
        edtLastName = (EditText) myView.findViewById(R.id.edtLastName);
        edtBirthday = (TextView) myView.findViewById(R.id.edtBirthday);
        spnGender = (Spinner) myView.findViewById(R.id.spnGender);
        edtEmailAddress = (EditText) myView.findViewById(R.id.edtEmailAddress);
        edtPhoneNumber = (EditText) myView.findViewById(R.id.edtPhoneNumber);
        edtLevel = (EditText) myView.findViewById(R.id.edtLevel);

        edtAddressStreet = (EditText) myView.findViewById(R.id.edtAddressStreet);
        edtAddressNumber = (EditText) myView.findViewById(R.id.edtAddressNumber);
        edtAddressComplement = (EditText) myView.findViewById(R.id.edtAddressComplement);
        edtAddressProvince = (EditText) myView.findViewById(R.id.edtAddressProvince);
        edtAddressCity = (EditText) myView.findViewById(R.id.edtAddressCity);
        edtAddressZip = (EditText) myView.findViewById(R.id.edtAddressZip);
    }
}
