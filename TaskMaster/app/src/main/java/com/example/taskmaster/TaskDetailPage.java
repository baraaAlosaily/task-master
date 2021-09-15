package com.example.taskmaster;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;

import java.io.File;

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

//
        ImageView myimg=findViewById(R.id.imageView2);

        if (i.getExtras().getString("img") != null) {
            Amplify.Storage.downloadFile(
                    i.getExtras().getString("img"),
                    new File(getApplicationContext().getFilesDir() + "/" + i.getExtras().getString("img") + ".jpg"),
                    result -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(result.getFile().getPath());
                        myimg.setImageBitmap(bitmap);
                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    },
                    error -> Log.e("MyAmplifyApp", "Download Failure", error)
            );
        }
    }
}