package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    TextView txtJWelcome_username;
    Button btnJWelcome_create, btnJWelcome_delete, btnJWelc_upd;
    ImageButton imgBtnJWelcome_search;
    ListView lvJWelcome_teams;

    DatabaseHelper dbHelper;
    static ArrayList<Team> welcomeTeamsList = new ArrayList<>();
    SearchWelcomeListAdapter welcomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        //Connecting GUI elements
        txtJWelcome_username = findViewById(R.id.txtVWelcome_username);
        btnJWelcome_create = findViewById(R.id.btnVWelcome_create);
        btnJWelcome_delete = findViewById(R.id.btnVWelcome_delete);
        imgBtnJWelcome_search = findViewById(R.id.imgBtnVWelcome_search);
        lvJWelcome_teams = findViewById(R.id.lvVWelcome_teams);
        btnJWelc_upd = findViewById(R.id.btnVWelcUpdate);

        //Setting up DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        Intent cameFrom = getIntent();

        //Change the TextView at the top to display the current user's username
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        txtJWelcome_username.setText("Welcome, " + loggedUser + "!");

        //Call button click listeners
        createListener();
        deleteListener();
        searchListener();
        welUpdListen();

        //Fill welcomeTeamsList if empty
        if (welcomeTeamsList.isEmpty()) {
            Team teamOne = new Team(1, 390F, "CecilDeSist", "Magmar", "Weezing", "Mr Mime", "Zapdos", "Wigglytuff", "Hitmonlee");
            welcomeTeamsList.add(teamOne);
            Team teamTwo = new Team(2, 410.833F, "Mephistopheles", "Mr Mime", "Scyther", "Venomoth", "Parasect", "Mewtwo", "Magneton");
            welcomeTeamsList.add(teamTwo);
        }

        //Add team to array list if passed from CreateTeamActivity
        if (cameFrom.getSerializableExtra("Team:") != null) {
            Team teamPassed = (Team) cameFrom.getSerializableExtra("Team:");
            welcomeTeamsList.add(teamPassed);

            Log.d("Total number of teams:", welcomeTeamsList.size() - 1 + "");
        }

        fillListView();
        teamDetailsClick();
    }

    private void createListener() {
        btnJWelcome_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent welcomeToCreate = new Intent(WelcomeActivity.this, CreateTeamActivity.class);
                welcomeToCreate.putExtra("Username:", loggedUser);
                startActivity(welcomeToCreate);

            }
        });
    }

    private void deleteListener() {
        btnJWelcome_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent welcomeToSelectDelete = new Intent(WelcomeActivity.this, SelectDeleteActivity.class);
                welcomeToSelectDelete.putExtra("Username:", loggedUser);
                startActivity(welcomeToSelectDelete);
            }
        });
    }

    private void searchListener() {
        imgBtnJWelcome_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent welcomeToSearch = new Intent(WelcomeActivity.this, SearchActivity.class);
                welcomeToSearch.putExtra("Username:", loggedUser);
                startActivity(welcomeToSearch);
            }
        });
    }

    public void fillListView() {
        welcomeAdapter = new SearchWelcomeListAdapter(this, dbHelper.welcomeTeams());
        lvJWelcome_teams.setAdapter(welcomeAdapter);
    }

    public void teamDetailsClick() {
        lvJWelcome_teams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent welcomeToDetails = new Intent(WelcomeActivity.this, TeamDetailsActivity.class);
                Team teamClicked = (Team) adapterView.getItemAtPosition(i);
                welcomeToDetails.putExtra("Username:", loggedUser);
                welcomeToDetails.putExtra("Team clicked:", teamClicked);
                startActivity(welcomeToDetails);
            }
        });
    }

    private void welUpdListen() {
        btnJWelc_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent welcToChooseUpd = new Intent(WelcomeActivity.this, ChooseUpdateActivity.class);
                welcToChooseUpd.putExtra("Username:", loggedUser);
                startActivity(welcToChooseUpd);
            }
        });
    }
}