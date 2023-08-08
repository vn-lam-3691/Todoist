package com.vanlam.todoist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vanlam.todoist.Adapter.ToDoAdapter;
import com.vanlam.todoist.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView taskList_recyclerView;
    private ToDoAdapter toDoAdapter;
    private List<ToDoModel> taskList;
    private TextView subTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subTitle = (TextView) findViewById(R.id.sub_title_app);
        taskList = new ArrayList<ToDoModel>();

        ToDoModel model = new ToDoModel();
        model.setId(0);
        model.setStatus(0);
        model.setTaskContent("This is an task");

        taskList.add(model);
        taskList.add(model);
        taskList.add(model);
        taskList.add(model);

        taskList_recyclerView = (RecyclerView) findViewById(R.id.taskList_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        toDoAdapter = new ToDoAdapter(this);
        taskList_recyclerView.setLayoutManager(layoutManager);
        taskList_recyclerView.setAdapter(toDoAdapter);

        toDoAdapter.setTaskList(taskList);

        int taskCount = taskList.size();
        subTitle.setText(getResources().getQuantityString(R.plurals.sub_title_app, taskCount, taskCount));
    }
}