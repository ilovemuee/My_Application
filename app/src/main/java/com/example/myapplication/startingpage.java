package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class startingpage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startingpage);
        getSupportActionBar().hide();
        Intent intent2 = new Intent(this, signin.class);
        Intent intent = new Intent(this, MainActivity5.class);
        SharedPreferences sp = getSharedPreferences("message", 0);
        String s = sp.getString("message", "-1");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        Handler h = new Handler(Looper.getMainLooper());
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s.equals("-1")) {
                    startActivity(intent);
                } else {
                    startActivity(intent2);
                }
                finish();
            }
        }, 1750);
    }
}