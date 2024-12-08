package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmDeleteActivity extends AppCompatActivity {
    TextView tJCon_hold, tJCon_this, tJCon_1, tJCon_2, tJCon_3, tJCon_know;
    Button bJCon_y, bJCon_n;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_delete);

        //Connect GUI elements
        tJCon_hold = findViewById(R.id.txtVConfirmDelete_holdOn);
        tJCon_this = findViewById(R.id.txtVConDel_thisIsWhat);
        tJCon_1 = findViewById(R.id.txtVConfirm_1);
        tJCon_2 = findViewById(R.id.txtVConfirm_2);
        tJCon_3 = findViewById(R.id.txtVConfirm_3);
        tJCon_know = findViewById(R.id.txtVConfirm_still);
        bJCon_y = findViewById(R.id.btnVConfirm_yes);
        bJCon_n = findViewById(R.id.btnVConfirm_no);

        //Set the text
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        tJCon_hold.setText("Hold your Horseas, " + loggedUser + "! Do you know the consequences of your actions?");

        dbHelper = new DatabaseHelper(this);

        //Call button listeners
        conYesListener();
        conNoListener();
    }

    private void conYesListener() {
        bJCon_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent conD_main = new Intent(ConfirmDeleteActivity.this, MainActivity.class);
                dbHelper.deleteAccount(loggedUser);
                startActivity(conD_main);
            }
        });
    }

    private void conNoListener() {
        bJCon_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent confirmD_selDel = new Intent(ConfirmDeleteActivity.this, SelectDeleteActivity.class);
                confirmD_selDel.putExtra("Username:", loggedUser);
                startActivity(confirmD_selDel);
            }
        });
    }
}