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

public class ChooseUpdateActivity extends AppCompatActivity {
    TextView chooseUpUname;
    Spinner chooseUpSpin;
    Button chooseUpYes, chooseUpNo;

    String choice;
    ArrayList<String> chooseUpChoices = new ArrayList<>();
    ArrayAdapter<String> cUChoicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_update);

        //Connect GUI elements
        chooseUpUname = findViewById(R.id.txtVchooseUp_uname);
        chooseUpSpin = findViewById(R.id.spnVchooseUp);
        chooseUpYes = findViewById(R.id.btnVChooseUp_confirm);
        chooseUpNo = findViewById(R.id.btnVChooseUp_back);

        //Populate the spinner
        chooseUpChoices.add("My password");
        chooseUpChoices.add("A team");
        chooseUpChoices.add("A review");

        cUChoicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, chooseUpChoices);
        chooseUpSpin.setAdapter(cUChoicesAdapter);

        //Set text
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        chooseUpUname.setText("Welcome, " + loggedUser + "!");

        //Call spinner listener
        chooseUpSpinListen();

        //Call button listeners
        chooseUpBack();
        chooseUpForward();
    }

    private void chooseUpSpinListen() {
        chooseUpSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choice = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void chooseUpBack() {
        chooseUpNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent chooseUpToWelcome = new Intent(ChooseUpdateActivity.this, WelcomeActivity.class);
                chooseUpToWelcome.putExtra("Username:", loggedUser);
                startActivity(chooseUpToWelcome);
            }
        });
    }

    private void chooseUpForward() {
        chooseUpYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                if (choice.equals("My password")) {
                    Intent chooseUp_to_upPass = new Intent(ChooseUpdateActivity.this, UpdatePasswordActivity.class);
                    chooseUp_to_upPass.putExtra("Username:", loggedUser);
                    startActivity(chooseUp_to_upPass);
                }
                else if (choice.equals("A team")) {
                    Intent chooseUp_to_chooseTeam = new Intent(ChooseUpdateActivity.this, ChooseTeamToUpdateActivity.class);
                    chooseUp_to_chooseTeam.putExtra("Username:", loggedUser);
                    startActivity(chooseUp_to_chooseTeam);
                }
                else if (choice.equals("A review")) {
                    Intent chooseUp_to_chooseRev = new Intent(ChooseUpdateActivity.this, ChooseReviewActivity.class);
                    chooseUp_to_chooseRev.putExtra("Username:", loggedUser);
                    startActivity(chooseUp_to_chooseRev);
                }
            }
        });
    }
}