package com.example.luizh.projeto_eorl.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.luizh.projeto_eorl.Fragments.DifferentialDiagnosisFragment;
import com.example.luizh.projeto_eorl.Fragments.MainFragment;
import com.example.luizh.projeto_eorl.Fragments.TreatmentFragment;
import com.example.luizh.projeto_eorl.R;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;

import java.util.ArrayList;

/**
 * Created by mac on 01/08/16.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    Context context;
    Diagnosis diagnosis;

    public TabsAdapter(Context context, FragmentManager fm, Diagnosis diagnosis) {

        super(fm);
        this.context = context;
        this.diagnosis = diagnosis;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
        {
            return context.getString(R.string.str_main_diag);
        }
        else
        {
            if(position == 1)
            {
                return context.getString(R.string.str_treatment);
            }
        }

        return context.getString(R.string.str_dif_diag);
    }

    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
        {
            MainFragment mainFragment = new MainFragment();
            return mainFragment;
        }
        else
        {
            if(position == 1)
            {
                return new TreatmentFragment();
            }
        }

        return new DifferentialDiagnosisFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
