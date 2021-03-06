package com.example.kkado.yrapp;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.GroupLeaderDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.dao.TeamGroupLeaderDAO;
import com.example.kkado.yrapp.entity.GroupLeader;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.entity.TeamGroupLeader;
import com.example.kkado.yrapp.helper.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class TeamFragment extends Fragment {
    private static final String TAG = "TeamGroupLeaderAdd";

    View myView;
    private Context context;
    private TextView mDisplayInitialDate;
    private TextView mDisplayFinalDate;
    private DatePickerDialog.OnDateSetListener mInitialDateSetListener;
    private DatePickerDialog.OnDateSetListener mFinalDateSetListener;
    private Date bInitialDate;
    private Date bFinalDate;
    private Integer personLeaderId;

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
        myView = inflater.inflate(R.layout.fragment_team, container, false);

        Button btnSave = (Button)myView.findViewById(R.id.btnSave);
        ImageButton btnReturn = (ImageButton)myView.findViewById(R.id.btnReturn);

        createInitialDateListener();
        createFinalDateListener();
        setAutoPersonLeader();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TeamGroupLeader  team= getTeamGroupLeader();
                saveNewTeamGroupLeader(team);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                returnToTeamGroupLeader_Book();
            }
        });

        return myView;
    }

    /**
     * @return
     */
    private TeamGroupLeader getTeamGroupLeader() {

        EditText edtDescription = myView.findViewById(R.id.edtDescription);
        EditText edtGoal = myView.findViewById(R.id.edtGoal);


        String groupName = edtDescription.getText().toString();
        int goal = personLeaderId;
        Date initialDate = bInitialDate;
        Date finalDate = bFinalDate;

        TeamGroupLeader team = new TeamGroupLeader();

        return team;
    }

    /**
     * @param newTeamGroupLeader
     */
    private void saveNewTeamGroupLeader(TeamGroupLeader newTeamGroupLeader) {
        // Create DAO to store person object.
        TeamGroupLeaderDAO dao = new TeamGroupLeaderDAO(context);
        // Save will now represent the newly created id fro the saved person
        long TeamGroupLeaderSave = dao.saveTeamGroupLeader(newTeamGroupLeader);

        if (TeamGroupLeaderSave > 0) {
            Util.alert("Success", context);
            returnToTeamGroupLeader_Book();
        } else {
            Util.alert("Error", context);
        }

    }

    /**
     *
     */
    private void createInitialDateListener() {
        mDisplayInitialDate = (TextView) myView.findViewById(R.id.edtInitialDate);

        mDisplayInitialDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                Date date = new Date();
                cal.set(date.getYear(), date.getMonth(), date.getDay());
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mInitialDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mInitialDateSetListener = new DatePickerDialog.OnDateSetListener() {
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
                bInitialDate = new Date(year - 1900, month, day);
                SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
                String test = format.format(bInitialDate);
                mDisplayInitialDate.setText(test);
                Log.d(TAG, bInitialDate.toString());
            }
        };
    }

    /**
     *
     */
    private void createFinalDateListener() {
        mDisplayFinalDate = (TextView) myView.findViewById(R.id.edtFinalDate);

        mDisplayFinalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                Date date = new Date();
                cal.set(date.getYear(), date.getMonth(), date.getDay());
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mFinalDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mFinalDateSetListener = new DatePickerDialog.OnDateSetListener() {
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
                bFinalDate = new Date(year - 1900, month, day);
                SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
                String date = format.format(bFinalDate);
                mDisplayFinalDate.setText(date);
                Log.d(TAG, bFinalDate.toString());
            }
        };
    }

    public void returnToTeamGroupLeader_Book() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new TeamFragment_Book()).commit();
    }

    private void setAutoPersonLeader() {
        PersonDAO dao = new PersonDAO(context);
        List<Person> parentList = new ArrayList<>();
        parentList.clear();

        try {
            parentList = dao.select();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(context,
                android.R.layout.simple_dropdown_item_1line, parentList);
        AutoCompleteTextView parentView = (AutoCompleteTextView)
                myView.findViewById(R.id.personLeader);
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
                personLeaderId = selectPerson.getIdPerson();
            }
        });
    }

}
