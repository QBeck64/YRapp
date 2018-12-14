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

import com.example.kkado.yrapp.dao.InvoicingDAO;
import com.example.kkado.yrapp.entity.Invoicing;
import com.example.kkado.yrapp.helper.Util;

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

    TextView vwvInvoicing;
    TextView vwvPerson;
    TextView vwvInitialDate;
    TextView vwvFinalDate;

    Integer idInvoicing;

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
        myView = inflater.inflate(R.layout.fragment_invoicing_view, container, false);

        Bundle data = getArguments();
        idInvoicing = data.getInt("idInvoicing");

        ImageButton btnReturn = (ImageButton) myView.findViewById(R.id.btnReturn);

        try {
            Invoicing invoicingView = getInvoicingView();
            displayInvoicing(invoicingView);

        } catch (Exception e) {
            e.printStackTrace();
        }


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToInvoicing_Book();
            }
        });
        return myView;
    }

    /**
     * @return
     * @throws Exception
     */
    private Invoicing getInvoicingView() throws Exception {

        InvoicingDAO invoicingDAO = new InvoicingDAO(context);
        Invoicing invoicingView = invoicingDAO.selectId(idInvoicing);

        return invoicingView;
    }

    /**
     * @param invoicingView
     */
    private void displayInvoicing(Invoicing invoicingView) {

        vwvInvoicing = (TextView) myView.findViewById(R.id.vwvInvoicing);
        vwvPerson = (TextView) myView.findViewById(R.id.vwvPerson);
        vwvInitialDate = (TextView) myView.findViewById(R.id.vwvInitialDate);
        vwvFinalDate = (TextView) myView.findViewById(R.id.vwvFinalDate);

        vwvInvoicing.setText(Float.toString(invoicingView.getInvoicing()));
        vwvPerson.setText(invoicingView.getPerson().getSurname() + "," + invoicingView.getPerson().getName());
        vwvInitialDate.setText(Util.ConvertDateToString(invoicingView.getPeriod().getInitialDate()));
        ;
        vwvFinalDate.setText(Util.ConvertDateToString(invoicingView.getPeriod().getFinalDate()));
    }

    /**
     *
     */
    public void returnToInvoicing_Book() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, new InvoicingFragment_Book()).commit();
    }
}
