package com.example.kkado.yrapp.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    /**
     *
     * @param date
     * @return
     */
    public static String ConvertDateToString(Date date){
        if (date == null )
        return null;
        String  stringDate;

        try {

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
           stringDate = formatter.format(date);

        } catch (Exception e) {
            throw e;
        }

        return stringDate;
    }

    /**
     *
     * @param stringDate
     * @return
     * @throws Exception
     */
    public static Date ConvertStringToDate(String stringDate) throws Exception {
        if (stringDate == null || stringDate.equals(""))
            return null;
        Date date;
        try {

            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            date = formatter.parse(stringDate);

        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
}
