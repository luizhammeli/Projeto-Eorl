package com.example.luizh.projeto_eorl.domain.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mac on 09/08/16.
 */
public class Message
{
    private String title;
    private String text;
    private String subject;
    private String date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void saveDB(DatabaseReference.CompletionListener... completionListener)
    {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Message").child(getDate()).child(getSubject()).child(userId).child(getTitle());
        setSubject(null);
        setDate(null);
        setTitle(null);

        if(completionListener.length == 0)
        {
            databaseReference.setValue(this);
        }
        else{
            databaseReference.setValue(this, completionListener[0]);
        }
    }
}
