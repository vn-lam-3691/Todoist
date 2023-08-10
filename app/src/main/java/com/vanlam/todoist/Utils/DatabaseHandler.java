package com.vanlam.todoist.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.vanlam.todoist.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TODO_LIST_DATABASE";
    public static final String TABLE_NAME = "TODO";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_STATUS = "STATUS";
    public static final String COLUMN_TASK = "TASK";
    private SQLiteDatabase db;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TB_TODO = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TASK + " TEXT, " + COLUMN_STATUS + " INTEGER)";
        sqLiteDatabase.execSQL(SQL_CREATE_TB_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL_DROP_TB_TODO = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_DROP_TB_TODO);
        onCreate(sqLiteDatabase);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, item.getTaskContent());
        values.put(COLUMN_STATUS, 0);

        db.insert(TABLE_NAME, null, values);
    }

    public void updateStatus(int id, int status) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_STATUS, status);

        db.update(TABLE_NAME, values, "ID = ?", new String[] {String.valueOf(id)});
    }

    public void updateTask(int id, String content) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_TASK, content);

        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id) {
        db.delete(TABLE_NAME, COLUMN_ID +" = ?", new String[] {String.valueOf(id)});
    }

    public List<ToDoModel> getAllTaskList() {
        List<ToDoModel> taskList = new ArrayList<ToDoModel>();
        Cursor cursor = null;

        db.beginTransaction();
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ToDoModel item = new ToDoModel();
                        item.setId(cursor.getInt(0));
                        item.setTaskContent(cursor.getString(1));
                        item.setStatus(cursor.getInt(2));

                        taskList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cursor.close();
        }

        return taskList;
    }
}
