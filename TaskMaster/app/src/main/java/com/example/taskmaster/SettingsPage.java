package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsPage extends AppCompatActivity {
    RadioGroup submitRadio;
    RadioButton radioButton;
    TextView teamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        Button saveUsername=findViewById(R.id.button4);
        saveUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        saveUsername.setOnClickListener((view) -> {
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
            SharedPreferences.Editor sharedPreferencesEditor=sharedPreferences.edit();

            EditText usernamefield=findViewById(R.id.nameInput);
            String username=usernamefield.getText().toString();

            sharedPreferencesEditor.putString("username",username);
            sharedPreferencesEditor.apply();

        });

        submitRadio=findViewById(R.id.teamSubmit);
        teamText=findViewById(R.id.textView10);
        Button applyTeam=findViewById(R.id.teamApply);
        applyTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                RadioButton One = findViewById(R.id.radioButton3);
                RadioButton Two = findViewById(R.id.radioButton4);
                RadioButton Three = findViewById(R.id.radioButton5);

                if (One.isChecked()){
                    sharedPreferencesEditor.putString("team", One.getText().toString());
                }else if(Two.isChecked()){
                    sharedPreferencesEditor.putString("team", Two.getText().toString());
                }else if(Three.isChecked()){
                    sharedPreferencesEditor.putString("team", Three.getText().toString());
                }

                sharedPreferencesEditor.apply();

                teamText.setText("You Choice is: " + radioButton.getText());

                Intent toHome = new Intent(SettingsPage.this,MainActivity.class);
                startActivity(toHome);
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        int radioId=submitRadio.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this,"This is my Team"+radioButton.getText(),Toast.LENGTH_LONG).show();
    }

}