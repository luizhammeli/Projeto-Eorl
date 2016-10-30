package com.example.luizh.projeto_eorl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.R;
import com.example.luizh.projeto_eorl.domain.util.Diagnosis;
import com.example.luizh.projeto_eorl.domain.util.Drug;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by luizhammerli on 16/10/16.
 */
public class DrugListAdapter extends BaseAdapter{

    List <Drug>drugList;
    List <Drug>list;
    Context context;

    public DrugListAdapter(Context context, List<Drug> drugList)
    {
        this.context = context;
        this.list = drugList;
        this.drugList = new ArrayList<Drug>();
        this.drugList.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_drug_list, viewGroup, false);
        TextView textView = (TextView)view.findViewById(R.id.tv_item_drug_list);
        Drug drug = list.get(i);
        textView.setText(drug.getName());

        return view;
    }

    public void filter(String charText) {


        charText = charText.toLowerCase(Locale.getDefault());

        list.clear();
        if (charText.length() == 0) {
            list.addAll(drugList);

        } else {
            for (Drug drug : drugList) {
                if (charText.length() != 0 && drug.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(drug);
                }
            }
        }
        notifyDataSetChanged();
    }
}
