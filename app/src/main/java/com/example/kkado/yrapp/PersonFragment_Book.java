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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.kkado.yrapp.dao.CompetitionParticipantDAO;
import com.example.kkado.yrapp.dao.InvoicingDAO;
import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.dao.TeamGroupLeaderDAO;
import com.example.kkado.yrapp.entity.CompetitionParticipant;
import com.example.kkado.yrapp.entity.Invoicing;
import com.example.kkado.yrapp.entity.Person;
import com.example.kkado.yrapp.entity.TeamGroupLeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Using the PersonDAO databses, a ListView is created and an ArrayList is used to display
 * all the persons currently stored in the PersonDAO. An onItemClick function can occur if a person is selected, hwich will then start the
 * PersonFragment_View Activity.
 */
public class PersonFragment_Book extends Fragment {
    View myView;
    private Context context;
    private ListView itemView;
    private List<Person> itens = new ArrayList<>();
    private ArrayAdapter<Person> arrayAdapter;

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
        myView = inflater.inflate(R.layout.fragment_person_book, container, false);

        itemView = (ListView) myView.findViewById(R.id.lstViewPerson);
        edtFilter = (EditText) myView.findViewById(R.id.edtFilterPerson);
        ImageButton bt = (ImageButton)myView.findViewById(R.id.btnCreatePerson);
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
                createNewPerson();
            }
        });
        return myView;
    }

    /**
     * @param
     */
    public void createNewPerson() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new PersonFragment()).commit();
    }

    /**
     * @throws Exception
     */
    private void initializeList() throws Exception {
        PersonDAO dao = new PersonDAO(context);
        itens.clear();
        itens = dao.select();

        arrayAdapter = new ArrayAdapter<Person>(context, android.R.layout.simple_list_item_1, itens);
        itemView.setAdapter(arrayAdapter);
        itemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            PersonFragment_View personFragment_view = new PersonFragment_View();

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                Person newPerson = (Person) arg0.getItemAtPosition(position);
//
                Bundle data = new Bundle();//create bundle instance
                data.putInt("personID", newPerson.getIdPerson());//put string to pass with a key value
                personFragment_view.setArguments(data);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_fragment, personFragment_view).commit();

            }
        });
    }
}
