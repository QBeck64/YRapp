package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.CompetitionDAO;
import com.example.kkado.yrapp.dao.GroupLeaderDAO;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.entity.GroupLeader;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class GroupFragment_View extends Fragment {
    private static final String TAG = "GroupLeaderView";
    View myView;
    private Context context;

    TextView vwvDescription ;
    TextView vwvPersonLeader;
    TextView vwvInitialDate  ;
    TextView vwvFinalDate;

    Integer idGroupLeader;
    GroupLeaderDAO groupLeaderDAO;

    GroupLeader groupLeaderView = new GroupLeader();

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
        myView = inflater.inflate(R.layout.fragment_group_view, container, false);

        Bundle data = getArguments();
        idGroupLeader = data.getInt("idGroupLeader");

        // Get/set competition
        try {
            groupLeaderDAO = getGroupLeaderView(idGroupLeader);
            groupLeaderView = groupLeaderDAO.selectId(idGroupLeader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        displayGroupLeader(groupLeaderView);

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
    private GroupLeaderDAO getGroupLeaderView(Integer id) throws Exception {
        // Create PersonDAO
        GroupLeaderDAO dao = new GroupLeaderDAO(context);
        return dao;
    }



    private void displayGroupLeader(GroupLeader groupLeaderView) {

        vwvDescription = (TextView) myView.findViewById(R.id.vwvDescription);
        vwvPersonLeader=(TextView) myView.findViewById(R.id.vwvLeaderPerson);
        vwvInitialDate  = (TextView) myView.findViewById(R.id.vwvInitialDate);
        vwvFinalDate=(TextView) myView.findViewById(R.id.vwvFinalDate);

        vwvDescription.setText(groupLeaderView.getGroupName());
        vwvPersonLeader.setText(groupLeaderView.getIdPersonLeader());
        vwvInitialDate.setText(Util.ConvertDateToString(groupLeaderView.getInitialDate()));  ;
        vwvFinalDate.setText(Util.ConvertDateToString( groupLeaderView.getFinalDate()));

    }



    public void createNewGroupLeader(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new GroupFragment()).commit();
    }

    public void returnToGroup_Book() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new GroupFragment_Book()).commit();
    }
}
