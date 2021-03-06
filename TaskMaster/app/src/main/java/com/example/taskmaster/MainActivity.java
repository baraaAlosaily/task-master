package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", String.valueOf(userStateDetails.getUserState()));
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

//    List<com.amplifyframework.datastore.generated.model.Todo> taskListAmp = new ArrayList<>();
//    private TaskAdapter taskAdapter;
//    private  List<TaskModel> taskList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addTask=findViewById(R.id.button);

        try {
//                    Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            getPinpointManager(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

//        taskList.add(new TaskModel("Code Challenge-26", "insertion-sort", "comleted"));
//        taskList.add(new TaskModel("Code Challenge-27", "Merge-sort", "assigned"));
//        taskList.add(new TaskModel("Code Challenge-28", "Quick-sort", "in progress"));
//        taskList.add(new TaskModel("Code Challenge-29", "Non", "new"));
//
//        TaskAdapter taskAdapter = new TaskAdapter(taskList, this);
        Button logout=findViewById(R.id.logOut);
        logout.setOnClickListener(view -> logout());


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTask=new Intent(MainActivity.this,AddTask.class);
                startActivity(goToAddTask);
            }
        });


        Button AllTask=findViewById(R.id.button2);
        AllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToallTask=new Intent(MainActivity.this,AllTasks.class);
                startActivity(goToallTask);
            }
        });

        Button AboutUs=findViewById(R.id.button5);
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String buttonText = b.getText().toString();
                Intent goToallTask=new Intent(MainActivity.this,TaskDetailPage.class);
                goToallTask.putExtra("aboutus",buttonText);
                startActivity(goToallTask);
            }
        });
        Button AddPost=findViewById(R.id.button6);
        AddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String buttonText = b.getText().toString();
                Intent goToallTask=new Intent(MainActivity.this,TaskDetailPage.class);
                goToallTask.putExtra("addpost",buttonText);
                startActivity(goToallTask);
            }
        });
        Button ContactUs=findViewById(R.id.button7);
        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Button b = (Button)view;
//                String buttonText = b.getText().toString();
                Intent goToallTask=new Intent(MainActivity.this,signIn.class);
//                goToallTask.putExtra("contactus",buttonText);
                startActivity(goToallTask);
            }
        });

        Button setting=findViewById(R.id.button8);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToallTask=new Intent(MainActivity.this,SettingsPage.class);
                startActivity(goToallTask);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String welcomeMessege = "Welcome ";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = sharedPreferences.getString("username", "username");
        TextView usernameveiw = findViewById(R.id.textView2);
        usernameveiw.setText(welcomeMessege + username);

        TextView username5=findViewById(R.id.textView11);
        username5.setText(getCurrentValue());


//
//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );

        SharedPreferences sharedPreferences1=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String team=sharedPreferences1.getString("team","team");

        List<Team>teams=new ArrayList<>();
        List<TaskModel> taskModels=new ArrayList<>();

        RecyclerView allDishesRecycleView = findViewById(R.id.datarcyclerview);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                allDishesRecycleView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
//        AppDataBase appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "task").allowMainThreadQueries().build();
//        TaskDao taskDao = appDataBase.taskDao();
        List<Todo> taskList = new ArrayList<Todo>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response2 -> {
                    for (Team tema : response2.getData()) {
//                        Log.i("MyAmplifyApp", tema.getName());
//                        Log.i("MyAmplifyApp", tema.getId());
                        teams.add(tema);
                    }
                    for (int i = 0; i < teams.size(); i++) {
                        if (teams.get(i).getName().equals(team)){
                            taskList.addAll(teams.get(i).getTasks());
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        allDishesRecycleView.setAdapter(new TaskAdapter(taskList,this));
        allDishesRecycleView.setLayoutManager(new LinearLayoutManager(this));
        String team1 = sharedPreferences.getString("team", "team");
        TextView teamName = findViewById(R.id.teamNameHome);
        teamName.setText(team1);
    }

    String getCurrentValue(){
        AuthUser authUser=Amplify.Auth.getCurrentUser();
        Log.e("getCurrentUser", authUser.toString());
        Log.e("getCurrentUser", authUser.getUserId());
        Log.e("getCurrentUser", authUser.getUsername());
        return authUser.getUsername();
    }

    public void logout(){
        Amplify.Auth.signOut(
                () ->{
                    Log.i("logout", "successfully logout");
                    Intent goToLogin = new Intent(getBaseContext(), signIn.class);
                    startActivity(goToLogin);
                } ,
                error -> Log.e("logout", error.toString())
        );
    }
}