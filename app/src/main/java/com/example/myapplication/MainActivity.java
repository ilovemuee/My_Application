package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton speaker_buton;
    private boolean current = false;
    TextView textView;
    EditText editText;
    ImageButton button;
    ImageButton button2;
    Adaptor adaptor;
    RecyclerView recyclerView;
    ArrayList<Modelclass> list;
    private TextToSpeech tts;
    private ImageButton history;
    private ImageButton emojiButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        emojiButton = findViewById(R.id.emoji_button);
        speaker_buton = findViewById(R.id.button4);
        history = findViewById(R.id.history);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.Button3);
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
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingLayout floatingLayout = new FloatingLayout(MainActivity.this);
                floatingLayout.createFloatingWindow();
            }
        });
        emojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  current = !current;
                  if(current){
                      emojiButton.setBackgroundResource(R.drawable.ic_baseline_emoji_emotions_24);
                  }
                  else{
                      emojiButton.setBackgroundResource(R.drawable.ic_baseline_emoji_emotions_2);
                  }

            }
        });
        recyclerView.setAdapter(adaptor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sended = editText.getText().toString();
                list.add(new Modelclass(Modelclass.Layout_two, sended));
                python python = new python();
                python2 python2 = new python2();
                String recieved;

                if(current) {
                    recieved = python.python(sended, MainActivity.this);
                }
                else{
                    recieved = python2.python2(sended, MainActivity.this);
                }
                recieved = recieved.trim();
                if(recieved.equals("-1")){
                    Intent intent = new Intent(MainActivity.this,MainActivity4.class);
                    startActivity(intent);
                }
                else{
                    list.add(new Modelclass(Modelclass.Layout_One, recieved));
                    MainHelper db = new MainHelper(MainActivity.this);
                    String id = String.valueOf((Integer.valueOf((int) (Math.random()*99999999))));
                    boolean worked = db.insertData(id,sended,recieved);
                }
                synchronized (recieved) {
                    tts.speak(recieved, TextToSpeech.QUEUE_FLUSH, null);
                }


                adaptor.notifyDataSetChanged();

            }
        });
        speaker_buton.setOnClickListener(new View.OnClickListener() {
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
                    python2 python2 = new python2();
                    String a;
                    if(current) {
                        a = python.python(result.get(0), MainActivity.this);
                    }
                    else{
                        a = python2.python2(result.get(0), MainActivity.this);
                    }
                    a = a.trim();
                    if(a.equals("-1")){
                        Intent intent = new Intent(MainActivity.this,MainActivity4.class);
                        startActivity(intent);
                        return;
                    }
                    String id = String.valueOf((Integer.valueOf((int) (Math.random()*99999999))));
                    Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
                    if(!a.equals("-1")){
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



