package com.vanlam.todoist.Model;

public class ToDoModel {
    private int id, status;
    private String taskContent;

    public ToDoModel() {
    }

    public ToDoModel(int id, int status, String taskContent) {
        this.id = id;
        this.status = status;
        this.taskContent = taskContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}
