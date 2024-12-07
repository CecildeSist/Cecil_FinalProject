package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LeaveReviewActivity extends AppCompatActivity {
    TextView tJLeave_choU, tJLeave_num;
    SeekBar seekJLeave;
    Button bJLeave_rev, bJLeave_back;

    DatabaseHelper dbHelper;

    Integer reviewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leave_review);

        //Connect GUI elements
        tJLeave_choU = findViewById(R.id.txtVLeaveReview);
        tJLeave_num = findViewById(R.id.txtVLeaveReview_number);
        seekJLeave = findViewById(R.id.seekVLeaveReview_score);
        bJLeave_rev = findViewById(R.id.btnVLeaveReview_review);
        bJLeave_back = findViewById(R.id.btnVLeaveReview_back);

        //Init dbHelper
        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        tJLeave_num.setText("0");
        seekJLeave.setProgress(0);

        //Grab team clicked and logged-in user's name
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        if (cameFrom.getSerializableExtra("Update review:") != null) {
            String userReviewing = (String) cameFrom.getSerializableExtra("Username:");
            Team teamReviewUpdate = (Team) cameFrom.getSerializableExtra("Update review:");
            String tC = teamReviewUpdate.getUserTrainer();
            tJLeave_choU.setText("Review for " + tC + "'s Team");
            bJLeave_rev.setText("Update Review");
            goToUpdateRevList();
            //Call function for updating a review
            updateReview();
        }
        else {
            Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");
            String teamCreator = teamCaught.getUserTrainer();
            tJLeave_choU.setText("Review for " + teamCreator + "'s Team");
            bJLeave_rev.setText("Leave Review");
            leaveReviewListener();
            leaveLeaveListener();
        }

        //Call seekbar and button listeners
        seekBarListener();
    }

    private void seekBarListener() {
        seekJLeave.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                reviewScore = seekJLeave.getProgress();
                tJLeave_num.setText(reviewScore.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void leaveReviewListener() {
        bJLeave_rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String userReviewing = (String) cameFrom.getSerializableExtra("Username:");
                Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

                Integer rID = dbHelper.countRecordsFromTable("Reviews") + 1;
                Integer teamID = teamCaught.getTeamID();
                dbHelper.addReview(rID, reviewScore, teamID, userReviewing);

                Intent reviewToDetails = new Intent(LeaveReviewActivity.this, TeamDetailsActivity.class);
                reviewToDetails.putExtra("Team clicked:", teamCaught);
                reviewToDetails.putExtra("Username:", userReviewing);

                Review newReview = new Review(rID, reviewScore, teamID, userReviewing);
                reviewToDetails.putExtra("New Review:", newReview);

                startActivity(reviewToDetails);
            }
        });
    }

    private void leaveLeaveListener() {
        bJLeave_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String userReviewing = (String) cameFrom.getSerializableExtra("Username:");
                Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

                Intent reviewToDetails = new Intent(LeaveReviewActivity.this, TeamDetailsActivity.class);
                reviewToDetails.putExtra("Team clicked:", teamCaught);
                reviewToDetails.putExtra("Username:", userReviewing);
                startActivity(reviewToDetails);
            }
        });
    }

    private void goToUpdateRevList() {
        bJLeave_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String userReviewing = (String) cameFrom.getSerializableExtra("Username:");

                Intent revToChooseRev = new Intent(LeaveReviewActivity.this, ChooseReviewActivity.class);
                revToChooseRev.putExtra("Username:", userReviewing);
                startActivity(revToChooseRev);
            }
        });
    }

    private void updateReview() {
        bJLeave_rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String userReviewing = (String) cameFrom.getSerializableExtra("Username:");

                Intent reviewToWelcome = new Intent(LeaveReviewActivity.this, WelcomeActivity.class);
                reviewToWelcome.putExtra("Username:", userReviewing);

                Team teamReviewUpdate = (Team) cameFrom.getSerializableExtra("Update review:");
                reviewToWelcome.putExtra("Updated Team:", teamReviewUpdate);

                Integer tR_ID = teamReviewUpdate.getTeamID();
                Log.d("Review Score", String.valueOf(reviewScore));

                Review revToUpdate;

                Integer rID = dbHelper.selectReviewClicked(userReviewing, tR_ID);
                Log.d("review ID:", rID + "");

                dbHelper.updateRev(tR_ID, userReviewing, reviewScore, rID);

                Review updatedReview = new Review(rID, reviewScore, tR_ID, userReviewing);
                reviewToWelcome.putExtra("Updated review:", updatedReview);
                startActivity(reviewToWelcome);
            }
        });
    }
}