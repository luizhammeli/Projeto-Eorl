package com.example.luizh.projeto_eorl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luizh.projeto_eorl.domain.util.UtilActivity;

public class CalculatorMenu extends UtilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.seta1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void callCorticoidsCalculatorActivity(android.view.View view)
    {
        Intent intent = new Intent(CalculatorMenu.this, CalculatorCorticoidsActivity.class);
        startActivity(intent);
    }

    public void callDrugCalculatorActivity(android.view.View view)
    {
        Intent intent = new Intent(CalculatorMenu.this, CalculatorWeightActivity.class);
        startActivity(intent);
    }
}
