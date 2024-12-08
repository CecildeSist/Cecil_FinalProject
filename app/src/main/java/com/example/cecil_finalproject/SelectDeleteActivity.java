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

import java.util.ArrayList;

public class SelectDeleteActivity extends AppCompatActivity {
    TextView txtJSelectDelete;
    Spinner spnJSelectDelete;
    Button btnJSelectDelete_proceed, btnJSelectDelete_back;

    ArrayList<String> deleteChoices = new ArrayList<>();
    ArrayAdapter<String> choicesAdapter;
    String deleteChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_delete);

        //Connect GUI elements
        txtJSelectDelete = findViewById(R.id.txtVSelectDelete_username);
        spnJSelectDelete = findViewById(R.id.spnVSelectDelete);
        btnJSelectDelete_proceed = findViewById(R.id.btnVSelectDelete_proceed);
        btnJSelectDelete_back = findViewById(R.id.btnVSelectDelete_back);

        //Put user's username in the textview
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        txtJSelectDelete.setText(loggedUser + ", what do you want to delete?");

        //Populate the spinner
        deleteChoices.add("A review I made");
        deleteChoices.add("A team I made");
        deleteChoices.add("My entire account");
        choicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, deleteChoices);
        spnJSelectDelete.setAdapter(choicesAdapter);
        spinnerListener();

        //Call button listeners
        selectDeleteProceedListener();
        selectDeleteBackListener();
    }

    private void spinnerListener() {
        spnJSelectDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deleteChoice = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //NOTE TO SELF not done
    private void selectDeleteProceedListener() {
        btnJSelectDelete_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                if (deleteChoice.equals("A review I made")) {
                    Intent selDel_to_delRev = new Intent(SelectDeleteActivity.this, DeleteReviewActivity.class);
                    selDel_to_delRev.putExtra("Username:", loggedUser);
                    startActivity(selDel_to_delRev);
                }
                else if (deleteChoice.equals("A team I made")) {
                    Intent selDel_teamDel = new Intent(SelectDeleteActivity.this, DeleteTeamActivity.class);
                    selDel_teamDel.putExtra("Username:", loggedUser);
                    startActivity(selDel_teamDel);
                }
            }
        });
    }

    private void selectDeleteBackListener() {
        btnJSelectDelete_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent selDelToWelcome = new Intent(SelectDeleteActivity.this, WelcomeActivity.class);
                selDelToWelcome.putExtra("Username:", loggedUser);
                startActivity(selDelToWelcome);
            }
        });
    }
}