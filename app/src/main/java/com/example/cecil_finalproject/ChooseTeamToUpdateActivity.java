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

import java.util.ArrayList;

public class ChooseTeamToUpdateActivity extends AppCompatActivity {
    TextView chooTeamT;
    ListView chooTeamL;
    Button chooTeamB;

    DatabaseHelper dbHelper;
    ArrayList<Team> oneUsersTeams = new ArrayList<>();
    SearchWelcomeListAdapter oUTAdapter;

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
        chTBListen();

        //fill the list view
        dbHelper = new DatabaseHelper(this);
        getOneUsersTeams();

        //Call team click listener
        teamChosenToUpdate();
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

    private void getOneUsersTeams() {
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

        oUTAdapter = new SearchWelcomeListAdapter(this, dbHelper.chooseTeamToUpdate(loggedUser));
        chooTeamL.setAdapter(oUTAdapter);
    }

    private void teamChosenToUpdate() {
        chooTeamL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent chT_to_createTeam = new Intent(ChooseTeamToUpdateActivity.this, CreateTeamActivity.class);
                Team teamUpdate = (Team) adapterView.getItemAtPosition(i);
                chT_to_createTeam.putExtra("Username:", loggedUser);
                chT_to_createTeam.putExtra("Team to update:", teamUpdate);
                startActivity(chT_to_createTeam);
            }
        });
    }
}