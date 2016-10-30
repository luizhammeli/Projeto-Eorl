package com.example.luizh.projeto_eorl.domain.util;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luizhammerli on 15/05/16.
 */
public class UtilActivity extends AppCompatActivity
{
    public ProgressBar progressBar;

    public void openProgressBar()
    {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void showToast(String message)
    {
        Toast.makeText(this, message ,Toast.LENGTH_LONG).show();
    }

    protected void closeProgressBar(){
        progressBar.setVisibility( View.GONE );
    }

    protected void showSnackBar(String message)
    {
        Snackbar.make(progressBar, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public boolean isEmailValid(String email) {
        if ((email == null) || (email.trim().length() == 0))
            return false;

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password, String confPassword)
    {
        if(password.length() > 6)
        {
            if(!password.equals(confPassword))
            {
                showToast("Os campos de senha e confirmação de senha são diferentes.");
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            showToast("Favor preencher todos os campos corretamente");
            return false;
        }
    }
}
