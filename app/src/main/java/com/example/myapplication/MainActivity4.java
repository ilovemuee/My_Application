package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    private Button addButton;
    HashSet ports = new HashSet();
    private int current;
    private Button first;
    private Button second;
    private Button third;
    private ArrayList<object> bigarr = new ArrayList<object>();
    private ArrayList<object> lights = new ArrayList<object>();
    private ArrayList<object> bulbs = new ArrayList<object>();
    private ArrayList<object> swtiches = new ArrayList<object>();
    private String newDataObject;
    ListView listview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listview = findViewById(R.id.listview);
        addButton = findViewById(R.id.button);
        first = findViewById(R.id.button2);
        second = findViewById(R.id.button3);
        third = findViewById(R.id.button4);
        CustomAdapter arr;
        arr = new CustomAdapter(MainActivity4.this, bigarr);
        listview.setAdapter(arr);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 0;
                bigarr.clear();
                bigarr.addAll(lights);
                arr.notifyDataSetChanged();
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 1;
                bigarr.clear();
                bigarr.addAll(bulbs);
                Toast.makeText(MainActivity4.this, String.valueOf(bulbs.toArray().length) ,Toast.LENGTH_SHORT).show();
                arr.notifyDataSetChanged();
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 2;
                bigarr.clear();
                bigarr.addAll(swtiches);
                Toast.makeText(MainActivity4.this, String.valueOf(swtiches.toArray().length) ,Toast.LENGTH_SHORT).show();
                arr.notifyDataSetChanged();
            }
        });
        addButton.setOnClickListener(v -> {
            List<String> options = Arrays.asList("device1", "device2", "device3", "device4", "device5","device6","device7","device8");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select an option");
            builder.setItems(options.toArray(new String[0]), (dialog, which) -> {
                newDataObject = new String(options.get(which));
                AlertDialog.Builder alterbox = new AlertDialog.Builder(MainActivity4.this);
                alterbox.setTitle("Choose the name you want to show for the plugin");
                alterbox.setMessage("");
                final EditText input = new EditText(MainActivity4.this);
                alterbox.setView(input);
                alterbox.setPositiveButton("OK", (dialogInterface, i) -> {
                    String portName = input.getText().toString().trim();
                    if(!ports.contains(newDataObject)) {
                        if (current == 0) {
                            lights.add(new object(portName, newDataObject));
                        } else if (current == 1) {
                            bulbs.add(new object(portName, newDataObject));
                        } else if (current == 2) {
                            swtiches.add(new object(portName, newDataObject));
                        }
                        ports.add(newDataObject);
                    } else {
                        Toast.makeText(this, "Port name is already in use. Please try a different name.", Toast.LENGTH_SHORT).show();
                    }
                });
                alterbox.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());
                alterbox.show();
            });
            builder.show();

        });

    }
}
