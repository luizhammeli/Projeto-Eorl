package com.example.luizh.projeto_eorl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.R;
import com.example.luizh.projeto_eorl.domain.util.Note;

import java.util.List;

/**
 * Created by mac on 28/07/16.
 */
public class NoteAdapter extends BaseAdapter
{
    Context context;
    List<Note> list;

    public NoteAdapter(Context context, List<Note> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size():0 ;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_note, viewGroup, false);
        TextView text = (TextView)view.findViewById(R.id.tv_text);
        TextView title = (TextView)view.findViewById(R.id.tv_title);
        Note note = list.get(position);
        text.setText(note.getText());
        title.setText(note.getTitle());

        return view;
    }
}
