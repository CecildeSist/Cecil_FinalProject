package com.example.cecil_finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BattleResultsActivity extends AppCompatActivity {
    TextView tJRes_curU, tJRes_curT, tJRes_curB, tJRes_choU, tJRes_choT, tJRes_choB, tJRes_res;
    Button btnJRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle_results);

        //Connect GUI elements
        tJRes_curU = findViewById(R.id.txtVResults_curUse);
        tJRes_curT = findViewById(R.id.txtVBattleResults_chosenUserTeam);
        tJRes_curB = findViewById(R.id.txtVBattleResults_chosenUserBST);
        tJRes_choU = findViewById(R.id.txtVBattleResults_chosenUser);
        tJRes_choT = findViewById(R.id.txtVBattleResults_chosenUserTeam);
        tJRes_choB = findViewById(R.id.txtVBattleResults_chosenUserBST);
        tJRes_res = findViewById(R.id.txtVBattleResults_results);
        btnJRes = findViewById(R.id.btnVBattleResults_ok);

        //Set text NOTE TO SELF not done

        //call button listener NOTE TO SELF not done
    }
}