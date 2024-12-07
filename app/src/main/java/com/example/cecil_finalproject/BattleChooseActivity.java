package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
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

        //Set usernames in text boxes NOTE TO SELF NOT DONE
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        Team teamCaught = (Team) cameFrom.getSerializableExtra("Team clicked:");

        txtJBaChoo_userVersusUser.setText(loggedUser + "VERSUS " + teamCaught.getUserTrainer() + "!");
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
    }
}