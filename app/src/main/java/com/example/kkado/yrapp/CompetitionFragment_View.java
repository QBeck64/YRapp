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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.AddressDAO;
import com.example.kkado.yrapp.dao.CompetitionDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Address;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class CompetitionFragment_View extends Fragment {
    private static final String TAG = "CompetitionView";
    View myView;
    private Context context;

    TextView vwvDescription ;
    TextView vwvGoal;
    TextView vwvInitialDate  ;
    TextView vwvFinalDate;

    Integer idCompetition;
    CompetitionDAO competitionDAO;

    Competition competitionView = new Competition();

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
        myView = inflater.inflate(R.layout.fragment_competition_view, container, false);

        Bundle data = getArguments();
        idCompetition = data.getInt("idCompetition");

        ImageButton btnReturn = (ImageButton) myView.findViewById(R.id.btnReturn);

        // Get/set competition
        try {
            competitionDAO = getCompetitionView(idCompetition);
            competitionView = competitionDAO.selectId(idCompetition);
        } catch (Exception e) {
            e.printStackTrace();
        }

        displayCompetition(competitionView);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToCompetition_Book();
            }
        });

        return myView;
    }


    /**
     *
     * @param id Person object id
     * @return Person
     * @throws Exception
     */
    private CompetitionDAO getCompetitionView(Integer id) throws Exception {
        // Create PersonDAO
        CompetitionDAO dao = new CompetitionDAO(context);
        return dao;
    }


    /**
     *
     * @param competitionView
     */
    private void displayCompetition(Competition competitionView) {

        vwvDescription = (TextView) myView.findViewById(R.id.vwvDescription);
        vwvGoal=(TextView) myView.findViewById(R.id.vwvGoal);
        vwvInitialDate  = (TextView) myView.findViewById(R.id.vwvInitialDate);
        vwvFinalDate=(TextView) myView.findViewById(R.id.vwvFinalDate);

        vwvDescription.setText(competitionView.getDescription());
        vwvGoal.setText(competitionView.getGoal());
        vwvInitialDate.setText(Util.ConvertDateToString(competitionView.getInitialDate()));  ;
        vwvFinalDate.setText(Util.ConvertDateToString( competitionView.getFinalDate()));

    }


    public void returnToCompetition_Book() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new CompetitionFragment_Book()).commit();
    }
}
