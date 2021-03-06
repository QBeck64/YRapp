package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.kkado.yrapp.dao.CompetitionDAO;
import com.example.kkado.yrapp.entity.Competition;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.helper.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class CompetitionFragment_Book extends Fragment {
    View myView;
    private Context context;
    private ListView itemView;
    private List<Competition> itens = new ArrayList<>();
    private ArrayAdapter<Competition> arrayAdapter;
    private EditText edtFilter;

    /**
     * @param context
     */
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
        myView = inflater.inflate(R.layout.fragment_competition_book, container, false);

        itemView = (ListView) myView.findViewById(R.id.lstViewCompetition);
        edtFilter = (EditText) myView.findViewById(R.id.edtFilterCompetition);
        ImageButton bt = (ImageButton)myView.findViewById(R.id.btnCreateCompetition);

        try {
            initializeList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        edtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCompetition();
            }
        });
        return myView;
    }

    /**
     * @param
     */
    public void createCompetition() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new CompetitionFragment()).commit();
    }

    /**
     * @throws Exception
     */
    private void initializeList() throws Exception {
        CompetitionDAO dao = new CompetitionDAO(context);
        itens.clear();
        itens = dao.select();

        arrayAdapter = new ArrayAdapter<Competition>(context, android.R.layout.simple_list_item_1, itens);
        itemView.setAdapter(arrayAdapter);
        itemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            CompetitionFragment_View competitionFragment_view = new CompetitionFragment_View();
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                Competition newCompetition = (Competition) arg0.getItemAtPosition(position);
//
                Bundle data = new Bundle();//create bundle instance
                data.putInt("idCompetition", newCompetition.getIdCompetition());//put string to pass with a key value
                competitionFragment_view.setArguments(data);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_fragment, competitionFragment_view).commit();

            }
        });
    }
}
