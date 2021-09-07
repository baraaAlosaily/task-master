package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

//    List<com.amplifyframework.datastore.generated.model.Todo> taskListAmp = new ArrayList<>();
//    private TaskAdapter taskAdapter;
//    private  List<TaskModel> taskList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addTask=findViewById(R.id.button);

//        taskList.add(new TaskModel("Code Challenge-26", "insertion-sort", "comleted"));
//        taskList.add(new TaskModel("Code Challenge-27", "Merge-sort", "assigned"));
//        taskList.add(new TaskModel("Code Challenge-28", "Quick-sort", "in progress"));
//        taskList.add(new TaskModel("Code Challenge-29", "Non", "new"));
//
//        TaskAdapter taskAdapter = new TaskAdapter(taskList, this);










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
                Button b = (Button)view;
                String buttonText = b.getText().toString();
                Intent goToallTask=new Intent(MainActivity.this,TaskDetailPage.class);
                goToallTask.putExtra("contactus",buttonText);
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


        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        RecyclerView allDishesRecycleView = findViewById(R.id.datarcyclerview);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                allDishesRecycleView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
//
//
//        AppDataBase appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "task").allowMainThreadQueries().build();
//        TaskDao taskDao = appDataBase.taskDao();
        List<Todo> taskList = new ArrayList<Todo>();

        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Todo.class),
                response -> {
                    for (Todo todo : response.getData()) {
//                        Log.i("MyAmplifyApp", todo.getName());
                        taskList.add(todo);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        allDishesRecycleView.setAdapter(new TaskAdapter(taskList,this));
        allDishesRecycleView.setLayoutManager(new LinearLayoutManager(this));

    }
}