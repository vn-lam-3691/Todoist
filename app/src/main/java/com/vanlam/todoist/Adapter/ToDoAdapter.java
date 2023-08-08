package com.vanlam.todoist.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanlam.todoist.MainActivity;
import com.vanlam.todoist.Model.ToDoModel;
import com.vanlam.todoist.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<TaskItemView> {
    private List<ToDoModel> taskList;
    private MainActivity mainActivity;

    public ToDoAdapter(MainActivity mainActivity) {
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
        ToDoModel item = taskList.get(position);
        int id = item.getId();
        int status = item.getStatus();
        String task = item.getTaskContent();

        holder.getCheckTask().setText(task);
        holder.getCheckTask().setChecked(toBoolean(status));
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
