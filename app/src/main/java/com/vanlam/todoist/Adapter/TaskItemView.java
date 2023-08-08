package com.vanlam.todoist.Adapter;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanlam.todoist.R;

public class TaskItemView extends RecyclerView.ViewHolder {
    private CheckBox checkTask;

    public TaskItemView(@NonNull View itemView) {
        super(itemView);
        this.checkTask = (CheckBox) itemView.findViewById(R.id.task_checkbox);
    }

    public CheckBox getCheckTask() {
        return checkTask;
    }
}
