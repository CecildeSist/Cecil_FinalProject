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
    TextView txtJRegister_error;
    Button btnJRegister_register, btnJRegister_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        //Connect the GUI
        etJRegister_username = findViewById(R.id.etVRegister_username);
        etJRegister_password = findViewById(R.id.etVRegister_password);
        txtJRegister_error = findViewById(R.id.txtVRegister_error);
        btnJRegister_register = findViewById(R.id.btnVRegister_register);
        btnJRegister_back = findViewById(R.id.btnVRegister_back);

        //Make the TextView invisible
        txtJRegister_error.setVisibility(View.INVISIBLE);

        //Call button listeners
        btnRegisterRegisterListener();
        btnRegisterBackListener();
    }

    //NOTE TO SELF not done
    public void btnRegisterRegisterListener() {
        btnJRegister_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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