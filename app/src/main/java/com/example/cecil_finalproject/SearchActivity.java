package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    Spinner spnJSearch_pkmn;
    EditText etJSearch_user;
    ImageButton imgBtnJSearch;
    Button btnJSearch;
    ListView lvJSearch;

    DatabaseHelper dbHelper;
    static ArrayList<Integer> searchTeamIDs = new ArrayList<>();
    static ArrayList<Team> searchTeamsList = new ArrayList<>();
    SearchWelcomeListAdapter searchAdapter;

    String strSpecies, strType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        //Connect GUI elements
        etJSearch_user = findViewById(R.id.etSearchCreator);
        spnJSearch_pkmn = findViewById(R.id.spnVSearch_pokemon);
        imgBtnJSearch = findViewById(R.id.imgbtnVSearch_search);
        btnJSearch = findViewById(R.id.btnVSearch_back);
        lvJSearch = findViewById(R.id.lvVSearch);

        //Set up database helper
        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        //Pass the logged-in user
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

        //Populate the spinners
        ArrayList<String> searchListPkmn = new ArrayList<>();
        searchListPkmn.add("");
        searchListPkmn.add("Venusaur");
        searchListPkmn.add("Charizard");
        searchListPkmn.add("Blastoise");
        searchListPkmn.add("Butterfree");
        searchListPkmn.add("Beedrill");
        searchListPkmn.add("Pidgeot");
        searchListPkmn.add("Raticate");
        searchListPkmn.add("Fearow");
        searchListPkmn.add("Arbok");
        searchListPkmn.add("Raichu");
        searchListPkmn.add("Sandslash");
        searchListPkmn.add("Nidoqueen");
        searchListPkmn.add("Nidoking");
        searchListPkmn.add("Clefable");
        searchListPkmn.add("Ninetales");
        searchListPkmn.add("Wigglytuff");
        searchListPkmn.add("Golbat");
        searchListPkmn.add("Vileplume");
        searchListPkmn.add("Parasect");
        searchListPkmn.add("Venomoth");
        searchListPkmn.add("Dugtrio");
        searchListPkmn.add("Persian");
        searchListPkmn.add("Golduck");
        searchListPkmn.add("Primeape");
        searchListPkmn.add("Arcanine");
        searchListPkmn.add("Poliwrath");
        searchListPkmn.add("Alakazam");
        searchListPkmn.add("Machamp");
        searchListPkmn.add("Victreebel");
        searchListPkmn.add("Tentacruel");
        searchListPkmn.add("Golem");
        searchListPkmn.add("Rapidash");
        searchListPkmn.add("Slowbro");
        searchListPkmn.add("Magneton");
        searchListPkmn.add("Farfetchd");
        searchListPkmn.add("Dodrio");
        searchListPkmn.add("Dewgong");
        searchListPkmn.add("Muk");
        searchListPkmn.add("Cloyster");
        searchListPkmn.add("Gengar");
        searchListPkmn.add("Onix");
        searchListPkmn.add("Hypno");
        searchListPkmn.add("Kingler");
        searchListPkmn.add("Electrode");
        searchListPkmn.add("Marowak");
        searchListPkmn.add("Hitmonlee");
        searchListPkmn.add("Hitmonchan");
        searchListPkmn.add("Lickitung");
        searchListPkmn.add("Weezing");
        searchListPkmn.add("Rhydon");
        searchListPkmn.add("Chansey");
        searchListPkmn.add("Tangela");
        searchListPkmn.add("Kangaskhan");
        searchListPkmn.add("Seadra");
        searchListPkmn.add("Seaking");
        searchListPkmn.add("Starmie");
        searchListPkmn.add("Mr Mime");
        searchListPkmn.add("Scyther");
        searchListPkmn.add("Jynx");
        searchListPkmn.add("Electabuzz");
        searchListPkmn.add("Magmar");
        searchListPkmn.add("Pinsir");
        searchListPkmn.add("Tauros");
        searchListPkmn.add("Gyarados");
        searchListPkmn.add("Lapras");
        searchListPkmn.add("Ditto");
        searchListPkmn.add("Vaporeon");
        searchListPkmn.add("Jolteon");
        searchListPkmn.add("Flareon");
        searchListPkmn.add("Porygon");
        searchListPkmn.add("Omastar");
        searchListPkmn.add("Kabutops");
        searchListPkmn.add("Aerodactyl");
        searchListPkmn.add("Snorlax");
        searchListPkmn.add("Articuno");
        searchListPkmn.add("Zapdos");
        searchListPkmn.add("Moltres");
        searchListPkmn.add("Mewtwo");
        searchListPkmn.add("Mew");

        ArrayAdapter searchPkmn = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, searchListPkmn);
        spnJSearch_pkmn.setAdapter(searchPkmn);

        //Spinner listener
        speciesSpinner();

        //Call button listeners
        searchListener();
        searchBackListener();
    }

    private void speciesSpinner() {
        spnJSearch_pkmn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strSpecies = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //NOTE TO SELF not done
    private void searchListener() {
        imgBtnJSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strCreator = "";

                if(!etJSearch_user.getText().toString().isEmpty()) {
                    strCreator = etJSearch_user.getText().toString();
                }

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