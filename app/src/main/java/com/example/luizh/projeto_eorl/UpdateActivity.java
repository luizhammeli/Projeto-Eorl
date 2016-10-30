package com.example.luizh.projeto_eorl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.luizh.projeto_eorl.domain.util.DateUtil;
import com.example.luizh.projeto_eorl.domain.util.User;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class UpdateActivity extends UtilActivity implements ValueEventListener,  DatabaseReference.CompletionListener{

    private EditText edtDate;
    private EditText edtNome;
    private EditText edtSenha;
    private String strDate;
    private String email;
    private User user;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
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
        edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    sendaDate();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClickDataNascimento (android.view.View view)
    {
        sendaDate();
    }

    class setDateListener implements DatePickerDialog.OnDateSetListener
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            Date dataNascimento = DateUtil.getData(year, monthOfYear + 1, dayOfMonth);
            strDate = DateUtil.getString(year, monthOfYear + 1,  dayOfMonth);
            edtDate.setText(strDate);
        }
    }

    public void sendaDate()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateActivity.this, new setDateListener(), year, month, day);
        datePickerDialog.show();
    }

    public void init()
    {
        edtDate = (EditText) findViewById(R.id.EdtNovaDataNascimento);
        edtNome = (EditText)findViewById(R.id.actNovoNome);
        edtSenha = (EditText)findViewById(R.id.edtSenhaAtual);
        progressBar = (ProgressBar) findViewById(R.id.update_progressBar);
        openProgressBar();
        user = new User();
        user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
        user.contextDataDB(this);
    }


    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

        if(databaseError != null)
        {
            FirebaseCrash.report(databaseError.toException() );
            showToast("Erro ao atualizar dados.");
        }
        else
        {
            showToast("Atualização realizada com sucesso.");
            finish();
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot)
    {
        openProgressBar();

        User u = dataSnapshot.getValue(User.class);

        edtDate.setText(u.getDataNascimento());
        edtNome.setText(u.getNome());
        email = u.getEmail();
        closeProgressBar();
    }

    public void Update(android.view.View view)
    {
        FirebaseCrash.log("UpdateActivity:clickListener:button:Update");
        updateUserValues();
        verifyData();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void updateUserValues()
    {
        user.setNome(edtNome.getText().toString());
        user.setEmail(email);
        user.setDataNascimento(edtDate.getText().toString());
    }

    public void verifyData(){
        if(edtSenha.getText().toString().isEmpty()){

            showToast("Favor Preencher Todos os campos");
        }
        else {
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = mAuth.getCurrentUser();

            String email = mAuth.getCurrentUser().getEmail();
            openProgressBar();

            AuthCredential credencial = EmailAuthProvider.getCredential(email, edtSenha.getText().toString());

            firebaseUser.reauthenticate(credencial)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                user.UpdateDB(UpdateActivity.this);
                                closeProgressBar();

                            } else {
                                closeProgressBar();
                                showToast("Senha incorreta.");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseCrash.report(e);
                }
            });
        }
    }
}
