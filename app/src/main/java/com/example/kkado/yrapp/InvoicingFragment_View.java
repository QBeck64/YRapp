package com.example.kkado.yrapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kkado.yrapp.dao.InvoicingDAO;
import com.example.kkado.yrapp.entity.Invoicing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class InvoicingFragment_View extends Fragment {
    private static final String TAG = "InvoicingView";
    View myView;
    private Context context;

    TextView vwvDescription ;
    TextView vwvPersonLeader;
    TextView vwvInitialDate  ;
    TextView vwvFinalDate;

    Integer idInvoicing;
    InvoicingDAO invoicingDAO;

    Invoicing invoicingView = new Invoicing();

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
        idInvoicing = data.getInt("idInvoicing");

        // Get/set competition
        try {
            invoicingDAO = getInvoicingView(idInvoicing);
            invoicingView = invoicingDAO.selectId(idInvoicing);
        } catch (Exception e) {
            e.printStackTrace();
        }

        displayInvoicing(invoicingView);

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
    private InvoicingDAO getInvoicingView(Integer id) throws Exception {
        // Create PersonDAO
        InvoicingDAO dao = new InvoicingDAO(context);
        return dao;
    }



    private void displayInvoicing(Invoicing invoicingView) {

        vwvDescription = (TextView) myView.findViewById(R.id.vwvDescription);
        vwvPersonLeader=(TextView) myView.findViewById(R.id.vwvLeaderPerson);
        vwvInitialDate  = (TextView) myView.findViewById(R.id.vwvInitialDate);
        vwvFinalDate=(TextView) myView.findViewById(R.id.vwvFinalDate);

//        vwvDescription.setText(invoicingView.getGroupName());
//        vwvPersonLeader.setText(invoicingView.getIdPersonLeader());
//        vwvInitialDate.setText(Util.ConvertDateToString(groupLeaderView.getInitialDate()));  ;
//        vwvFinalDate.setText(Util.ConvertDateToString( groupLeaderView.getFinalDate()));

    }



    public void createNewInvoicing(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new GroupFragment()).commit();
    }

    public void returnToGroup_Book() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new GroupFragment_Book()).commit();
    }
}
