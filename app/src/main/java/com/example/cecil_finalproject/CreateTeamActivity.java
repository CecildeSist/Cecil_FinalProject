package com.example.cecil_finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateTeamActivity extends AppCompatActivity {
    TextView txtJCreate_header, txtJCreate_errorSix;
    Spinner pkmn1Spin, pkmn2Spin, pkmn3Spin, pkmn4Spin, pkmn5Spin, pkmn6Spin;
    Button btnJCreate_create, btnJCreate_back;

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

        txtJCreate_errorSix = findViewById(R.id.txtVCreate_errorSix);
        //Make this invisible
        txtJCreate_errorSix.setVisibility(View.INVISIBLE);

        btnJCreate_create = findViewById(R.id.btnVCreateTeam_createTeam);
        btnJCreate_back = findViewById(R.id.btnVCreateTeam_back);
        
    }
}