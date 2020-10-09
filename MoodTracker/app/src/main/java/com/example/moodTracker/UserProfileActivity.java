package com.example.moodTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {
    String name, email, contact;
    Uri photoUrl;
    TextView full_name;
    EditText et_name, et_email, et_contact;
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        contact = getIntent().getStringExtra("contact");
        //photoUrl = getIntent().getData();

        // Binding
        full_name = findViewById(R.id.fullName_field);
        et_name = findViewById(R.id.full_name_profile);
        et_email = findViewById(R.id.email_profile);
        et_contact = findViewById(R.id.contact_number_profile);
        profile_image = findViewById(R.id.profile_image);

        // updating values

        full_name.setText(name);
        et_name.setText(name);
        et_email.setText(email);
        et_contact.setText(contact);
        //profile_image.setImageURI(photoUrl);
    }
}