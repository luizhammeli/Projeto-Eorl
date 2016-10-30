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
public class DifferentialDiagnosisFragment extends Fragment {


    TextView tvAicntibiot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.differential_diagnosis_fragment, container, false);

        tvAicntibiot = (TextView)view.findViewById(R.id.tv_desc);
        tvAicntibiot.setText(DiagnosisContentActivity.diagnosis.getDesc());

        return view;
    }
}
