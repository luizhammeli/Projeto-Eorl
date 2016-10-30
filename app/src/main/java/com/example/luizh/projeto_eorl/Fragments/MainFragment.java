package com.example.luizh.projeto_eorl.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.luizh.projeto_eorl.DiagnosisContentActivity;
import com.example.luizh.projeto_eorl.R;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;

/**
 * Created by mac on 04/08/16.
 */
public class MainFragment extends android.support.v4.app.Fragment {

    private TextView exam;
    private TextView clinicalCase;
    private TextView attention;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);

        exam = (TextView)view.findViewById(R.id.tv_exam);
        clinicalCase = (TextView)view.findViewById(R.id.tv_clinical_case);
        attention = (TextView)view.findViewById(R.id.tv_attention);

        exam.setText(DiagnosisContentActivity.diagnosis.getExamefisico());
        clinicalCase.setText(DiagnosisContentActivity.diagnosis.getQuadroClinico());
        attention.setText(DiagnosisContentActivity.diagnosis.getAtencao());

        return view;
    }
}
