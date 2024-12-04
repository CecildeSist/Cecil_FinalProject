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
    ArrayList<Review> thisTeamsReviews = new ArrayList<>();
    ReviewAdapter detailsAdapterB;

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
        dbHelper.initAllTables();

        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

        Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");
        //Change the TextView at the top to display the current user's username
        String teamUser = teamCaught.getUserTrainer();
        txtJDetails_header.setText("Team by " + teamUser);

        Integer teamID = teamCaught.getTeamID();

        //Fill the array list of reviews

        /*if (teamID == 1) {
            Review initialReview = new Review(2, 3, 1, "Mephistopheles");
        }
        else if (teamID == 2) {
            Review initialReview = new Review(1, 5, 2, "CecilDeSist");
        }*/

        thisTeamsReviews = dbHelper.oneTeamsReviews(teamCaught);
        detailsAdapterB = new ReviewAdapter(this, thisTeamsReviews);
        lvlJDetails_reviews.setAdapter(detailsAdapterB);
        Integer numReviews = thisTeamsReviews.size();

        txtJDetails_avg.setText(dbHelper.averageRating(teamID) + " / 5");

        //Step 0: make review button grey and unusable if a review from the current user already exists NOTE TO SELF NOT DONE
        if (dbHelper.isReviewUnallowed(teamCaught, loggedUser)) {
            //make button grey (#E0E0E0)
            //I went to colors.xml and added both yellow and light grey. NOTE TO SELF CHECK WITH TEACHER IF THAT'S ALLOWED
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

        //Update review adapter and average score if the intent is from the "leave review" activity NOTE TO SELF NOT DONE
        Review reviewCheck = (Review) cameFrom.getSerializableExtra("New Review");
        if (reviewCheck != null) {
            //Update review adapter

            //detailsAdapterB.notifyDataSetChanged();

            /*thisTeamsReviews = dbHelper.oneTeamsReviews(teamCaught);
            detailsAdapterB = new ReviewAdapter(this, thisTeamsReviews);
            lvlJDetails_reviews.setAdapter(detailsAdapterB);*/

            /*thisTeamsReviews.add(reviewCheck);
            detailsAdapterB.notifyDataSetChanged();*/
            if (reviewCheck.getTeamID() == teamID) {
                thisTeamsReviews.add(reviewCheck);
                detailsAdapterB.notifyDataSetChanged();
            }

            //Update average score NOTE TO SELF NOT DONE
            /*txtJDetails_avg.setText(dbHelper.averageRating(teamID) + " / 5");*/
            String avgScore = dbHelper.averageRating(teamID);
            txtJDetails_avg.setText(avgScore + " / 5");
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
    }
}