package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Task Details");
        setContentView(R.layout.activity_task_detail_page);

//        Intent intent=getIntent();
//        String aboutus= intent.getExtras().getString("aboutus");
//        TextView aboutusView=findViewById(R.id.textView4);
//        aboutusView.setText(aboutus);
//
//        Intent intent2=getIntent();
//        String addpost= intent2.getExtras().getString("addpost");
//        TextView addpostView=findViewById(R.id.textView8);
//        addpostView.setText(addpost);
//
//        Intent intent3=getIntent();
//        String contactus= intent3.getExtras().getString("contactus");
//        TextView contactusView=findViewById(R.id.textView9);
//        contactusView.setText(contactus);

        Intent i=getIntent();
        String title=i.getStringExtra("title");
        String body=i.getStringExtra("body");
        String state=i.getStringExtra("state");
        ((TextView)findViewById(R.id.textView9)).setText(title);
        ((TextView)findViewById(R.id.textView5)).setText(body);
        ((TextView)findViewById(R.id.textView7)).setText(state);
    }
}