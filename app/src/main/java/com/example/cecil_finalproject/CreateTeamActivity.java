package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CreateTeamActivity extends AppCompatActivity {
    TextView txtJCreate_header;
    Spinner pkmn1Spin, pkmn2Spin, pkmn3Spin, pkmn4Spin, pkmn5Spin, pkmn6Spin;
    Button btnJCreate_create, btnJCreate_back;

    ArrayList<String> listOfPokemon = new ArrayList<>();
    ArrayAdapter<String> pkmnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_team);

        //Grab UI elements
        txtJCreate_header = findViewById(R.id.txtVCreate_header);

        pkmn1Spin = findViewById(R.id.spnVCreateTeam_pkmn1);
        pkmn2Spin = findViewById(R.id.spnVCreateTeam_pkmn2);
        pkmn3Spin = findViewById(R.id.spnVCreate_pkmn3);
        pkmn4Spin = findViewById(R.id.spnVCreate_pkmn4);
        pkmn5Spin = findViewById(R.id.spnVCreateTeam_pkmn5);
        pkmn6Spin = findViewById(R.id.spnVCreate_pkmn6);

        btnJCreate_create = findViewById(R.id.btnVCreateTeam_createTeam);
        btnJCreate_back = findViewById(R.id.btnVCreateTeam_back);

        //Change the TextView at the top to display the current user's username
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        txtJCreate_header.setText(loggedUser +"'s New Team");

        //Populate the spinners
        listOfPokemon.add("Venusaur");
        listOfPokemon.add("Charizard");
        listOfPokemon.add("Blastoise");
        listOfPokemon.add("Butterfree");
        listOfPokemon.add("Beedrill");
        listOfPokemon.add("Pidgeot");
        listOfPokemon.add("Raticate");
        listOfPokemon.add("Fearow");
        listOfPokemon.add("Arbok");
        listOfPokemon.add("Raichu");
        listOfPokemon.add("Sandslash");
        listOfPokemon.add("Nidoqueen");
        listOfPokemon.add("Nidoking");
        listOfPokemon.add("Clefable");
        listOfPokemon.add("Ninetales");
        listOfPokemon.add("Wigglytuff");
        listOfPokemon.add("Golbat");
        listOfPokemon.add("Vileplume");
        listOfPokemon.add("Parasect");
        listOfPokemon.add("Venomoth");
        listOfPokemon.add("Dugtrio");
        listOfPokemon.add("Persian");
        listOfPokemon.add("Golduck");
        listOfPokemon.add("Primeape");
        listOfPokemon.add("Arcanine");
        listOfPokemon.add("Poliwrath");
        listOfPokemon.add("Alakazam");
        listOfPokemon.add("Machamp");
        listOfPokemon.add("Victreebel");
        listOfPokemon.add("Tentacruel");
        listOfPokemon.add("Golem");
        listOfPokemon.add("Rapidash");
        listOfPokemon.add("Slowbro");
        listOfPokemon.add("Magneton");
        listOfPokemon.add("Farfetchd");
        listOfPokemon.add("Dodrio");
        listOfPokemon.add("Dewgong");
        listOfPokemon.add("Muk");
        listOfPokemon.add("Cloyster");
        listOfPokemon.add("Gengar");
        listOfPokemon.add("Onix");
        listOfPokemon.add("Hypno");
        listOfPokemon.add("Kingler");
        listOfPokemon.add("Electrode");
        listOfPokemon.add("Marowak");
        listOfPokemon.add("Hitmonlee");
        listOfPokemon.add("Hitmonchan");
        listOfPokemon.add("Lickitung");
        listOfPokemon.add("Weezing");
        listOfPokemon.add("Rhydon");
        listOfPokemon.add("Chansey");
        listOfPokemon.add("Tangela");
        listOfPokemon.add("Kangaskhan");
        listOfPokemon.add("Seadra");
        listOfPokemon.add("Seaking");
        listOfPokemon.add("Starmie");
        listOfPokemon.add("Mister Mime");
        listOfPokemon.add("Scyther");
        listOfPokemon.add("Jynx");
        listOfPokemon.add("Electabuzz");
        listOfPokemon.add("Magmar");
        listOfPokemon.add("Pinsir");
        listOfPokemon.add("Tauros");
        listOfPokemon.add("Gyarados");
        listOfPokemon.add("Lapras");
        listOfPokemon.add("Ditto");
        listOfPokemon.add("Vaporeon");
        listOfPokemon.add("Jolteon");
        listOfPokemon.add("Flareon");
        listOfPokemon.add("Porygon");
        listOfPokemon.add("Omastar");
        listOfPokemon.add("Kabutops");
        listOfPokemon.add("Aerodactyl");
        listOfPokemon.add("Snorlax");
        listOfPokemon.add("Articuno");
        listOfPokemon.add("Zapdos");
        listOfPokemon.add("Moltres");
        listOfPokemon.add("Mewtwo");
        listOfPokemon.add("Mew");

        pkmnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listOfPokemon);
        pkmn1Spin.setAdapter(pkmnAdapter);
        pkmn2Spin.setAdapter(pkmnAdapter);
        pkmn3Spin.setAdapter(pkmnAdapter);
        pkmn4Spin.setAdapter(pkmnAdapter);
        pkmn5Spin.setAdapter(pkmnAdapter);
        pkmn6Spin.setAdapter(pkmnAdapter);

        //Call spinner listeners NOTE TO SELF not done
        firstSpinnerListener();

        //Call button listeners NOTE TO SELF not done
        createTeamListener();
        backListener();
    }

    //NOTE TO SELF not done
    private void firstSpinnerListener() {
        pkmn1Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void createTeamListener() {
        btnJCreate_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();

                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent createToWelcome = new Intent(CreateTeamActivity.this, WelcomeActivity.class);
                createToWelcome.putExtra("Username:", loggedUser);

                //Create new team
                //Step 1: declare objects added to team data
                Integer teamID;
                String trainerName, pkmnA, pkmnB, pkmnC, pkmnD, pkmnE, pkmnF;
                Float averageBST;

                trainerName = loggedUser;

                //Step 2: Assign value to the objects NOTE TO SELF not done
                Team newTeam = new Team();

                //Step 3: put team in putExtra under "Team:" and create function to update the listview based on what's in the database NOTE TO SELF not done
            }
        });
    }

    private void backListener() {
        btnJCreate_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();

                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent createToWelcome = new Intent(CreateTeamActivity.this, WelcomeActivity.class);
                createToWelcome.putExtra("Username:", loggedUser);
                startActivity(createToWelcome);
            }
        });
    }
}