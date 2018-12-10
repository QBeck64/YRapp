package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kkado.yrapp.dao.CompetitionDAO;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class CompetitionFragment_Book extends Fragment
{
    View myView;
    private Context context;
    private ListView itemView;
    private List<Competition> itens = new ArrayList<>();
    private ArrayAdapter<Competition> arrayAdapter;
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_competition_book, container, false);

        itemView = (ListView)myView.findViewById(R.id.competitionView);
        try {
            initializeList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return myView;
    }

    /**
     *
     * @param
     */
    public void createCompetition(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new CompetitionFragment()).commit();
    }

    private void initializeList() throws Exception {
        CompetitionDAO dao = new CompetitionDAO(context);
        itens.clear();
        itens = dao.select();

        arrayAdapter = new ArrayAdapter<Competition>(context, android.R.layout.simple_list_item_1, itens);
        itemView.setAdapter(arrayAdapter);

    }
}
