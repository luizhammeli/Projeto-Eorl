package com.example.luizh.projeto_eorl.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.DiagnosisContentActivity;
import com.example.luizh.projeto_eorl.R;

/**
 * Created by mac on 04/08/16.
 */
public class TreatmentFragment extends Fragment {

    TextView tvAntibiotic;
    TextView tvAllergy;
    TextView tvMedAssoc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.treatment_fragment, container, false);
        tvAntibiotic = (TextView)view.findViewById(R.id.tv_antibiotic);
        tvAllergy = (TextView)view.findViewById(R.id.tv_allergy);
        tvMedAssoc = (TextView)view.findViewById(R.id.tv_med_assoc);

        tvAntibiotic.setText(DiagnosisContentActivity.diagnosis.getAntibiotico());
        tvAllergy.setText(DiagnosisContentActivity.diagnosis.getAlergia());
        tvMedAssoc.setText(DiagnosisContentActivity.diagnosis.getMedicacoesAssociadas());

        return view;
    }
}
