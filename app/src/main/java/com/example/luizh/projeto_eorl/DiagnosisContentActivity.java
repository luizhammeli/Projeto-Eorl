package com.example.luizh.projeto_eorl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luizh.projeto_eorl.adapter.TabsAdapter;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;

public class DiagnosisContentActivity extends UtilActivity {

    ViewPager viewPager;
    Bundle bundle;
    public static Diagnosis diagnosis;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        init();
        toolbar.setTitle(diagnosis.getNome());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.seta1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    public void init()
    {
        bundle = getIntent().getExtras();
        diagnosis = (Diagnosis) bundle.getSerializable("Diagnosis");
        initTabs();
    }

    public void initTabs()
    {
        viewPager = (ViewPager)findViewById(R.id.viwerPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(DiagnosisContentActivity.this , getSupportFragmentManager(), diagnosis));
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(this, android.R.color.white);
        int cor2 = ContextCompat.getColor(this, R.color.colorAccent);
        tabLayout.setSelectedTabIndicatorColor(cor2);
        tabLayout.setTabTextColors(cor, cor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(diagnosis.getNome());
    }

}
