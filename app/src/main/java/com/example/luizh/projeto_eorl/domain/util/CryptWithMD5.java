package com.example.luizh.projeto_eorl.domain.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 * Created by luizh on 07/05/2016.
 */

// FONTE: http://stackoverflow.com/a/14201817/2578331

public class CryptWithMD5
{
    private static MessageDigest md;

    public static String cryptWithMD5(String pass)
    {
        try
        {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }

            return sb.toString();
        }
        catch (NoSuchAlgorithmException ex)
        {
            Log.e("LOG", Level.SEVERE+" - "+ex);
        }

        return null;
    }
}
