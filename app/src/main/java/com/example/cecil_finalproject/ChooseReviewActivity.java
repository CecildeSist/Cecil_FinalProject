package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChooseReviewActivity extends AppCompatActivity {
    TextView chooRevT;
    ListView chooRevL;
    Button chooRevB;

    DatabaseHelper dbHelper;
    ChooseReviewAdapter chR_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_review);

        //Connect GUI elements
        chooRevB = findViewById(R.id.chooseRev_back);
        chooRevL = findViewById(R.id.chooseReview_list);
        chooRevT = findViewById(R.id.chooseRev_user);

        //Heh, BLT. Like the sandwich.

        //Anyway, set the text.
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        chooRevT.setText(loggedUser + ", please choose the team whose review you want to edit.");

        //Call button listener
        chRevBListener();

        //Fill the list view
        dbHelper = new DatabaseHelper(this);
        getTOUR();
        reviewChosenListener();
    }

    private void chRevBListener() {
        chooRevB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent chR_to_chUp = new Intent(ChooseReviewActivity.this, ChooseUpdateActivity.class);
                chR_to_chUp.putExtra("Username:", loggedUser);
                startActivity(chR_to_chUp);
            }
        });
    }

    private void getTOUR() {
        //TOUR stands for "Teams One User Reviewed"
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

        chR_adapter = new ChooseReviewAdapter(this, dbHelper.teamsOneUserReviewed(loggedUser));
        chooRevL.setAdapter(chR_adapter);
    }

    private void reviewChosenListener(){
        chooRevL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent chR_to_Review = new Intent(ChooseReviewActivity.this, LeaveReviewActivity.class);
                Team teamReviewUpdate = (Team) adapterView.getItemAtPosition(i);
                chR_to_Review.putExtra("Username:", loggedUser);
                chR_to_Review.putExtra("Update this review:", teamReviewUpdate);
                startActivity(chR_to_Review);
            }
        });
    }
}