package com.example.luizh.projeto_eorl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.R;
import com.example.luizh.projeto_eorl.domain.util.CalculatorDrug;

import java.util.List;

/**
 * Created by mac on 16/08/16.
 */
public class CalculatorDrugAdapter extends BaseAdapter
{
    private Context context;
    private List<CalculatorDrug> list;
     private TextView txvDrug;

    public CalculatorDrugAdapter(Context context, List<CalculatorDrug> list) {

        this.context = context;
        this.list = list;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewItem = LayoutInflater.from(context).inflate(R.layout.item_drug_calculator, viewGroup, false);
        txvDrug = (TextView)viewItem.findViewById(R.id.tv_item_drug_calculator);
        txvDrug.setText(list.get(i).getName());

        return viewItem;
    }
}
