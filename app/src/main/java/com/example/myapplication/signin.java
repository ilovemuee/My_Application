package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import kotlin.jvm.Synchronized;

public class signin extends AppCompatActivity {
    public static ArrayList<String> arrayList = new ArrayList<String>();
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        firebase details = new firebase();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                synchronized (details.read(username)) {
                    try {
                            String a = arrayList.get(2);
                            String b = arrayList.get(3);
                            if (password.equals(a) && username.equals(b)) {
                                Intent intent = new Intent(signin.this, MainActivity.class);
                                startActivity(intent);
                            }

                    } catch (Exception e) {
                        Toast.makeText(signin.this, "please provide valid login details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
