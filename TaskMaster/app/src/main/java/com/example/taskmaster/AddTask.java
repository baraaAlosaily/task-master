package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    private TaskDao taskDao;
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        appDataBase= Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"task").allowMainThreadQueries().build();
        taskDao=appDataBase.taskDao();

        Button createTask=findViewById(R.id.button3);
        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Submitted!",Toast.LENGTH_LONG).show();
                EditText taskTitle=findViewById(R.id.editText);
                EditText taskDesk=findViewById(R.id.editText2);
                EditText taskstate=findViewById(R.id.stateinput);
                String taskTitleValue=taskTitle.getText().toString();
                String taskDescValue=taskDesk.getText().toString();
                String taskStateValue=taskstate.getText().toString();

                TaskModel newTask=new TaskModel(taskTitleValue,taskDescValue,taskStateValue);
                
                taskDao.insertOne(newTask);

                Intent goToAllTasls=new Intent(AddTask.this,MainActivity.class);
                startActivity(goToAllTasls);
//
            }
        });
    }
}