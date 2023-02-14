package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView his_page;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MainHelper db = new MainHelper(this);
        Cursor data = db.getdata();
        ArrayList<User> his_List = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                String question = data.getString(1);
                String answer = data.getString(2);
                long currentTime = System.currentTimeMillis();
                Date date = new Date(currentTime);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formattedDate = simpleDateFormat.format(date);
                his_List.add(new User(question,answer,formattedDate));
            }while (data.moveToNext());
        }
        his_page =findViewById(R.id.lis_Of_conversation);
        List_Adaptor his_lis_adap = new List_Adaptor(his_List,MainActivity2.this);
        his_page.setAdapter(his_lis_adap);
        data.close();
    }
}