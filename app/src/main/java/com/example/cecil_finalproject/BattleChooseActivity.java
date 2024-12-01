package com.example.cecil_finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BattleChooseActivity extends AppCompatActivity {
    TextView txtJBaChoo_userVersusUser, txtJBaChoo_userPlease;
    Spinner spnJBaChoo;
    ImageButton imgButJBaChoo;
    Button btnJBaChoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle_choose);

        //Connect GUI elements
        txtJBaChoo_userVersusUser = findViewById(R.id.txtVBattleChoose_trainerNames);
        txtJBaChoo_userPlease = findViewById(R.id.txtVBaChoo_userPlease);
        spnJBaChoo = findViewById(R.id.spnVBattleChoose_teams);
        imgButJBaChoo = findViewById(R.id.imgBtnVBaChoo_battle);
        btnJBaChoo = findViewById(R.id.btnVBattleChoose_back);

        //Set usernames in text boxes NOTE TO SELF NOT DONE
    }
}