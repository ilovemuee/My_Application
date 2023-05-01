package com.example.myapplication;

import android.view.inspector.InspectionCompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class firebase extends AppCompatActivity {
    private boolean Inserted = false;
    private boolean deleted = false;
    public class fetch {
        public String password, reg_no, section, degree,year_Of_Joining,current_semister;
        fetch(String password, String reg_no, String section, String degree,String year_Of_Joining,String current_semister) {
            this.password = password;
            this.reg_no = reg_no;
            this.degree = degree;
            this.section = section;
            this.year_Of_Joining = year_Of_Joining;
            this.current_semister = current_semister;
        }



    }
    Boolean insert(String id,fetch fetch){
        FirebaseDatabase.getInstance().getReference().child("students").child(id).setValue(fetch).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Inserted = true;
            }
        });
        return Inserted;
    }
    Boolean delete(String id){
        FirebaseDatabase.getInstance().getReference().child("students").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                deleted = true;
            }
        });
        return deleted;
    };
    ArrayList<String> read(String id){
        ArrayList<String> details = new ArrayList<String>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("students").child(id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        signin.arrayList.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            signin.arrayList.add(snapshot1.getValue().toString());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });


        return details;
    }
}