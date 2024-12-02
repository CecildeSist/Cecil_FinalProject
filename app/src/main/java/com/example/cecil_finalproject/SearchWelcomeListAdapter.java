package com.example.cecil_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//NOTE TO SELF make a different adapter for the search page and name it differently so the professor doesn't get confused
public class SearchWelcomeListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Team> listOfTeams;

    public SearchWelcomeListAdapter (Context c, ArrayList<Team> ls) {
        context = c;
        listOfTeams = ls;
    }

    @Override
    public int getCount() {
        return listOfTeams.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfTeams.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(WelcomeActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.search_welcome_cell, null);
        }

        //Find GUI elements
        TextView welCelUname = view.findViewById(R.id.txtV_SW_uName);
        TextView welCelRate = view.findViewById(R.id.txtV_SW_avg);
        TextView welCelA = view.findViewById(R.id.txtV_SW_pkmnA);
        TextView welCelB = view.findViewById(R.id.txtV_SC_pkmnB);
        TextView welCelC = view.findViewById(R.id.txtV_SC_pkmnC);
        TextView welCelD = view.findViewById(R.id.txtV_SC_pkmnD);
        TextView welCelE = view.findViewById(R.id.txtV_SC_pkmnE);
        TextView welCelF = view.findViewById(R.id.txtV_SC_pkmnF);

        //Retrieve the team
        Team team = listOfTeams.get(i);

        DatabaseHelper db = new DatabaseHelper(context);
        Integer teamID = team.getTeamID();

        //Set GUI
        welCelUname.setText(team.getUserTrainer());
        //NOTE TO SELF find out how to turn the float into a string
        welCelRate.setText(db.averageRating(teamID));
        welCelA.setText(team.getPkmnA());
        welCelB.setText(team.getPkmnB());
        welCelC.setText(team.getPkmnC());
        welCelD.setText(team.getPkmnD());
        welCelE.setText(team.getPkmnE());
        welCelF.setText(team.getPkmnF());

        return view;
    }
}
