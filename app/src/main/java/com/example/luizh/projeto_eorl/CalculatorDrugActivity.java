package com.example.luizh.projeto_eorl;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.luizh.projeto_eorl.adapter.CalculatorDrugAdapter;
import com.example.luizh.projeto_eorl.adapter.DiagnosisAdapter;
import com.example.luizh.projeto_eorl.domain.util.CalculatorDrug;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.AlertDialog;

public class CalculatorDrugActivity extends UtilActivity {

    ListView listView;
    ArrayList<CalculatorDrug> list;
    int weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_drug);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.seta1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        init();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                calculate(list.get(i));

                AlertDialog.Builder dlgResult = new AlertDialog.Builder(CalculatorDrugActivity.this);

                dlgResult.setTitle("Resultado: ");
                dlgResult.setMessage(String.format("%.1f", list.get(i).getResult()) + " ml (" + Integer.toString(list.get(i).getDailyFrequency()) + "x/dia)");
                dlgResult.setNeutralButton("Ok", null);
                dlgResult.show();
            }
        });
    }

    private void calculate(CalculatorDrug calculatorDrug) {

        double totalDose = (double) ((weight * calculatorDrug.getDose()) / calculatorDrug.getDailyFrequency());
        calculatorDrug.setResult((totalDose  * calculatorDrug.getFormML()) / calculatorDrug.getFormMG());
    }

    private void init() {

        progressBar = (ProgressBar)findViewById(R.id.calculator_drug_activity_progressBar);
        openProgressBar();

        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("WEIGHT")))
        {
            weight = bundle.getInt("WEIGHT");
        }

        list = new ArrayList<CalculatorDrug>();

        list = parseJson(CustomApplication.getInstance().getJsonMainFile());

        listView = (ListView)findViewById(R.id.lstDrugCalculator);

        listView.setAdapter(new CalculatorDrugAdapter(this, list));

        closeProgressBar();
    }


    public ArrayList<CalculatorDrug> parseJson(String result)
    {
        ArrayList<CalculatorDrug> list = new ArrayList<CalculatorDrug>();
        CalculatorDrug calculatorDrug;

        try
        {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jasonArray = jsonObject.getJSONArray("CALCULADORA");

            for(int a=0;a<jasonArray.length(); a++)
            {
                calculatorDrug = new CalculatorDrug();
                jsonObject = jasonArray.getJSONObject(a);

                calculatorDrug.setName(jsonObject.optString("NOME"));
                calculatorDrug.setDose(Integer.parseInt(jsonObject.optString("DOSE")));
                calculatorDrug.setFormMG(Integer.parseInt(jsonObject.optString("MG")));
                calculatorDrug.setFormML(Integer.parseInt(jsonObject.optString("ML")));
                calculatorDrug.setDailyFrequency(Integer.parseInt(jsonObject.optString("FREQUENCIA_DIARIA")));

                list.add(calculatorDrug);

            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return list;
    }

}
