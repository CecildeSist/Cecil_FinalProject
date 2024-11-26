package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    Spinner spnJSearch_users, spnJSearch_pkmn, spnJSearch_types;
    EditText etJSearch_lower, etJSearch_upper;
    ImageButton imgBtnJSearch;
    Button btnJSearch;
    ListView lvJSearch;

    DatabaseHelper dbHelper;
    static ArrayList<Team> searchTeamsList = new ArrayList<>();
    SearchWelcomeListAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        //Connect GUI elements
        spnJSearch_users = findViewById(R.id.spnVSearch_usernames);
        spnJSearch_pkmn = findViewById(R.id.spnVSearch_pokemon);
        spnJSearch_types = findViewById(R.id.spnVSearch_type);
        etJSearch_lower = findViewById(R.id.etVSearch_lower);
        etJSearch_upper = findViewById(R.id.etVSearch_upper);
        imgBtnJSearch = findViewById(R.id.imgbtnVSearch_search);
        btnJSearch = findViewById(R.id.btnVSearch_back);
        lvJSearch = findViewById(R.id.lvVSearch);

        //Set up database helper
        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        //Pass the logged-in user
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

        //NOTE TO SELF must assign view to listview NOTE TO SELF not done

        //Call button listeners
        searchListener();
        searchBackListener();
    }

    //NOTE TO SELF not done
    private void searchListener() {
        imgBtnJSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void searchBackListener() {
        btnJSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent searchToWelcome = new Intent(SearchActivity.this, WelcomeActivity.class);
                searchToWelcome.putExtra("Username:", loggedUser);
                startActivity(searchToWelcome);
            }
        });
    }
}