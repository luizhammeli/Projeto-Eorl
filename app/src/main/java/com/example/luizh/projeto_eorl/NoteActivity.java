package com.example.luizh.projeto_eorl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.luizh.projeto_eorl.adapter.NoteAdapter;
import com.example.luizh.projeto_eorl.domain.util.Note;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends UtilActivity implements ValueEventListener  {

    Note note;
    ListView list;
    List<Note> notesList;
    public static final String PAR_NOTE = "Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.seta1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initViews();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Note note = notesList.get(position);
                Intent intent = new Intent(NoteActivity.this, NoteAddActivity.class);
                intent.putExtra("Note", note);
                startActivityForResult(intent, 0);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(NoteActivity.this, NoteAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    public void init()
    {
        openProgressBar();
        Note.contextDataDB(NoteActivity.this);
    }

    public void initViews()
    {
        progressBar = (ProgressBar) findViewById(R.id.note_activity_progressBar);
        list = (ListView)findViewById(R.id.lstNotes);

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot)
    {
        if(dataSnapshot.getValue() != null) {
            notesList = parseDataSnapshot(dataSnapshot.getValue().toString());
            list.setAdapter(new NoteAdapter(this, notesList));
        }
        else
        {
            notesList = new ArrayList<Note>();
            list.setAdapter(new NoteAdapter(this, notesList));
        }

        closeProgressBar();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        closeProgressBar();
    }

    public List<Note> parseDataSnapshot(String data)
    {
        String [] dataSplitted;

        data = data.replace("{", "");
        data = data.replace("}", "");
        data = data.replace("=text=", "=");
        data = data.replace(", ", "-");
        data = data.replace("|",", ");
        dataSplitted = data.split("-");
        return Note.getNotesList(dataSplitted);
    }
}
