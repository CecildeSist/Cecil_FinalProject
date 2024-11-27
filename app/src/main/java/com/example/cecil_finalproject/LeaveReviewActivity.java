package com.example.cecil_finalproject;

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

        //Call seekbar and button listeners NOTE TO SELF not done
        seekBarListener();
        leaveReviewListener();
        leaveLeaveListener();
    }

    //NOTE TO SELF not done
    private void seekBarListener() {
    }

    //NOTE TO SELF not done
    private void leaveReviewListener() {
        bJLeave_rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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