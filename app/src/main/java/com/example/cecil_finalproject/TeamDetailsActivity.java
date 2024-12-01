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

        txtJDetails_avg.setText(dbHelper.averageRating(teamID) + " / 5");

        //Step 1: create and call button listeners
        detailsBackListener();
        detailsBattleListener();
        detailsReviewListener();

        //Step 2: add all the Pokemon in the team to an array list of Pokemon using the database helper
        allPokemon = dbHelper.pkmnOnTeam(teamID);

        //Step 3: put the Pokemon in the first listview NOTE TO SELF NOT DONE
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
}