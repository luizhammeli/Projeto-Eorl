package com.example.luizh.projeto_eorl.domain.util;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luiz on 23/07/16.
 */
public class Note  implements Serializable
{
    private String title;
    private String text;

    public Note()
    {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void saveDB(DatabaseReference.CompletionListener... completionListener)
    {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Notes").child(userId).child(getTitle());
        setTitle(null);

        if(completionListener.length == 0)
        {
            databaseReference.setValue(this);
        }
        else{
            databaseReference.setValue(this, completionListener[0]);
        }
    }

    public void removeDB(Context context)
    {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Notes").child(userId).child(getTitle());
        databaseReference.removeValue((DatabaseReference.CompletionListener) context);

    }

    public static void contextDataDB(Context context)
    {
        Query databaseReference = FirebaseDatabase.getInstance().getReference().child("Notes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.addValueEventListener( (ValueEventListener) context);
    }

    public static List<Note> getNotesList(String [] notesArray)
    {
        List<Note> notesList= new ArrayList<Note>();

        for(int a=0;a<notesArray.length;a++)
        {
            String[] arrayAux;
            arrayAux = notesArray[a].split("=");
            Note note = new Note();
            note.setTitle(arrayAux[0]);
            note.setText(arrayAux[1]);
            notesList.add(note);
        }

        return notesList;

    }
}
