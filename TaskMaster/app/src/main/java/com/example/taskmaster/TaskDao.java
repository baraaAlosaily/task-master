package com.example.taskmaster;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao{

    @Query("SELECT * FROM taskmodel WHERE task_name Like :name")
    TaskModel findByName(String name);

    @Query("SELECT * FROM taskmodel")
    List<TaskModel> findAll();
    @Insert
    void insertOne(TaskModel taskModel);

    @Delete
    void deleteItem(TaskModel taskModel);
}

