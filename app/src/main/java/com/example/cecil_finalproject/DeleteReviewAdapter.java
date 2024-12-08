package com.example.cecil_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeleteReviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Review> reviewsReviewed;

    public DeleteReviewAdapter (Context c, ArrayList<Review> rR) {
        context = c;
        reviewsReviewed = rR;
    }

    @Override
    public int getCount() {
        return reviewsReviewed.size();
    }

    @Override
    public Object getItem(int i) {
        return reviewsReviewed.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(DeleteReviewActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.cell_new_choose_rev, null);
        }

        Review r = reviewsReviewed.get(i);

        //Find GUI elements
        TextView rID = view.findViewById(R.id.cNCR_rID);
        TextView rS = view.findViewById(R.id.cNCR_rS);
        TextView tID = view.findViewById(R.id.cNCR_tID);

        rID.setText(r.getRevID().toString());
        rS.setText(r.getRevScore().toString());
        tID.setText(r.getTeamID().toString());

        return view;
    }
}
