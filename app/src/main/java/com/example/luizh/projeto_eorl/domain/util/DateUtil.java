package com.example.luizh.projeto_eorl.domain.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luizh on 12/05/2016.
 */
public class DateUtil
{
    public static Date getData(int year, int monthOfYear, int dayOfMonth)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth );
        return calendar.getTime();
    }

    public static String getString(int year, int monthOfYear, int dayOfMonth)
    {
        String strData, strDia, strMes;

        strDia =  Integer.toString(dayOfMonth);
        strMes = Integer.toString(monthOfYear);

        if(dayOfMonth < 10 || monthOfYear < 10)
        {
            if(dayOfMonth < 10)
            {
                strDia = '0' + Integer.toString(dayOfMonth);
            }

            if(monthOfYear < 10)
            {
                strMes = '0' + Integer.toString(monthOfYear);
            }

        }

        strData = strDia + '/' + strMes + '/' + Integer.toString(year);

        return strData;

    }
}
