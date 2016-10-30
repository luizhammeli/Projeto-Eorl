package com.example.luizh.projeto_eorl;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.luizh.projeto_eorl.domain.util.Corticoids;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CalculatorCorticoidsActivity extends UtilActivity {

    private Spinner spnIn;
    private Spinner spnFor;
    private EditText edtDose;
    ArrayAdapter<String> adpNameCorticoids;
    ArrayList<Corticoids> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_corticoids);
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

    }

    private void init() {

        spnIn = (Spinner)findViewById(R.id.spinnerIn);
        spnFor = (Spinner)findViewById(R.id.spinnerFor);
        edtDose = (EditText)findViewById(R.id.edtDoseCorticoids);
        adpNameCorticoids = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpNameCorticoids.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list = parseJson(CustomApplication.getInstance().getJsonMainFile());

        spnFor.setAdapter(adpNameCorticoids);
        spnIn.setAdapter(adpNameCorticoids);

    }


    public ArrayList<Corticoids> parseJson(String result)
    {
        ArrayList<Corticoids> list = new ArrayList<>();
        Corticoids corticoids;

        try
        {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jasonArray = jsonObject.getJSONArray("CORTICOIDE");

            for(int a=0;a<jasonArray.length(); a++)
            {
                corticoids = new Corticoids();
                jsonObject = jasonArray.getJSONObject(a);

                corticoids.setName(jsonObject.optString("NOME"));
                corticoids.setValue(Integer.parseInt(jsonObject.optString("VALOR")));

                list.add(corticoids);
                adpNameCorticoids.add(list.get(a).getName());
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public void convertCorticoids(android.view.View view) {

        AlertDialog.Builder dlgResult = new AlertDialog.Builder(CalculatorCorticoidsActivity.this);

        if(edtDose.getText().toString().isEmpty())
        {
            showToast("Por favor, entre com um valor");
        }
        else
        {
            if(spnIn.getSelectedItemId() == spnFor.getSelectedItemId())
            {
                showToast("Por favor, os corticoides selecionados não podem ser iguais");

            }
            else
            {
                String result = calcular();
                dlgResult.setTitle("Resultado: ");
                dlgResult.setMessage(edtDose.getText().toString() + " mg" + " de "  + (list.get((int) spnIn.getSelectedItemId()).getName()) + " equivalem a " + result + " mg de " + (list.get((int) spnFor.getSelectedItemId()).getName()));
                dlgResult.setNeutralButton("Ok", null);
                dlgResult.show();
            }
        }
    }

    private String calcular() {

        int forValue = (list.get((int) spnFor.getSelectedItemId()).getValue());
        int inValue = (list.get((int) spnIn.getSelectedItemId()).getValue());
        int dose = Integer.parseInt(edtDose.getText().toString());

        double totalDose = (double) forValue / inValue;

        totalDose = totalDose * dose;

        return String.format("%.1f", totalDose);
    }
}
