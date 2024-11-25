package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    TextView txtJWelcome_username;
    Button btnJWelcome_create, btnJWelcome_delete;
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

        //Fill welcomeTeamsList if empty
        if (welcomeTeamsList.isEmpty()) {
            Team teamOne = new Team(1, 390F, "CecilDeSist", "Magmar", "Weezing", "Mister Mime", "Zapdos", "Wigglytuff", "Hitmonlee");
            welcomeTeamsList.add(teamOne);
            Team teamTwo = new Team(2, 410.833F, "Mephistopheles", "Mister Mime", "Scyther", "Venomoth", "Parasect", "Mewtwo", "Magneton");
            welcomeTeamsList.add(teamTwo);
        }

        //Add team to array list if passed from CreateTeamActivity NOTE TO SELF not done


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
}