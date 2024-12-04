package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
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
        Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

        //Call seekbar and button listeners NOTE TO SELF not done
        seekBarListener();
        leaveReviewListener();
        leaveLeaveListener();
    }

    //NOTE TO SELF not done
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

    //NOTE TO SELF not done
    private void leaveReviewListener() {
        bJLeave_rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String userReviewing = (String) cameFrom.getSerializableExtra("Username:");
                Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

                Integer teamID = teamCaught.getTeamID();
                dbHelper.addReview(reviewScore, teamID, userReviewing);

                Intent reviewToDetails = new Intent(LeaveReviewActivity.this, TeamDetailsActivity.class);
                reviewToDetails.putExtra("Team clicked:", teamCaught);
                reviewToDetails.putExtra("Username:", userReviewing);
                startActivity(reviewToDetails);
            }
        });
    }

    //NOTE TO SELF not done
    private void leaveLeaveListener() {
        bJLeave_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}