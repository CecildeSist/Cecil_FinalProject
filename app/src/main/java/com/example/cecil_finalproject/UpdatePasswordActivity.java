package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdatePasswordActivity extends AppCompatActivity {
    TextView upPassUname, errorOldMustMatch, errorNoChange;
    EditText oldPassJ, newPassJ;
    Button upPassYesJ, upPassNoJ;
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_password);
        //Set GUI elements
        upPassUname = findViewById(R.id.txtUpPass_uname);
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        upPassUname.setText("Updating " + loggedUser + "'s Password");

        errorOldMustMatch = findViewById(R.id.upPassErrorMustMatch);
        errorOldMustMatch.setVisibility(View.INVISIBLE);

        errorNoChange = findViewById(R.id.upPassErrorSame);
        errorNoChange.setVisibility(View.INVISIBLE);

        oldPassJ = findViewById(R.id.oldPassV);
        newPassJ = findViewById(R.id.newPassV);
        upPassYesJ = findViewById(R.id.upPassYesV);
        upPassNoJ = findViewById(R.id.upPassNoV);

        //Call button listeners
        upPassBack();
        upBassForward();
    }

    private void upPassBack() {
        upPassNoJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent upPass_to_chooseUp = new Intent(UpdatePasswordActivity.this, ChooseUpdateActivity.class);
                upPass_to_chooseUp.putExtra("Username:", loggedUser);
                startActivity(upPass_to_chooseUp);
            }
        });
    }

    private void upBassForward() {
        upPassYesJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = oldPassJ.getText().toString();
                String newPass = newPassJ.getText().toString();

                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                String oldPassCorrect = dbHelper.oldPasswordCorrect(loggedUser);

                if (!oldPass.equals(oldPassCorrect)) {
                    errorOldMustMatch.setVisibility(View.VISIBLE);
                    errorNoChange.setVisibility(View.INVISIBLE);
                }
                else if (oldPass.equals(newPass)) {
                    errorOldMustMatch.setVisibility(View.INVISIBLE);
                    errorNoChange.setVisibility(View.VISIBLE);
                }
                else {
                    errorOldMustMatch.setVisibility(View.INVISIBLE);
                    errorNoChange.setVisibility(View.INVISIBLE);
                    dbHelper.updatePass(loggedUser, newPass);
                    Intent upPass_to_Start = new Intent(UpdatePasswordActivity.this, MainActivity.class);
                    startActivity(upPass_to_Start);
                }
            }
        });
    }
}