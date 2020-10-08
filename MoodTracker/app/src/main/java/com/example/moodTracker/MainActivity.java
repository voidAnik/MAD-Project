package com.example.moodTracker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity  {

    RecyclerView rcview ;
    rvadapter adapter;
    private List<model> quesList;
    DatabaseReference dbreff,query1;
    TextView tv_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcview = (RecyclerView)findViewById(R.id.rv);
        tv_signin = findViewById(R.id.tv_SignIn);
        rcview.setLayoutManager(new LinearLayoutManager(this));
        quesList = new ArrayList<>();
        adapter = new rvadapter(this,quesList);
        rcview.setAdapter(adapter);

        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.moodTracker.LoginAuthActivity.class);
                startActivity(intent);
            }
        });

        query1 = FirebaseDatabase.getInstance().getReference("QuesMt");

       // Query q1 = FirebaseDatabase.getInstance().getReference("Ques").orderByChild("id").equalTo("i1");
        query1.addListenerForSingleValueEvent(valueEventListener);

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            quesList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    model model = snapshot.getValue(model.class);
                    quesList.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}