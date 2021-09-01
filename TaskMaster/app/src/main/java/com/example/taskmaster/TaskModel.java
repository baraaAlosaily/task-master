package com.example.taskmaster;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskModel {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="task_name")
    public String title;
    @ColumnInfo(name="task_body")
    public String body;
    @ColumnInfo(name="task_state")
    public String state;

    public TaskModel(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getState() {
        return state;
    }
}
