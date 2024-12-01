package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TeamDetailsActivity extends AppCompatActivity {
    TextView txtJDetails_header, txtJDetails_avg;
    ListView lvlJDetails_team, lvlJDetails_reviews;
    Button btnJDetails_back, btnJDetails_Review;
    ImageButton imgJDetails_battle;

    DatabaseHelper dbHelper;

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
    }
}