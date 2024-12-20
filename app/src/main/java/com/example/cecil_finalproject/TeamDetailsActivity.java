package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TeamDetailsActivity extends AppCompatActivity {
    TextView txtJDetails_header, txtJDetails_avg;
    ListView lvlJDetails_team, lvlJDetails_reviews;
    Button btnJDetails_back, btnJDetails_Review;
    ImageButton imgJDetails_battle;

    DatabaseHelper dbHelper;
    ArrayList<Pokemon> allPokemon = new ArrayList<>();
    pkmnDetailsAdapter detailsAdapterA;
    static ArrayList<Review> thisTeamsReviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_team_details);

        //Connect GUI elements
        txtJDetails_header = findViewById(R.id.txtVTeamDetails_username);
        lvlJDetails_team = findViewById(R.id.lvVTeamDetails_team);
        txtJDetails_avg = findViewById(R.id.txtVTeamDetails_average);
        btnJDetails_back = findViewById(R.id.btnVdetails_back);
        imgJDetails_battle = findViewById(R.id.imgVDetails);
        btnJDetails_Review = findViewById(R.id.btnVdetails_review);
        lvlJDetails_reviews = findViewById(R.id.lvVTeamDetails_reviews);

        dbHelper = new DatabaseHelper(this);

        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

        Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");
        //Change the TextView at the top to display the current user's username
        String teamUser = teamCaught.getUserTrainer();
        txtJDetails_header.setText("Team by " + teamUser);

        Integer teamID = teamCaught.getTeamID();

        //Fill the array list of reviews
        thisTeamsReviews = dbHelper.oneTeamsReviews(teamCaught);
        ReviewAdapter detailsAdapterB = new ReviewAdapter(this, thisTeamsReviews);
        //detailsAdapterB.notifyDataSetChanged();

        lvlJDetails_reviews.setAdapter(detailsAdapterB);
        if (cameFrom.getSerializableExtra("New Review:") != null) {
            /*Review reviewData = (Review) cameFrom.getSerializableExtra("New Review:");
            thisTeamsReviews.add(reviewData);*/
            detailsAdapterB.notifyDataSetChanged();
            txtJDetails_avg.setText(dbHelper.averageRating(teamID) + " / 5");
        }

        txtJDetails_avg.setText(dbHelper.averageRating(teamID) + " / 5");

        //Step 0: make review button grey and unusable if a review from the current user already exists
        if (dbHelper.isReviewUnallowed(teamCaught, loggedUser)) {
            //make button grey (#E0E0E0)
            //I went to colors.xml and added both yellow and light grey.
            btnJDetails_Review.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_grey));
        }
        else {
            //make button yellow again (#FDCE40)
            btnJDetails_Review.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.yellow));
            //Only call the listener for the review button if the boolean is false
            detailsReviewListener();
        }

        //Step 1: create and call button listeners
        detailsBackListener();
        detailsBattleListener();

        //Step 2: add all the Pokemon in the team to an array list of Pokemon using the database helper
        allPokemon = dbHelper.pkmnOnTeam(teamID);

        //Step 3: add a review if the current user just gave one NOTE TO SELF NOT DONE

        //Step 4: populate the first list view
        fillFirstList();

        if (cameFrom.getSerializableExtra("Updated Review:") != null) {
            Review upRev = (Review) cameFrom.getSerializableExtra("Updated Review:");

            thisTeamsReviews.add(upRev);
            detailsAdapterB.notifyDataSetChanged();
        }
    }

    private void detailsBackListener() {
        btnJDetails_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent detailsToWelcome = new Intent(TeamDetailsActivity.this, WelcomeActivity.class);
                detailsToWelcome.putExtra("Username:", loggedUser);
                startActivity(detailsToWelcome);
            }
        });
    }

    private void detailsBattleListener() {
        imgJDetails_battle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
                Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

                Intent detailsToBattle = new Intent(TeamDetailsActivity.this, BattleChooseActivity.class);
                detailsToBattle.putExtra("Username:", loggedUser);
                detailsToBattle.putExtra("Team clicked:", teamCaught);
                startActivity(detailsToBattle);
            }
        });
    }

    private void detailsReviewListener() {
        btnJDetails_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
                Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

                Intent detailsToReview = new Intent(TeamDetailsActivity.this, LeaveReviewActivity.class);
                detailsToReview.putExtra("Username:", loggedUser);
                detailsToReview.putExtra("Team clicked:", teamCaught);
                startActivity(detailsToReview);
            }
        });
    }

    public void fillFirstList() {
        detailsAdapterA = new pkmnDetailsAdapter(this, allPokemon);
        lvlJDetails_team.setAdapter(detailsAdapterA);
        //detailsAdapterA.notifyDataSetChanged();
    }
}