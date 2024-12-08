package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteTeamActivity extends AppCompatActivity {
    TextView dT_u;
    ListView dT_L;
    Button dT_b;

    DatabaseHelper dbHelper;
    TeamDeleteAdapter td_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_team);

        //Connect GUI elements
        dT_u = findViewById(R.id.deleteTeam_username);
        dT_L = findViewById(R.id.delT_list);
        dT_b = findViewById(R.id.delT_btn);

        //Set text
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        dT_u.setText(loggedUser + ", please click on the team you want to delete. Make sure you're sure about this!");

        //Call button listener
        dTBack();
    }

    private void dTBack() {
        dT_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent delT_selDel = new Intent(DeleteTeamActivity.this, SelectDeleteActivity.class);
                delT_selDel.putExtra("Username:", loggedUser);
                startActivity(delT_selDel);
            }
        });
    }
}