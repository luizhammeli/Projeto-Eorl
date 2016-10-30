package com.example.luizh.projeto_eorl.domain.util;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.luizh.projeto_eorl.domain.util.CryptWithMD5.*;

/**
 * Created by luizh on 08/05/2016.
 */
public class User
{
    public static String PROVIDER = "LoginType";

    private String dataNascimento;
    private String nome;
    private String password;
    private String email;
    private String id;



    public User(){}


    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void generateCryptWithMD5()
    {
        setPassword(CryptWithMD5.cryptWithMD5(getPassword()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void setInMap( Map<String, Object> map ) {
        if (getDataNascimento() != null) {
            map.put("dataNascimento", getDataNascimento());
        }

        if (getEmail() != null) {
            map.put("email", getEmail());
        }

        if( getNome() != null ){
            map.put( "nome", getNome() );
        }
    }

    public void saveDB(DatabaseReference.CompletionListener... completionListener)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(getId());
        setPassword(null);

        if(completionListener.length == 0) {
            databaseReference.setValue(this);
        }
        else{

            databaseReference.setValue(this, completionListener[0]);
        }
    }

    public void saveSp(Context context, String token)
    {
        LibraryClass.saveSp(context, PROVIDER, token);
    }


    public String getSp(Context context)
    {
       return LibraryClass.getSp(context, PROVIDER);
    }

    public boolean isSocialNetworkLogged( Context context )
    {
        String  result = getSp(context);

        if(result.contains("google"))
        {
            return true;
        }

        else{
            return false;
        }
    }

    public void contextDataDB(Context context)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(getId());

        databaseReference.addListenerForSingleValueEvent( (ValueEventListener) context);
    }

    public void UpdateDB( DatabaseReference.CompletionListener... completionListener)
    {
        Map<String, Object> map = new HashMap<>();
        setInMap(map);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(getId());

        if( completionListener.length > 0 ) {

            databaseReference.updateChildren(map, completionListener[0]);
        }

    }
}
