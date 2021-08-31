package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addTask=findViewById(R.id.button);
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

        List<TaskModel> myallTask=new ArrayList<>();
        myallTask.add(new TaskModel("Code Challenge-26","insertion-sort","comleted"));
        myallTask.add(new TaskModel("Code Challenge-27","Merge-sort","assigned"));
        myallTask.add(new TaskModel("Code Challenge-28","Quick-sort","in progress"));
        myallTask.add(new TaskModel("Code Challenge-29","Non","new"));

        RecyclerView allTaskRecycleView=findViewById(R.id.datarcyclerview);
        allTaskRecycleView.setLayoutManager(new LinearLayoutManager(this));
        allTaskRecycleView.setAdapter(new TaskAdapter(myallTask,this));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String welcomeMessege="Welcome ";
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username=sharedPreferences.getString("username","username");
        TextView usernameveiw=findViewById(R.id.textView2);
        usernameveiw.setText(welcomeMessege+username);
    }
}