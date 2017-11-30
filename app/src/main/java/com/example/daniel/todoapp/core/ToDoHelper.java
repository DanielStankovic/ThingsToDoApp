package com.example.daniel.todoapp.core;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoHelper extends SQLiteOpenHelper {
    public ToDoHelper(Context context) {
        super(context, ToDoContract.DATABASE_NAME, null, ToDoContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ToDoContract.ItemTable.SQL_CREATE_ITEMS_TABLE);
        db.execSQL(ToDoContract.PriorityTable.SQL_CREATE_PRIORITY_TABLE);

        for (int i = 0; i < ToDoContract.PriorityTable.SQL_CREATE_INITIAL_PRIORITY_DATA.length; i++) {
            db.execSQL(ToDoContract.PriorityTable.SQL_CREATE_INITIAL_PRIORITY_DATA[i]);
        }
            }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ToDoContract.ItemTable.SQL_DELETE_ITEMS_TABLE);
        db.execSQL(ToDoContract.PriorityTable.SQL_DELETE_PRIORITY_TABLE);
        onCreate(db);
    }
}
