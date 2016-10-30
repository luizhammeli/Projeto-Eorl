package com.example.luizh.projeto_eorl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;

public class ResetActivity extends AppCompatActivity
{
    Button btnResetEmail;
    EditText edtEmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        edtEmail = (EditText)findViewById(R.id.actResetEmail);

        mAuth =  FirebaseAuth.getInstance();

    }

    public void sendResetEmail(android.view.View view)
    {
        FirebaseCrash.log("LoginActivity:clickListener:button:sendResetEmail");

        if(edtEmail.getText().toString().isEmpty())
        {
            edtEmail.setError("");
            Toast.makeText(ResetActivity.this, "Favor preencher o campo E-mail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.sendPasswordResetEmail(edtEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        edtEmail.setText("");
                        Toast.makeText(ResetActivity.this, "E-mail enviado", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ResetActivity.this, "Falha ao envia e-mail", Toast.LENGTH_SHORT).show();
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
