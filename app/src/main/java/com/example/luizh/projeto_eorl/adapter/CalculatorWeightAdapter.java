package com.example.luizh.projeto_eorl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.R;

import java.util.List;

/**
 * Created by mac on 15/08/16.
 */
public class CalculatorWeightAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> listWeight;
    private TextView txvWeight;

    public CalculatorWeightAdapter(Context context, List<Integer> listWeight) {

        this.context = context;
        this.listWeight = listWeight;
    }

    @Override
    public int getCount() {
        return  listWeight.size();
    }

    @Override
    public Object getItem(int i) {
        return  listWeight.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewItem = LayoutInflater.from(context).inflate(R.layout.item_weight_calculator, viewGroup, false);
        txvWeight = (TextView)viewItem.findViewById(R.id.txvWeightCalculator);
        txvWeight.setText(listWeight.get(i).toString() + " kg");

        return viewItem;
    }
}
