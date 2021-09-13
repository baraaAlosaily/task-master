package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class verification extends AppCompatActivity {
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        Button verfiy=findViewById(R.id.button10);
        EditText code=findViewById(R.id.sCode);

        Intent intent=getIntent();
         username=intent.getExtras().getString("username","");
//        username=intent.getExtras().getString("username");
        password=intent.getExtras().getString("password","");

        verfiy.setOnClickListener(view -> verification(username,code.getText().toString()));
    }
    void verification (String username,String verficationCode){
        Amplify.Auth.confirmSignUp(
                username,
                verficationCode,
                success -> {
                    Log.i("verification", "verification successful: " + success.toString());
                    Intent goToSignIn = new Intent(verification.this, signIn.class);
                    goToSignIn.putExtra("username", username);

                    startActivity(goToSignIn);
                },
                error -> {
                    Log.e("verification", "verification failed: " + error.toString());
                });
    }
}