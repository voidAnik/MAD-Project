package com.example.moodTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class UserProfileActivity extends AppCompatActivity {
    String name, email, contact;
    Uri photoUrl;
    TextView full_name;
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
        photoUrl = getIntent().getData();

        // Binding
        full_name = findViewById(R.id.fullName_field);
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
        profile_image.setImageURI(photoUrl);
        Toast.makeText(this, ""+photoUrl, Toast.LENGTH_LONG).show();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(UserProfileActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
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
}