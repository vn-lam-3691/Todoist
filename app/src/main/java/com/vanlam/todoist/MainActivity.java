package com.vanlam.todoist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vanlam.todoist.Adapter.ToDoAdapter;
import com.vanlam.todoist.Model.ToDoModel;
import com.vanlam.todoist.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {
    private RecyclerView taskList_recyclerView;
    private ToDoAdapter toDoAdapter;
    private List<ToDoModel> taskList;
    private FloatingActionButton fabAddNewTask;
    private TextView subTitle;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subTitle = (TextView) findViewById(R.id.sub_title_app);
        fabAddNewTask = (FloatingActionButton) findViewById(R.id.fab_add);
        taskList = new ArrayList<ToDoModel>();
        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList_recyclerView = (RecyclerView) findViewById(R.id.taskList_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        toDoAdapter = new ToDoAdapter(db, this);
        taskList_recyclerView.setLayoutManager(layoutManager);
        taskList_recyclerView.setAdapter(toDoAdapter);

        toDoAdapter.setTaskList(taskList);

        int taskCount = taskList.size();
        subTitle.setText(getResources().getQuantityString(R.plurals.sub_title_app, taskCount, taskCount));

        taskList = db.getAllTaskList();
        Collections.reverse(taskList);
        toDoAdapter.setTaskList(taskList);

        fabAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTaskList();
        Collections.reverse(taskList);
        toDoAdapter.setTaskList(taskList);
        toDoAdapter.notifyDataSetChanged();
    }
}