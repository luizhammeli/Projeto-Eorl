package com.example.luizh.projeto_eorl.domain.util;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by luizh on 07/05/2016.
 */
public final class LibraryClass
{
    public static String PREF = "com.example.luizh.projeto_eorl.PREF";


    static public void saveSp(Context context, String key, String value)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    static public String getSp(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String token = sp.getString(key, "");
        return (token);
    }
}
