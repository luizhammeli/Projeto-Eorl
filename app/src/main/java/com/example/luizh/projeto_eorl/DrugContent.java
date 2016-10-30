package com.example.luizh.projeto_eorl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.domain.util.Drug;

public class DrugContent extends AppCompatActivity {

    Drug drug;
    TextView txvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        init();
        toolbar.setTitle(drug.getName());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.seta1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        initViews();
    }

    private void initViews() {

        TextView txvAge = (TextView)findViewById(R.id.tv_age);
        TextView txvPresentation = (TextView)findViewById(R.id.tv_presentation);
        TextView txvContradiction = (TextView)findViewById(R.id.tv_contradiction);
        TextView txtPregnancy = (TextView)findViewById(R.id.tv_pregnancy);
        TextView txvLactation = (TextView)findViewById(R.id.tv_lactation);
        TextView txvDrugsInteractions = (TextView)findViewById(R.id.tv_drugs_interactions);
        TextView txvReactions = (TextView)findViewById(R.id.tv_reactions);
        TextView txvFailure = (TextView)findViewById(R.id.tv_failure);
        TextView txvImportantInformation = (TextView)findViewById(R.id.tv_important_information);
        TextView txvDosage = (TextView)findViewById(R.id.tv_dosage);

        if(drug!=null) {

            txvAge.setText(drug.getAge());
            txvPresentation.setText(drug.getPresentation());
            txvContradiction.setText(drug.getContradiction());
            txtPregnancy.setText(drug.getPregnancy());
            txvLactation.setText(drug.getLactation());
            txvDrugsInteractions.setText(drug.getDrugsInteractions());
            txvReactions.setText(drug.getReactions());
            txvFailure.setText(drug.getFailure());
            txvImportantInformation.setText(drug.getImportantInformation());
            txvDosage.setText(drug.getDosage());
        }

    }

    private void init() {

        Bundle bundle = getIntent().getExtras();
        drug = (Drug) bundle.getSerializable("Drug");
    }

}
