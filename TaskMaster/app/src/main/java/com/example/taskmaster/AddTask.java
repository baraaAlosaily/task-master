package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {

    private TaskDao taskDao;
    private AppDataBase appDataBase;

    RadioGroup submitRadio;
    RadioButton radioButton;
    RadioButton One;
    RadioButton Two;
    RadioButton Three;
    TextView teamText;
    List<Team>teams=new ArrayList<>();
    Team team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {
                    for (Team todo : response.getData()) {
//                        Log.i("MyAmplifyApp", todo.getName());
                        teams.add(todo);
                        System.out.println(teams);
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );



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
                    Todo todo = Todo.builder()
                            .title(taskTitleValue).team(team)
                            .body(taskDescValue).state(taskStateValue)
                            .build();

                    Amplify.API.mutate(
                            ModelMutation.create(todo),
                            response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                            error -> Log.e("MyAmplifyApp", "Create failed", error)
                    );



                Intent goToAllTasls=new Intent(AddTask.this,MainActivity.class);
                startActivity(goToAllTasls);
//
            }
        });
        submitRadio=findViewById(R.id.radioGroup);
        teamText=findViewById(R.id.textView6);
        Button applyTeam=findViewById(R.id.button9);
        applyTeam.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
//                int radioGroup = submitRadio.getCheckedRadioButtonId();
                One = findViewById(R.id.radioButton);
                Two = findViewById(R.id.radioButton2);
                Three = findViewById(R.id.radioButton6);
                teamText.setText("You Choice is: " + radioButton.getText());

                String name="";

                if(One.isChecked()){
                    name="One";
                }else if (Two.isChecked()){
                    name="Two";
                }else if (Three.isChecked()){
                    name="Three";
                }

                team=null;
                for(int i=0;i<teams.size();i++){
                    if(teams.get(i).getName().equals(name)){
                        team=teams.get(i);
                    }
                }
                teamText.setText("You Choice is: " + radioButton.getText());


//                team = Team.builder()
//                        .name((String) radioButton.getText())
//                        .build();
//
//                Amplify.API.mutate(
//                        ModelMutation.create(team),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error)
//                );
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        int radioId=submitRadio.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this,"This is my Team"+radioButton.getText(),Toast.LENGTH_LONG).show();
    }
}