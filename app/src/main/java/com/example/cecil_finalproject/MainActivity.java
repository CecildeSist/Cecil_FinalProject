//===
//Author        : Cecil
//Date          : November 4th, 2024
//Description   : NOTE TO SELF add this later
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

public class MainActivity extends AppCompatActivity {
    EditText etJMain_u, etJMain_p;
    TextView txtJMain_error;
    Button btnJMain_logIn, btnJMain_register;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Connect the GUI
        etJMain_u = findViewById(R.id.etVMain_username);
        etJMain_p = findViewById(R.id.etVMain_password);
        txtJMain_error = findViewById(R.id.txtVMain_error);
        btnJMain_logIn = findViewById(R.id.btnVMain_logIn);
        btnJMain_register = findViewById(R.id.btnVMain_register);

        //Make error message invisible
        txtJMain_error.setVisibility(View.INVISIBLE);

        dbHelper = new DatabaseHelper(this);

        //Call button listeners
        mainLogInBtnListener();
        mainRegisterBtnListener();
    }

    public void mainLogInBtnListener() {
        btnJMain_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logU = etJMain_u.getText().toString();
                String logP = etJMain_p.getText().toString();
                boolean isLogInValid = dbHelper.logInValid(logU, logP);
                if (isLogInValid == false) {
                    txtJMain_error.setVisibility(View.VISIBLE);
                }
                else {
                    txtJMain_error.setVisibility(View.INVISIBLE);
                    Intent mainToWelcome = new Intent(MainActivity.this, WelcomeActivity.class);
                    mainToWelcome.putExtra("Username:", logU);
                    startActivity(mainToWelcome);
                }
            }
        });
    }

    public void mainRegisterBtnListener() {
        btnJMain_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainToRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(mainToRegister);
            }
        });
    }
}