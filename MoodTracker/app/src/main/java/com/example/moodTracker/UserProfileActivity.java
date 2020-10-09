package com.example.moodTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class UserProfileActivity extends AppCompatActivity {
    String name, email, contact, provider;
    Uri photoUrl;
    TextView full_name, loggedInWith;
    com.google.android.material.textfield.TextInputLayout et_name, et_email, et_contact;
    ImageView profile_image;
    Button update, signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        contact = getIntent().getStringExtra("contact");
        provider = getIntent().getStringExtra("provider");
        photoUrl = getIntent().getData();
        //Toast.makeText(this, ""+provider, Toast.LENGTH_SHORT).show();

        // Binding
        full_name = findViewById(R.id.fullName_field);
        loggedInWith = findViewById(R.id.loggedInWith);
        et_name = findViewById(R.id.full_name_profile);
        et_email = findViewById(R.id.email_profile);
        et_contact = findViewById(R.id.contact_number_profile);
        profile_image = findViewById(R.id.profile_image);
        update = findViewById(R.id.btn_update);
        signOut = findViewById(R.id.btn_sign_out);

        // updating values

        full_name.setText(name);
        et_name.getEditText().setText(name);
        et_email.getEditText().setText(email);
        et_contact.getEditText().setText(contact);
        //profile_image.setImageURI(photoUrl);
        Glide.with(getBaseContext())
                .load(photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profile_image);
        Toast.makeText(this, ""+photoUrl, Toast.LENGTH_LONG).show();

        // Viewing with which provider user signed in
        signInProviders();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(UserProfileActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseAuth.getInstance().signOut();
                                Toast.makeText(UserProfileActivity.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UserProfileActivity.this, LoginAuthActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void signInProviders() {
        if(provider.contains("google")){
            loggedInWith.setText("By Google");
        }
        else if(provider.contains("facebook")){
            loggedInWith.setText("By Facebook");
        }
        else if(provider.contains("phone")){
            loggedInWith.setText("By Phone Number");
            full_name.setText("Unknown");
            profile_image.setImageResource(R.drawable.profile_sample);
        }
        else if(provider.contains("twitter")){
            loggedInWith.setText("By Twitter");
        }
        else if(provider.contains("password")){
            loggedInWith.setText("By E-mail");
            if(photoUrl == null){
                profile_image.setImageResource(R.drawable.profile_sample);
            }
        }
    }
}