package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kkado.yrapp.dao.PersonDAO;
import com.example.kkado.yrapp.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PersonFragment_Book extends Fragment
{
    View myView;
    private Context context;
    private ListView itemView;
    private List<Person> itens = new ArrayList<>();
    private ArrayAdapter<Person> arrayAdapter;

    private EditText edtFilter;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_person_book, container, false);

        itemView = (ListView)myView.findViewById(R.id.lstViewPerson);
        edtFilter = (EditText) myView.findViewById(R.id.edtFilterPerson);

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

        return myView;
    }

    /**
     *
     * @param
     */
    public void createNewPerson(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new PersonFragment()).commit();
    }

    private void initializeList() throws Exception {
        PersonDAO dao = new PersonDAO(context);
        itens.clear();
        itens = dao.select();

        arrayAdapter = new ArrayAdapter<Person>(context, android.R.layout.simple_list_item_1, itens);
        itemView.setAdapter(arrayAdapter);
        itemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                Person newPerson = (Person) arg0.getItemAtPosition(position);
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_fragment, new PersonFragment()).commit();
                Bundle data = new Bundle();//create bundle instance
                data.putString("key_value", "String to pass");//put string to pass with a key value

                Intent clickIntent = new Intent(context, PersonFragment.class);
                clickIntent.putExtra("PersonId", newPerson.getIdPerson());

                startActivity(clickIntent);
            }
        });
    }
}
