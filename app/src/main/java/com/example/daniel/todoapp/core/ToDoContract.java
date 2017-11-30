package com.example.daniel.todoapp.core;


import android.provider.BaseColumns;

public class ToDoContract {

    private ToDoContract(){

    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoDatabase";

    public static abstract class ItemTable implements BaseColumns{

        public static final String TABLE_NAME = "item";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_PRIORITY= "priority";

        public static final String COLUMN_NAME_COMPLETED = "completed";
        public static final String COLUMN_NAME_DELETED= "deleted";

        public static final String SQL_CREATE_ITEMS_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_TITLE + " TEXT," +
                        COLUMN_NAME_CONTENT + " TEXT," +
                        COLUMN_NAME_DATE + " INTEGER," +
                        COLUMN_NAME_PRIORITY + " INTEGER," +
                        COLUMN_NAME_COMPLETED + " INTEGER," +
                        COLUMN_NAME_DELETED + " INTEGER)";

        public static final String SQL_DELETE_ITEMS_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static abstract class PriorityTable implements BaseColumns {

        public static final String TABLE_NAME = "priority";
        public static final String COLUMN_NAME_NAME= "name";
        public static final String COLUMN_NAME_COLOR = "color";

        public static final String SQL_CREATE_PRIORITY_TABLE =
                "CREATE TABLE "+ TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_NAME + " TEXT," +
                        COLUMN_NAME_COLOR + " TEXT)";

        public static final String[] SQL_CREATE_INITIAL_PRIORITY_DATA = new String[]{
                "INSERT INTO " + TABLE_NAME + " (" +
                        COLUMN_NAME_NAME + ", " +
                        COLUMN_NAME_COLOR + " ) VALUES ( 'high', 'red' );",
                "INSERT INTO " + TABLE_NAME + " (" +
                        COLUMN_NAME_NAME + ", " +
                        COLUMN_NAME_COLOR + " ) VALUES ( 'medium', 'amber' );",
                "INSERT INTO " + TABLE_NAME + " (" +
                        COLUMN_NAME_NAME + ", " +
                        COLUMN_NAME_COLOR + " ) VALUES ( 'low', 'yellow' );",
        };

        public static final String SQL_DELETE_PRIORITY_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
