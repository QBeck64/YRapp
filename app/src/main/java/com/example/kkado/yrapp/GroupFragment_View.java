package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.CompetitionDAO;
import com.example.kkado.yrapp.dao.GroupLeaderDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.entity.GroupLeader;
import com.example.kkado.yrapp.entity.Person;
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

        ImageButton btnReturn = (ImageButton) myView.findViewById(R.id.btnReturn);
        try {
            GroupLeader groupLeaderView =   getGroupLeaderView();

            displayGroupLeader(groupLeaderView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToGroup_Book();
            }
        });

        return myView;
    }

    /***
     *
     * @return
     * @throws Exception
     */
    private GroupLeader getGroupLeaderView() throws Exception {

        GroupLeaderDAO groupLeaderDAO = new GroupLeaderDAO(context);
        GroupLeader   groupLeaderView = groupLeaderDAO.selectId(idGroupLeader);

        return groupLeaderView;
    }

    /**
     *
     * @param groupLeaderView
     */
    private void displayGroupLeader(GroupLeader groupLeaderView) {

        vwvDescription = (TextView) myView.findViewById(R.id.vwvDescription);
        vwvPersonLeader=(TextView) myView.findViewById(R.id.vwvLeaderPerson);
        vwvInitialDate  = (TextView) myView.findViewById(R.id.vwvInitialDate);
        vwvFinalDate=(TextView) myView.findViewById(R.id.vwvFinalDate);


        vwvDescription.setText(groupLeaderView.getGroupName());

        vwvPersonLeader.setText(groupLeaderView.getPersonLeader().getSurname() + "," + groupLeaderView.getPersonLeader().getName() );
        vwvInitialDate.setText(Util.ConvertDateToString(groupLeaderView.getInitialDate()));  ;
        vwvFinalDate.setText(Util.ConvertDateToString( groupLeaderView.getFinalDate()));

    }

    public void returnToGroup_Book() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new GroupFragment_Book()).commit();
    }
}
