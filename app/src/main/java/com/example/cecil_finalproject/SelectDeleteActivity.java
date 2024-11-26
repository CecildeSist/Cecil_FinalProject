package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SelectDeleteActivity extends AppCompatActivity {
    TextView txtJSelectDelete;
    Spinner spnJSelectDelete;
    Button btnJSelectDelete_proceed, btnJSelectDelete_back;

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

        //Call button listeners
        selectDeleteProceedListener();
        selectDeleteBackListener();
    }

    //NOTE TO SELF not done
    private void selectDeleteProceedListener() {
        btnJSelectDelete_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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