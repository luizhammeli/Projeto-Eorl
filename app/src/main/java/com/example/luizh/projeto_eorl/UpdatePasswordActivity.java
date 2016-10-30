package com.example.luizh.projeto_eorl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.domain.util.UtilActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;

public class UpdatePasswordActivity extends UtilActivity {

    private AutoCompleteTextView actConfOldPassword;
    private AutoCompleteTextView actNewPassword;
    private AutoCompleteTextView actConfNewPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.seta1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();

        initView();
    }

    public void initView()
    {
        actConfOldPassword = (AutoCompleteTextView) findViewById(R.id.actOldPassword);
        actNewPassword = (AutoCompleteTextView) findViewById(R.id.actNewPassword);
        actConfNewPassword = (AutoCompleteTextView) findViewById(R.id.actConfNewPassword);
        progressBar = (ProgressBar) findViewById(R.id.sig_up_progressBar);
    }

    public void updatePassword()
    {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if(validatePassword(actNewPassword.getText().toString(), actConfNewPassword.getText().toString())) {

            firebaseUser.updatePassword(actNewPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                showToast("Senha atualizada com sucesso");
                                closeProgressBar();
                                finish();
                            }
                            else {

                                closeProgressBar();
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

    public void verifyPassword(android.view.View view){

        FirebaseCrash.log("UpdatePasswordActivity:clickListener:button:verifyPassword");

        if(actConfOldPassword.getText().toString().isEmpty()){

            showToast("Favor Preencher Todos os campos");
        }
        else {
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = mAuth.getCurrentUser();

            String email = mAuth.getCurrentUser().getEmail();
            openProgressBar();

            AuthCredential credencial = EmailAuthProvider.getCredential(email, actConfOldPassword.getText().toString());

            firebaseUser.reauthenticate(credencial)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                updatePassword();
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
