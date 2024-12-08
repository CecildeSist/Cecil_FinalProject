package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class BattleChooseActivity extends AppCompatActivity {
    TextView txtJBaChoo_userVersusUser, txtJBaChoo_userPlease;
    ListView baChooL;
    Button btnJBaChoo;

    DatabaseHelper dbHelper;
    static ArrayList<Team> allTeams = new ArrayList<>();
    BattleChooseAdapter bChooAdapter;
    //Hahah, "beat you"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle_choose);

        //Connect GUI elements
        txtJBaChoo_userVersusUser = findViewById(R.id.txtVBattleChoose_trainerNames);
        txtJBaChoo_userPlease = findViewById(R.id.txtVBaChoo_userPlease);
        baChooL = findViewById(R.id.baChoo_list);
        btnJBaChoo = findViewById(R.id.btnVBattleChoose_back);

        //Set usernames in text boxes
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

        txtJBaChoo_userVersusUser.setText(loggedUser + " VERSUS " + teamCaught.getUserTrainer() + "!");
        txtJBaChoo_userPlease.setText(loggedUser +", please choose a team.");

        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        if (allTeams.isEmpty()) {
            Team teamOne = new Team(1, 390F, "CecilDeSist", "Magmar", "Weezing", "Mr Mime", "Zapdos", "Wigglytuff", "Hitmonlee");
            allTeams.add(teamOne);
            Team teamTwo = new Team(2, 410.833F, "Mephistopheles", "Mr Mime", "Scyther", "Venomoth", "Parasect", "Mewtwo", "Magneton");
            allTeams.add(teamTwo);
        }

        bChooAdapter = new BattleChooseAdapter(this, dbHelper.welcomeTeams());
        baChooL.setAdapter(bChooAdapter);

        backButtonListener();
        teamChosen();

    }

    private void backButtonListener() {
        btnJBaChoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
                Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

                Intent battleToDetails = new Intent(BattleChooseActivity.this, TeamDetailsActivity.class);
                battleToDetails.putExtra("Username:", loggedUser);
                battleToDetails.putExtra("Team clicked:", teamCaught);
                startActivity(battleToDetails);
            }
        });
    }

    private void teamChosen() {
        baChooL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent chooseBa_to_results = new Intent(BattleChooseActivity.this, BattleResultsActivity.class);
                Team teamChosen = (Team) adapterView.getItemAtPosition(i);
                chooseBa_to_results.putExtra("Team chosen:", teamChosen);
                chooseBa_to_results.putExtra("Username:", loggedUser);
                Team opposingTeam = (Team) cameFrom.getSerializableExtra("Team clicked:");
                chooseBa_to_results.putExtra("Opposing team:", opposingTeam);

                startActivity(chooseBa_to_results);
            }
        });
    }
}