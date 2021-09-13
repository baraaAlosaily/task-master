package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class signIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        configure();
        Button signIn=findViewById(R.id.signIn);
        EditText username=findViewById(R.id.username2);
        EditText password=findViewById(R.id.password2);
        Button signUp=findViewById(R.id.signUp2);

        signIn.setOnClickListener(view -> signInfunc(username.getText().toString(),password.getText().toString()));

        signUp.setOnClickListener(view ->
        { Intent intent=new Intent(com.example.taskmaster.signIn.this, com.example.taskmaster.signUp.class);
        startActivity(intent);
        });

    }
    void signInfunc(String userName, String password){
        Amplify.Auth.signIn(
                userName,
                password,
                success ->{
                    Log.i("signIn", "signIn successful: " + success.toString());
                    Intent goToMain = new Intent(signIn.this, MainActivity.class);
                    startActivity(goToMain);
                },
                error ->{
                    Log.e("signIn", "signIn failed: " + error.toString());
                }
        );
    }

    void configure(){
                try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
    }
}