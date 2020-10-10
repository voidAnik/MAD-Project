package com.example.moodTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FunctionalActivity extends AppCompatActivity {
    Button btn_profile, btn_queries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional);

        btn_profile = findViewById(R.id.btn_profile);
        btn_queries = findViewById(R.id.btn_queries);

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FunctionalActivity.this, UserProfileActivity.class));
            }
        });
        btn_queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FunctionalActivity.this, personality_check_Activity.class));
            }
        });
    }
}