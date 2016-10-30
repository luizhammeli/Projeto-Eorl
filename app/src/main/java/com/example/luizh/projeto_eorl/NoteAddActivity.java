package com.example.luizh.projeto_eorl;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.example.luizh.projeto_eorl.domain.util.Note;
import com.example.luizh.projeto_eorl.domain.util.User;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NoteAddActivity extends UtilActivity implements ValueEventListener, DatabaseReference.CompletionListener{

    Note note;
    EditText edtTitle;
    EditText edtText;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        toolbar.setNavigationIcon(R.drawable.seta1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initViews()
    {
        edtTitle = (EditText)findViewById(R.id.edtTitle);
        edtText = (EditText)findViewById(R.id.edtText);
        progressBar = (ProgressBar)findViewById(R.id.note_progressBar);

        bundle = getIntent().getExtras();

        if((bundle != null) && (bundle.containsKey("Note")))
        {
            note = (Note)bundle.getSerializable("Note");
            edtText.setText(note.getText());
            edtTitle.setText(note.getTitle());
        }
        else
        {
            note = new Note();
        }

        Note.contextDataDB(this);
    }

    public void initNote()
    {
        note.setText(edtText.getText().toString().replace(",","|"));
        note.setTitle(edtTitle.getText().toString().replace(", ","|"));

    }

    public void saveData(android.view.View view)
    {
        FirebaseCrash.log("NoteAddActivity:clickListener:button:saveData");

        if(checksBlankFields())
        {
            openProgressBar();
            initNote();
            note.saveDB(NoteAddActivity.this);
        }
        else
        {
            showToast("Favor preencher todos os campos");
        }
    }

    public void removeData(android.view.View view)
    {
        FirebaseCrash.log("NoteAddActivity:clickListener:button:removeData");

        if((bundle != null) && (bundle.containsKey("Note")))
        {
            openProgressBar();
            initNote();
            note.removeDB(NoteAddActivity.this);

        }
        else
        {
            showToast("Opção Inválida");
        }
    }

    public void callCamera(android.view.View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

        closeProgressBar();

        if(databaseError != null)
        {
            FirebaseCrash.report( databaseError.toException() );
            showToast("Erro ao salvar dados.");
        }
        else
        {
            showToast("Operaçao realizada com sucesso");
            finish();
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot)
    {
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public boolean checksBlankFields()
    {
        if(!edtText.getText().toString().isEmpty() && !edtText.getText().toString().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
