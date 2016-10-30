package com.example.luizh.projeto_eorl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.util.Log;

import com.example.luizh.projeto_eorl.domain.util.DateUtil;
import com.example.luizh.projeto_eorl.domain.util.User;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends UtilActivity implements DatabaseReference.CompletionListener {

    private AutoCompleteTextView actName;
    private AutoCompleteTextView actEmail;
    private AutoCompleteTextView actConfEmail;
    private EditText edtPassWord;
    private EditText edtConfPassWord;
    private EditText edtDate;
    private String strDate;
    private User user;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        initView();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if(firebaseUser == null)
                {
                    return;
                }
                else
                {
                    user.setId(firebaseUser.getUid());
                    user.saveDB(SignUpActivity.this);
                }
            }
        };

        edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    sendaDate();
                }
            }
        });
    }

    public void initView() {
        actName = (AutoCompleteTextView) findViewById(R.id.actNome);
        actEmail = (AutoCompleteTextView) findViewById(R.id.actEmail);
        actConfEmail = (AutoCompleteTextView) findViewById(R.id.actCofirmEmail);
        edtDate = (EditText) findViewById(R.id.EdtDataNascimento);
        edtPassWord = (EditText) findViewById(R.id.edtSenha);
        edtConfPassWord = (EditText) findViewById(R.id.edtConfSenha);
        progressBar = (ProgressBar) findViewById(R.id.sig_up_progressBar);

        //Bloquear edicao do campo edtDate
        edtDate.setInputType(InputType.TYPE_NULL);
    }

    public boolean initUser()
    {
        if(edtDate.getText().toString().isEmpty() || edtConfPassWord.getText().toString().isEmpty() || edtPassWord.getText().toString().isEmpty()
                || actName.getText().toString().isEmpty() || actConfEmail.getText().toString().isEmpty() || actEmail.getText().toString().isEmpty())
        {
            showSnackBar("Favor verificar se todos os campos estão preenchidos corretamente.");
            return false;
        }
        else
        {
            if(validateEmail())
            {
                if(validateName())
                {
                    if(validatePassword())
                    {
                        user = new User();
                        user.setNome(actName.getText().toString());
                        user.setEmail(actEmail.getText().toString());
                        user.setDataNascimento(strDate);
                        user.setPassword(edtPassWord.getText().toString());
                        //user.generateCryptWithMD5();
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                closeProgressBar();
                return false;
            }
        }
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, new setDateListener(), year, month, day);
        datePickerDialog.show();
    }

    public void sendSignData(android.view.View view)
    {
        FirebaseCrash.log("LoginActivity:clickListener:button:sendSignData");
        openProgressBar();

        if(initUser()) {
            saveUser();
        }

        else
        {
            closeProgressBar();
        }
    }

    public void saveUser()
    {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {

                if(!task.isSuccessful())
                {
                    closeProgressBar();

                    if(task.getException().toString().contains("The email address is already in use by another account."))
                    {
                        showSnackBar("Erro ao cadastrar usuário. O e-mail informado é usado por outra conta.");
                    }
                    else
                    {
                        showSnackBar("Erro ao cadastrar usuário.");
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                FirebaseCrash.report(e);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( mAuthListener != null ){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

        mAuth.signOut();
        showToast("Conta criada com Sucesso!");
        closeProgressBar();
        finish();
    }

    public boolean validateEmail()
    {
        if(isEmailValid(actEmail.getText().toString()))
        {
            if(actEmail.getText().toString().equals(actConfEmail.getText().toString()))
            {
                return true;
            }
            else
            {
                showSnackBar("Os emails informados não coincidem");
                return false;
            }
        }
        else
        {

            actEmail.setError("O e-mail informado é inválido");
            showSnackBar("Favor preencher todos os campos corretamente");
            return false;
        }

    }

    public boolean validateName()
    {
        if(actName.getText().toString().length() < 8)
        {
            actName.setError("O campo Nome deve conter ao menos 8 caracteres");
            showSnackBar("Favor preencher todos os campos corretamente");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean validatePassword()
    {
        if(edtPassWord.getText().toString().length() > 6)
        {
            if(!edtPassWord.getText().toString().equals(edtConfPassWord.getText().toString()))
            {
                showSnackBar("Os campos de senha e confirmação de senha são diferentes.");
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            actEmail.setError("O campo Senha deve conter ao menos 6 caracteres");
            showSnackBar("Favor preencher todos os campos corretamente");
            return false;
        }
    }
}
