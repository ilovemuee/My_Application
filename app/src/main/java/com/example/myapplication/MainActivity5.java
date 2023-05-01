package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    private EditText passwordEditText;
    private EditText regNoEditText;
    private EditText sectionEditText;
    private EditText degreeEditText;
    private EditText yearOfJoiningEditText;
    private EditText currentSemesterEditText;

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        passwordEditText = findViewById(R.id.passwordEditText);
        regNoEditText = findViewById(R.id.regNoEditText);
        sectionEditText = findViewById(R.id.sectionEditText);
        degreeEditText = findViewById(R.id.degreeEditText);
        yearOfJoiningEditText = findViewById(R.id.yearOfJoiningEditText);
        currentSemesterEditText = findViewById(R.id.currentSemesterEditText);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase students = new firebase();
                try {
                    String password = passwordEditText.getText().toString();
                    String regNo = regNoEditText.getText().toString();
                    String section = sectionEditText.getText().toString();
                    String degree = degreeEditText.getText().toString();
                    String yearOfJoining = yearOfJoiningEditText.getText().toString();
                    String currentSemester = currentSemesterEditText.getText().toString();
                    firebase.fetch details = students.new fetch(password, regNo, section, degree, yearOfJoining, currentSemester);
                    synchronized (students.insert(details.reg_no, details)) {
                        SharedPreferences sp = getSharedPreferences("message", 0);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("message", "true");
                        ed.apply();
                        Intent intent2 = new Intent(MainActivity5.this, signin.class);
                        startActivity(intent2);
                    }
                }
                catch (Exception e){
                    Toast.makeText(MainActivity5.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

                };

        });
    }
}
