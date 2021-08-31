package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVeiwHolder> {

    List<TaskModel> allTask=new ArrayList<>();
    Context context;

    public TaskAdapter(List<TaskModel> allTask,Context context) {
        this.allTask = allTask;
        this.context=context;
    }

//    public TaskAdapter(List<TaskModel> allTask) {
//        this.allTask = allTask;
//    }

    public static class TaskVeiwHolder extends RecyclerView.ViewHolder {
        View itemView;
        ConstraintLayout mainLayout;
        public TaskModel taskModel;

        public TaskVeiwHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }

    @NonNull
    @Override
    public TaskVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskVeiwHolder taskVeiwHolder=new TaskVeiwHolder(view);
        return taskVeiwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVeiwHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.taskModel=allTask.get(position);
        TextView tasktitle=holder.itemView.findViewById(R.id.titleview);
        tasktitle.setText(holder.taskModel.title);
        TextView taskbody=holder.itemView.findViewById(R.id.bodyView);
        taskbody.setText(holder.taskModel.body);
        TextView taskstate=holder.itemView.findViewById(R.id.stateView);
        taskstate.setText(holder.taskModel.state);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaskDetailPage.class);
                intent.putExtra("title",allTask.get(position).getBody() );
                intent.putExtra("body", allTask.get(position).getBody());
                intent.putExtra("state", allTask.get(position).getState());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }


}