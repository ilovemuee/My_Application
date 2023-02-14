package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton imagebutton;
    TextView textView;
    EditText editText;
    Button button;
    Adaptor adaptor;
    RecyclerView recyclerView;
    ArrayList<Modelclass> list;
    private TextToSpeech tts;
    private Button history;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagebutton = findViewById(R.id.imageButton);
        list = new ArrayList<>();
        history = findViewById(R.id.history);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new Adaptor(list,MainActivity.this);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.getDefault());
                }
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adaptor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                python python = new python();
                String sended = editText.getText().toString();
                String recieved = python.python(sended,MainActivity.this);
                synchronized (recieved) {
                    tts.speak(recieved, TextToSpeech.QUEUE_FLUSH, null);
                }
                if(recieved.equals("-1")){
                        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                        startActivity(intent);
                }
                else{
                    list.add(new Modelclass(Modelclass.Layout_One, sended));
                    list.add(new Modelclass(Modelclass.Layout_two, recieved));
                    MainHelper db = new MainHelper(MainActivity.this);
                    String id = String.valueOf((Integer.valueOf((int) (Math.random()*99999999))));
                    boolean worked = db.insertData(id,sended,recieved);
                    adaptor.notifyDataSetChanged();
                }
            }
        });
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                } else {
                    Toast.makeText(MainActivity.this, "Your device Don't Support speech input", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 10:
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    python python = new python();
                    String a = python.python(result.get(0),MainActivity.this);
                    if(a.equals("-1")){
                        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                        startActivity(intent);
                        return;
                    }
                    String id = String.valueOf((Integer.valueOf((int) (Math.random()*99999999))));
                    Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
                    if(a.equals("-1")){
                        synchronized (a) {
                            tts.speak(a, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                    list.add(new Modelclass(Modelclass.Layout_One, result.get(0)));
                    list.add(new Modelclass(Modelclass.Layout_two, a));
                    MainHelper db = new MainHelper(this);
                    boolean worked = db.insertData(id,result.get(0),a);
                    adaptor.notifyDataSetChanged();
                    break;
            }
        }
    }

}



