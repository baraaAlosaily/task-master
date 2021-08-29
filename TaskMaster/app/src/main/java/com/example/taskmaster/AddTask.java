package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button createTask=findViewById(R.id.button3);
        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Submitted!",Toast.LENGTH_LONG).show();
                EditText taskTitle=findViewById(R.id.editText);
                EditText taskDesk=findViewById(R.id.editText2);
                String taskTitleValue=taskTitle.getText().toString();
                String taskDescValue=taskDesk.getText().toString();

                Intent goToAllTasls=new Intent(AddTask.this,AllTasks.class);
                goToAllTasls.putExtra("tasktitle",taskTitleValue);
                goToAllTasls.putExtra("taskdesc",taskDescValue);
            }
        });
    }
}