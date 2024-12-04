package com.example.cecil_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Review> reviews;

    public ReviewAdapter(Context c, ArrayList<Review> r) {
        context = c;
        reviews = r;
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int i) {
        return reviews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(TeamDetailsActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.cell_rating, null);
        }

        //Find GUI elements
        TextView cell_rating_reviewer = view.findViewById(R.id.rating_username);
        ImageView star1 = view.findViewById(R.id.imgStar1);
        ImageView star2 = view.findViewById(R.id.imgStar2);
        ImageView star3 = view.findViewById(R.id.imgStar3);
        ImageView star4 = view.findViewById(R.id.imgStar4);
        ImageView star5 = view.findViewById(R.id.imgStar5);

        //Set GUI NOTE TO SELF NOT DONE
        Review rev = reviews.get(i);
        String reviewer = rev.getuReviewer();
        Integer revScore = rev.getRevScore();

        cell_rating_reviewer.setText("Review by " + reviewer);
        if (revScore >= 1) {
            star1.setImageResource(R.drawable.gold_star);
        }
        if (revScore >= 2) {
            star2.setImageResource(R.drawable.gold_star);
        }
        if (revScore >= 3) {
            star3.setImageResource(R.drawable.gold_star);
        }
        if (revScore >= 4) {
            star4.setImageResource(R.drawable.gold_star);
        }
        if (revScore == 5) {
            star5.setImageResource(R.drawable.gold_star);
        }

        return view;
    }
}
