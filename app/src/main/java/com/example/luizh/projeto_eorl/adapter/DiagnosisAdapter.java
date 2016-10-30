package com.example.luizh.projeto_eorl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.R;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by mac on 02/08/16.
 */
public class DiagnosisAdapter extends BaseAdapter {

    Context context;
    List<Diagnosis> list;
    List<Diagnosis> arrayList;

    public DiagnosisAdapter(Context context, List<Diagnosis> list)
    {
        this.context = context;
        this.list = list;
        arrayList = new ArrayList<Diagnosis>();
        arrayList.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_diagnosis, viewGroup, false);
        TextView textView = (TextView)view.findViewById(R.id.tv_item_diagnosis);
        Diagnosis diagnosis = list.get(position);
        textView.setText(diagnosis.getNome());
        return view;
    }

    public void filter(String charText) {


        charText = charText.toLowerCase(Locale.getDefault());

        list.clear();
        if (charText.length() == 0) {
            list.addAll(arrayList);

        } else {
            for (Diagnosis diagnosis : arrayList) {
                if (charText.length() != 0 && diagnosis.getNome().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(diagnosis);
                }
            }
        }
        notifyDataSetChanged();
    }

}
