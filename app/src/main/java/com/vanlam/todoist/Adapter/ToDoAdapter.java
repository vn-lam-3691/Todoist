package com.vanlam.todoist.Adapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanlam.todoist.AddNewTask;
import com.vanlam.todoist.MainActivity;
import com.vanlam.todoist.Model.ToDoModel;
import com.vanlam.todoist.R;
import com.vanlam.todoist.Utils.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<TaskItemView> {
    private List<ToDoModel> taskList;
    private MainActivity mainActivity;
    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, MainActivity mainActivity) {
        this.db = db;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public TaskItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_view, parent, false);
        TaskItemView itemView = new TaskItemView(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemView holder, int position) {
        db.openDatabase();
        ToDoModel item = taskList.get(position);
        int id = item.getId();
        int status = item.getStatus();
        String task = item.getTaskContent();

        holder.getCheckTask().setText(task);
        holder.getCheckTask().setChecked(toBoolean(status));

        holder.getCheckTask().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    db.updateStatus(item.getId(), 1);
                }
                else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public void editItem(int position) {
        ToDoModel item = taskList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTaskContent());

        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(mainActivity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public boolean toBoolean(int n) {
        return n != 0;
    }

    public void setTaskList(List<ToDoModel> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
