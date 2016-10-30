package com.example.luizh.projeto_eorl;

import android.os.Bundle;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.luizh.projeto_eorl.domain.util.Message;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageActivity extends UtilActivity implements  DatabaseReference.CompletionListener{

    private Message message;
    private EditText title;
    private EditText text;
    private Spinner spinnerSubject;
    private ArrayAdapter<String> adpSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
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

    }

    public void init()
    {
        title = (EditText)findViewById(R.id.edtTitleMessage);
        text = (EditText)findViewById(R.id.edtTextMessage);
        spinnerSubject = (Spinner)findViewById(R.id.spinner);
        progressBar = (ProgressBar)findViewById(R.id.message_activity_progressBar);
        createAdapter();
    }


    public void initMessage()
    {
        message = new Message();
        message.setTitle(title.getText().toString());
        message.setText(text.getText().toString());
        message.setDate(getDateTime());
        message.setSubject(spinnerSubject.getSelectedItem().toString());
    }

    public String getDateTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(new Date()).toString();
    }

    public void sendMesssage(View view) {

        if(title.getText().toString().isEmpty())
        {
            showToast("Adicione pelo menos um título");
        }
        else
        {
            openProgressBar();
            FirebaseCrash.log("MessageActivity:clickListener:button:sendMesssage");
            initMessage();
            message.saveDB(MessageActivity.this);
        }
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

        if(databaseError == null)
        {
           showToast("Mensagem enviada com sucesso!");
            finish();
        }
        else
        {
            FirebaseCrash.report(databaseError.toException() );
            showToast("Erro ao enviar mensagem. Tente novamente!");
        }

        closeProgressBar();

    }

    public void createAdapter()
    {
        adpSubject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adpSubject.add("Reclamação");
        adpSubject.add("Problemas com a App");
        adpSubject.add("Sugestōes");
        adpSubject.add("Outros");
        spinnerSubject.setAdapter(adpSubject);
    }
}
