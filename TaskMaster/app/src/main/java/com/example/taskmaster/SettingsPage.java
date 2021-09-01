package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        Button saveUsername=findViewById(R.id.button4);
        saveUsername.setOnClickListener((view) -> {
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
            SharedPreferences.Editor sharedPreferencesEditor=sharedPreferences.edit();

            EditText usernamefield=findViewById(R.id.usernameInput);
            String username=usernamefield.getText().toString();

            sharedPreferencesEditor.putString("username",username);
            sharedPreferencesEditor.apply();

        });



    }
}