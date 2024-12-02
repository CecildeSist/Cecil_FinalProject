package com.example.cecil_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class pkmnDetailsAdapter extends BaseAdapter {
    Context context;
    ArrayList<Pokemon> pkmnList;

    public pkmnDetailsAdapter(Context c, ArrayList<Pokemon> pL) {
        context = c;
        pkmnList = pL;
    }

    @Override
    public int getCount() {
        return pkmnList.size();
    }

    @Override
    public Object getItem(int i) {
        return pkmnList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(TeamDetailsActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.details_cell, null);
        }

        //Find GUI elements
        TextView detailsName = view.findViewById(R.id.txtV_DCell_pkmn);
        TextView detailsTypeA = view.findViewById(R.id.txtV_DCell_typeA);
        TextView detailsTypeB = view.findViewById(R.id.txtV_DCell_typeB);

        //Retrieve the Pokemon
        Pokemon pkmn = pkmnList.get(i);

        //Set GUI
        detailsName.setText(pkmn.getPkmnName());
        detailsTypeA.setText(pkmn.getTypeA());
        detailsTypeB.setText(pkmn.getTypeB());

        return view;
    }
}
