//===
//Author        : Cecil
//Date          : November 4th, 2024
//Description   :  NOTE TO SELF add later
//===

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

    EditText etJMain_username, etJMain_password;
    TextView txtJMain_error;
    Button btnJMain_logIn, btnJMain_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Connect GUI elements
        etJMain_username = findViewById(R.id.etVMain_username);
        etJMain_password = findViewById(R.id.etVMain_password);
        txtJMain_error = findViewById(R.id.txtVMain_error);
        btnJMain_logIn = findViewById(R.id.btnVMain_logIn);
        btnJMain_register = findViewById(R.id.btnVMain_register);

        //Make error text invisible
        txtJMain_error.setVisibility(View.INVISIBLE);

        //Call the button listeners
        btnMainLogInListener();
        btnMainRegisterListener();
    }

    //NOTE TO SELF not done
    public void btnMainLogInListener() {
        btnJMain_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void btnMainRegisterListener(){
        Intent mainToRegister = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(mainToRegister);
    }
}