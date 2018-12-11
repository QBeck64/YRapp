package com.example.kkado.yrapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypeAddress;
import com.example.kkado.yrapp.Enum.TypePerson;
import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Person;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.kkado.yrapp.LevelUpdaterService.startActionUpdateCg;

/**
 * Contact_Add will provide configure and retrieve user input from the activity screen.
 * All data will be saved to the appropriate entities, Person & Address, which will then be stored in
 * the appropriate databases, PersonDAO & AddressDAO.
 * 2 Buttons will be used, one returning to Contact_Book and one which will "add" the newly entered contact.
 */
public class Contact_Add extends AppCompatActivity {
    private static final String TAG = "ContactAdd";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Date bDate;
    private Integer parentId;

    /**
     * Upon creation of Contact_Add, the Gender spinner, DatePicker, and personParent AutoCompleteTextView
     * will be initialized.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__add);

        createDateListener();
        setGenderSpinner();
        setPersonTypeSpinner();
        setAutoPersonParent();
    }

    /**
     * Using the PersonDAO, an ArrayList will be created and filled with all Persons residing in the PersonDAO.
     * An arrayadapter will be used to help display the list, and will be set in an AutoCompleteTextView object.
     * Person names from the List will be displayed and clickable to the user as input is typed. Purpose
     * is to guarantee that the user enters a valid parent name, so a valid personParentId can be set in the new Contact
     * being created.
     */
    private void setAutoPersonParent() {
        PersonDAO dao = new PersonDAO(this);
        List<Person> parentList = new ArrayList<>();
        parentList.clear();

        try {
            parentList = dao.select();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<Person> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, parentList);
        AutoCompleteTextView parentView = (AutoCompleteTextView)
                findViewById(R.id.personParent);
        parentView.setAdapter(adapter);

        parentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Create a new person object to save the onItemClick selected object, then save the selected
             * personId to the private variable, "parentId".
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person selectPerson = (Person)parent.getItemAtPosition(position);
                // Set parentId for use in setting Contact
                parentId = selectPerson.getIdPerson();
            }
        });
    }

    /**
     * Using the TextView "birthday" field in the activity, DatePicker is created. the dialog box will appear to the
     * user with the format, "MM/dd/yyyy". An OnSetListener set the data into the private Date object, "bDate". It is
     * important to remember that after setting the birthday to "bDate", you must update the TextView in the activity
     * to reflect the selected birthday.
     */
    private void createDateListener() {
        mDisplayDate = (TextView) findViewById(R.id.birthday);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        Contact_Add.this,
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
                bDate = new Date(year - 1900, month, day);
                SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
                String test = format.format(bDate);
                mDisplayDate.setText(test);
                Log.d(TAG, bDate.toString());
            }
        };
    }

    /**
     * Debatable at the moment!
     */
    private void setPersonTypeSpinner() {
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add(TypePerson.Candidate.getDescription());
        spinnerArray.add(TypePerson.SalesPerson.getDescription());
        spinnerArray.add(TypePerson.Leader.getDescription());
        spinnerArray.add(TypePerson.ZoneManager.getDescription());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.type);
        sItems.setAdapter(adapter);
    }

    /**
     * Create an arrayList and add both possible genders. Set within an ArrayAdapter. Create and set
     * spinner to "gender" spinner in activity, then set the adapter.
     */
    private void setGenderSpinner() {
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add(Gender.Masculine.getDescription());
        spinnerArray.add(Gender.Feminine.getDescription());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.gender);
        sItems.setAdapter(adapter);
    }

    /**
     * New intent is created to return to the Contact_Book.
     * @param v
     */
    public void returnButton(View v) {
        Intent myIntent = new Intent(this, Contact_Book.class);
        startActivity(myIntent);
    }

    /**
     * for use when add contact button is clicked. New person and address objects are created. Each object will be appropriately filled using "set" functions, and will then be passed onto
     * "saveNewContact" function to be stored in the databases. Upon successful completion of saving to databses,
     * a Group Leader update service will run to update contact level if necessary.
     * @param v
     * @throws Exception
     */
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

        //Group Leader updater service. The following code will check if the parent is a group leader
        //and and update her level if needed
        startActionUpdateCg(this, newContact.getIdPersonParent());
    }

    /**
     * Creates a Person Object and sets all variables within, using user input from activity. Method will
     * return the initialized and set Person object.
     * @return
     */
    private Person setContactInfo() {
        Person contactPerson = new Person();

        EditText editFirst = findViewById(R.id.firstName);
        EditText editLast = findViewById(R.id.lastName);
        EditText editPhone = findViewById(R.id.phoneNumber);
        EditText editEmail = findViewById(R.id.emailAddress);
        EditText editLevel = findViewById(R.id.level);

        //Spinners
        Spinner gender = findViewById(R.id.gender);
        Spinner type = findViewById(R.id.type);

        // Convert input to strings and store in Person object
        contactPerson.setName(editFirst.getText().toString());
        contactPerson.setSurname(editLast.getText().toString());
        contactPerson.setBirthday(bDate);
        contactPerson.setGender(Gender.getGenderDescription(gender.getSelectedItem().toString()));
        Log.d(TAG, "Gender selected is, " + contactPerson.getGender());
        contactPerson.setPhoneNumber(editPhone.getText().toString());
        contactPerson.setEmail(editEmail.getText().toString());
        contactPerson.setType(TypePerson.getTypePersonDescription(type.getSelectedItem().toString()));
        contactPerson.setLevel(Integer.parseInt(editLevel.getText().toString()));
        contactPerson.setIdPersonParent(parentId);
        contactPerson.setIdPerson(0);

        return contactPerson;
    }

    /**
     * Creates an Address Object and sets all variables within, using user input from activity. Method will
     * return the initialized and set Address object.
     * @return
     */
    private Address setAddressInfo() {
        Address contactAddress = new Address();

        // Set editText values
        EditText editStreet = (EditText)findViewById(R.id.addressStreet);
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

    /**
     * Creates new PersonDAO and AddressDAO objects to save the passed Person and Address objects.
     * @param newContact
     * @param newAddress
     */
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
        long addressSave = aDao.saveAddress(newAddress); //Currently returning -1
        Log.d(TAG, "Execute saveAddress");
        Integer test = (int) addressSave;
        Log.d(TAG, test.toString());

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
