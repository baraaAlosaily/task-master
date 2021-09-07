package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

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

//                TaskModel newTask=new TaskModel(taskTitleValue,taskDescValue,taskStateValue);

//                try {
//                    // Add these lines to add the AWSApiPlugin plugins
//                    Amplify.addPlugin(new AWSApiPlugin());
//                    Amplify.configure(getApplicationContext());
//
//                    Log.i("MyAmplifyApp", "Initialized Amplify");

                    Todo todo = Todo.builder()
                            .title(taskTitleValue)
                            .body(taskDescValue).state(taskStateValue)
                            .build();

                    Amplify.API.mutate(
                            ModelMutation.create(todo),
                            response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                            error -> Log.e("MyAmplifyApp", "Create failed", error)
                    );
//                } catch (AmplifyException error) {
//                    Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//                }
                
//                taskDao.insertOne(newTask);

                Intent goToAllTasls=new Intent(AddTask.this,MainActivity.class);
                startActivity(goToAllTasls);
//
            }
        });
    }
}