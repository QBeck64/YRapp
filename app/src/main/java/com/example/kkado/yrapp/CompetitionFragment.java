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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.CompetitionDAO;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class CompetitionFragment extends Fragment {
    private static final String TAG = "CompetitionAdd";

    View myView;
    private Context context;
    private TextView mDisplayInitialDate;
    private TextView mDisplayFinalDate;
    private DatePickerDialog.OnDateSetListener mInitialDateSetListener;
    private DatePickerDialog.OnDateSetListener mFinalDateSetListener;
    private Date bInitialDate;
    private Date bFinalDate;
    EditText edtDescription;
    EditText edtGoal;

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
        myView = inflater.inflate(R.layout.fragment_competition, container, false);

        ImageButton btnSave = (ImageButton) myView.findViewById(R.id.btnSave);
        ImageButton btnReturn = (ImageButton) myView.findViewById(R.id.btnReturn);

        createInitialDateListener();
        createFinalDateListener();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNewCompetition();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                returnToCompetition_Book();
            }
        });

        return myView;
    }

    /**
     * @return
     */
    private Competition getCompetition() {

        edtDescription = myView.findViewById(R.id.edtDescription);
        edtGoal = myView.findViewById(R.id.edtGoal);


        String description = edtDescription.getText().toString();
        String goal = edtGoal.getText().toString();
        Date initialDate = bInitialDate;
        Date finalDate = bFinalDate;

        Competition competition = null;
        if (validateFields(description, goal, initialDate, finalDate)) {
            competition = new Competition(description, goal, initialDate, finalDate);
        }
        return competition;
    }

    /**
     *
     * @param description
     * @param goal
     * @param initialDate
     * @param finalDate
     * @return
     */
    private boolean validateFields(String description, String goal, Date initialDate, Date finalDate) {

        boolean result = true;
        if (description == null || description.isEmpty()) {
            result = false;
            Util.alert("Please, description is required.", context);
        } else if (goal == null || goal.isEmpty()) {
            result = false;
            Util.alert("Please, goal is required.", context);
        } else if (initialDate == null || initialDate.toString().isEmpty()) {
            result = false;
            Util.alert("Please, initial date is required.", context);
        } else if (finalDate == null || finalDate.toString().isEmpty()) {
            result = false;
            Util.alert("Please, final date is required.", context);
        }
        return result;
    }

    /**
     *
     */
    private void saveNewCompetition() {
        Competition newCompetition = getCompetition();

        if (newCompetition != null) {
            // Create DAO to store person object.
            CompetitionDAO dao = new CompetitionDAO(context);
            // Save will now represent the newly created id fro the saved person
            long competitionSave = dao.saveCompetition(newCompetition);

            if (competitionSave > 0) {
                Util.alert("Success", context);
                returnToCompetition_Book();
            } else {
                Util.alert("Error", context);
            }
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
                String date = format.format(bInitialDate);
                mDisplayInitialDate.setText(date);
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

    public void returnToCompetition_Book() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new CompetitionFragment_Book()).commit();
    }

}
