package com.example.luizh.projeto_eorl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.luizh.projeto_eorl.adapter.CalculatorWeightAdapter;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;

import java.util.ArrayList;
import java.util.List;

public class CalculatorWeightActivity extends UtilActivity {

    ListView listViewWeight;
    List<Integer> listWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_weight);
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

        listViewWeight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(CalculatorWeightActivity.this, CalculatorDrugActivity.class);
                intent.putExtra("WEIGHT", listWeight.get(i));
                startActivity(intent);

            }
        });

    }

    public void init()
    {
        listViewWeight = (ListView)findViewById(R.id.lstWeightCalculator);
        listWeight = new ArrayList<Integer>();
        insertWeightValues();
        listViewWeight.setAdapter(new CalculatorWeightAdapter(CalculatorWeightActivity.this, listWeight));
    }

    public void insertWeightValues()
    {
        for(int a = 2; a <= 40; a++)
        {
            listWeight.add(a);
        }
    }
}
