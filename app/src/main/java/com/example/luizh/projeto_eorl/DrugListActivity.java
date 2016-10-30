package com.example.luizh.projeto_eorl;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.luizh.projeto_eorl.adapter.DrugListAdapter;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;
import com.example.luizh.projeto_eorl.domain.util.Drug;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DrugListActivity extends UtilActivity {

    ListView listView;
    ArrayList<Drug> drugList;
    DrugListAdapter drugListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_list);
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

                Intent intent = new Intent(DrugListActivity.this, DrugContent.class);
                intent.putExtra("Drug", drugList.get(i));
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
        progressBar = (ProgressBar)findViewById(R.id.drug_list_activity_progressBar);
        openProgressBar();
        drugList = new ArrayList<Drug>();
        drugList = parseJson(CustomApplication.getInstance().getJsonMainFile());
        drugListAdapter = new DrugListAdapter(this, drugList);
        listView = (ListView)findViewById(R.id.drug_list_listView);
        listView.setAdapter(drugListAdapter);
        closeProgressBar();
    }

    public ArrayList<Drug> parseJson(String result)
    {
        ArrayList<Drug> list = new ArrayList<Drug>();
        Drug drug;

        try
        {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jasonArray = jsonObject.getJSONArray("BULA");

            for(int a=0;a<jasonArray.length(); a++)
            {
                drug = new Drug();
                jsonObject = jasonArray.getJSONObject(a);

                drug.setName(jsonObject.optString("NOME"));
                drug.setPresentation(jsonObject.optString("APRESENTACAO"));
                drug.setAge(jsonObject.optString("IDADE"));
                drug.setContradiction(jsonObject.optString("CONTRADICAO"));
                drug.setDosage(jsonObject.optString("POSOLOGIA"));
                drug.setPregnancy(jsonObject.optString("GRAVIDEZ"));
                drug.setLactation(jsonObject.optString("LACTACAO"));
                drug.setDrugsInteractions(jsonObject.optString("INTERACOES_MEDICAMENTOSAS"));
                drug.setReactions(jsonObject.optString("REACOES_ADVERSAS"));
                drug.setFailure(jsonObject.optString("INSUFICIENCIA_RENAL"));
                drug.setImportantInformation(jsonObject.optString("INFORMACOES_IMPORTANTES"));
                list.add(drug);
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

                drugListAdapter.filter(query);
                listView.invalidate();

                return true;
            }
        });

        return true;
    }

}
