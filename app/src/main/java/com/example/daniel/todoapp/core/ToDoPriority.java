package com.example.daniel.todoapp.core;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.daniel.todoapp.R;

public class ToDoPriority implements Parcelable{

     Context context;

    private int id;
    private String name;
    private String color;

    protected ToDoPriority(Parcel in) {
        id = in.readInt();
        name = in.readString();
        color = in.readString();
    }

    public static final Creator<ToDoPriority> CREATOR = new Creator<ToDoPriority>() {
        @Override
        public ToDoPriority createFromParcel(Parcel in) {
            return new ToDoPriority(in);
        }

        @Override
        public ToDoPriority[] newArray(int size) {
            return new ToDoPriority[size];
        }
    };

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.name = content;
    }

   public int[] getUIColor(){
       int[] hexColors = new int[2];
       switch (color){
           case "red":
               hexColors[0] = ContextCompat.getColor(context, R.color.priority_red);
               hexColors[1] = ContextCompat.getColor(context, R.color.priority_red_dark);
               break;
           case "amber":
               hexColors[0] = ContextCompat.getColor(context, R.color.priority_amber);
               hexColors[1] = ContextCompat.getColor(context, R.color.priority_amber_dark);
               break;
           case "yellow":
               hexColors[0] = ContextCompat.getColor(context, R.color.priority_yellow);
               hexColors[1] = ContextCompat.getColor(context, R.color.priority_yellow_dark);
               break;


       }
       return hexColors;
   }

    public void setColor(String color) {
        this.color = color;
    }

    public ToDoPriority(int id, String name, String color, Context context) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public static ToDoPriority[] readAll(Context context){
        ToDoPriority[] items = null;
        ToDoHelper toDoHelper = new ToDoHelper(context);
        SQLiteDatabase db = toDoHelper.getReadableDatabase();

        ToDoPriority toDoPriority;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT _id, name, color FROM priority", null);
            items = new ToDoPriority[cursor.getCount()];
            int counter = 0;

            while (cursor.moveToNext()){
                int _id = cursor.getInt(cursor.getColumnIndexOrThrow(ToDoContract.PriorityTable._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ToDoContract.PriorityTable.COLUMN_NAME_NAME));
                String color = cursor.getString(cursor.getColumnIndexOrThrow(ToDoContract.PriorityTable.COLUMN_NAME_COLOR));

                toDoPriority = new ToDoPriority(_id, name, color, context);
                items[counter]= toDoPriority;
                counter++;
            }

        }catch (Exception e){
            Log.e("Exception - ReadAll", e.toString());
        }finally {
            db.close();
            cursor.close();
        }



        return items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(color);
    }
}
