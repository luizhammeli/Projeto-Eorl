package com.example.luizh.projeto_eorl;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.luizh.projeto_eorl.adapter.DiagnosisAdapter;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class DiagnosisActivity extends UtilActivity {

    ListView listView;
    ArrayList<Diagnosis> list;
    DiagnosisAdapter diagnosisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
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

                Intent intent = new Intent(DiagnosisActivity.this, DiagnosisContentActivity.class);
                intent.putExtra("Diagnosis", list.get(i));
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }



    public void init()
    {
        progressBar = (ProgressBar)findViewById(R.id.diagnosis_activity_progressBar);
        openProgressBar();
        list = new ArrayList<Diagnosis>();
        list = parseJson(CustomApplication.getInstance().getJsonMainFile());
        diagnosisAdapter = new DiagnosisAdapter(this, list);
        listView = (ListView)findViewById(R.id.diagnosis_activity_listView);
        listView.setAdapter(diagnosisAdapter);

        closeProgressBar();
    }


    public ArrayList<Diagnosis> parseJson(String result)
    {
        ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();
        Diagnosis diagnosis;

        try
        {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jasonArray = jsonObject.getJSONArray("DIAGNOSTICO");

            for(int a=0;a<jasonArray.length(); a++)
            {
                diagnosis = new Diagnosis();
                jsonObject = jasonArray.getJSONObject(a);

                diagnosis.setNome(jsonObject.optString("NOME"));
                diagnosis.setQuadroClinico(jsonObject.optString("QUADRO_CLINICO"));
                diagnosis.setAlergia(jsonObject.optString("ALERGIA"));
                diagnosis.setAntibiotico(jsonObject.optString("ANTIBIOTICO"));
                diagnosis.setAtencao(jsonObject.optString("ATENCAO"));
                diagnosis.setDesc(jsonObject.optString("DESC"));
                diagnosis.setExamefisico(jsonObject.optString("EXAME_FISICO"));
                diagnosis.setMedicacoesAssociadas(jsonObject.optString("MEDICACOES_ASSOCIADAS"));
                list.add(diagnosis);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_searchable_diagnosis_activuty, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_searchable_activity).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
       @Override
       public boolean onQueryTextSubmit(String query) {
           return false;
       }

       @Override
       public boolean onQueryTextChange(String query) {

           diagnosisAdapter.filter(query);
           listView.invalidate();

           return true;
       }
   });

        return true;
    }
}
