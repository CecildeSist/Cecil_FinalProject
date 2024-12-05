package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChooseTeamToUpdateActivity extends AppCompatActivity {
    TextView chooTeamT;
    ListView chooTeamL;
    Button chooTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_team_to_update);
        //Connect GUI elements
        chooTeamT = findViewById(R.id.chooseTeamUname);
        chooTeamL = findViewById(R.id.lvChooseTeam);
        chooTeamB = findViewById(R.id.btnChooseTeam);

        //Set text
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        chooTeamT.setText(loggedUser + ", please click on the team you want to update.");

        //Call button listener
    }

    private void chTBListen() {
        chooTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent chTeam_to_chUp = new Intent(ChooseTeamToUpdateActivity.this, ChooseUpdateActivity.class);
                chTeam_to_chUp.putExtra("Username:", loggedUser);
                startActivity(chTeam_to_chUp);
            }
        });
    }
}