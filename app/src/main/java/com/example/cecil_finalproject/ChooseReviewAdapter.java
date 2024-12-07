package com.example.cecil_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChooseReviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Team> teamsReviewed;

    public ChooseReviewAdapter (Context c, ArrayList<Team> tR) {
        context = c;
        teamsReviewed = tR;
    }

    @Override
    public int getCount() {
        return teamsReviewed.size();
    }

    @Override
    public Object getItem(int i) {
        return teamsReviewed.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(ChooseReviewActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.cell_choose_review, null);
        }

        Team t = teamsReviewed.get(i);

        //Find GUI elements
        TextView chRU = view.findViewById(R.id.chRUUser);
        TextView chR1 = view.findViewById(R.id.chROne);
        TextView chR2 = view.findViewById(R.id.chRTwo);
        TextView chR3 = view.findViewById(R.id.chRThree);
        TextView chR4 = view.findViewById(R.id.chRFour);
        TextView chR5 = view.findViewById(R.id.chRFive);
        TextView chR6 = view.findViewById(R.id.chRSix);

        chRU.setText(t.getUserTrainer());
        chR1.setText(t.getPkmnA());
        chR2.setText(t.getPkmnB());
        chR3.setText(t.getPkmnC());
        chR4.setText(t.getPkmnD());
        chR5.setText(t.getPkmnE());
        chR6.setText(t.getPkmnF());

        return view;
    }
}
