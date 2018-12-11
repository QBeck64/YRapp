package com.example.kkado.yrapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kkado.yrapp.dao.CompetitionDAO;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class CompetitionFragment extends Fragment
{
    View myView;
    private Context context;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_competition, container, false);
        Button bt = myView.findViewById(R.id.btnSave);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Competition competition =     getCompetition();
                saveNewCompetition(competition);
            }
        });

        return myView;
    }

    /**
     *
     * @return
     */
    private Competition getCompetition(){

        EditText edtDescription = myView.findViewById(R.id.edtDescription);
        EditText edtGoal = myView.findViewById(R.id.edtGoal);
        EditText edtInitialDate = myView.findViewById(R.id.edtInitialDate);
        EditText edtFinalDate = myView.findViewById(R.id.edtFinalDate);

        String description = edtDescription.getText().toString();
        String goal = edtGoal.getText().toString();
        Date initialDate = ConvertStringToDate(edtInitialDate.getText().toString());
        Date finalDate = ConvertStringToDate(edtFinalDate.getText().toString());

        Competition competition = new Competition(description, goal, initialDate, finalDate);

        return competition;
    }

    /**
     *
     * @param dateText
     * @return
     */
    private Date ConvertStringToDate(String dateText){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
     * @param newCompetition
     */
    private void saveNewCompetition(Competition newCompetition) {
        // Create DAO to store person object.
        CompetitionDAO dao = new CompetitionDAO(context);
        // Save will now represent the newly created id fro the saved person
        long competitionSave = dao.saveCompetition(newCompetition);

        if (competitionSave>0) {
            Util.alert("Success", context);
        } else {
            Util.alert("Error", context);
        }

    }

}
