package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    EditText etJRegister_username, etJRegister_password;
    TextView txtJRegister_error, txtJRegister_errorEmpty;
    Button btnJRegister_register, btnJRegister_back;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        //Connect the GUI
        etJRegister_username = findViewById(R.id.etVRegister_username);
        etJRegister_password = findViewById(R.id.etVRegister_password);
        txtJRegister_error = findViewById(R.id.txtVRegister_error);
        txtJRegister_errorEmpty = findViewById(R.id.txtVRegister_errorEmpty);
        btnJRegister_register = findViewById(R.id.btnVRegister_register);
        btnJRegister_back = findViewById(R.id.btnVRegister_back);

        //Make the TextViews invisible
        txtJRegister_error.setVisibility(View.INVISIBLE);
        txtJRegister_errorEmpty.setVisibility(View.INVISIBLE);

        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        //Call button listeners
        btnRegisterRegisterListener();
        btnRegisterBackListener();
    }

    public void btnRegisterRegisterListener() {
        btnJRegister_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerUsername = etJRegister_username.getText().toString();
                String registerPassword = etJRegister_password.getText().toString();
                //Check if either EditText is empty
                if(registerUsername.isEmpty() || registerPassword.isEmpty()) {
                    txtJRegister_errorEmpty.setVisibility(View.VISIBLE);
                }
                //Add user to database if otherwise.
                else {
                    txtJRegister_errorEmpty.setVisibility(View.INVISIBLE);

                    if (dbHelper.usernameAvailable(registerUsername)) {
                        //Create new User object
                        User newUser = new User();
                        newUser.setuName(registerUsername);
                        newUser.setpWord(registerUsername);

                        txtJRegister_error.setVisibility(View.INVISIBLE);
                        dbHelper.addNewUser(registerUsername, registerPassword);
                        Intent registerToMain = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(registerToMain);
                    }
                    else{
                        txtJRegister_error.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void btnRegisterBackListener() {
        btnJRegister_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerToMain = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(registerToMain);
            }
        });
    }
}