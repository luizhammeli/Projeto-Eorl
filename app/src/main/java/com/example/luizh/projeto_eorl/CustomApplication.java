package com.example.luizh.projeto_eorl;

import android.app.Application;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by luizh on 07/05/2016.
 */
public class CustomApplication extends Application
{
    private String jsonMainFile;
    private static CustomApplication instance = null;

    public static CustomApplication getInstance()
    {
        return instance;
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
        Resources resources = this.getResources();
        InputStream in = resources.openRawResource(R.raw.eorl);
        setJsonMainFile(getStringFromInputStream(in));
        instance = this;
    }


    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        try
        {
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String getJsonMainFile() {
        return jsonMainFile;
    }

    public void setJsonMainFile(String jsonMainFile) {
        this.jsonMainFile = jsonMainFile;
    }
}
