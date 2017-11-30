package com.example.daniel.todoapp.core;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.daniel.todoapp.R;

public class ToDoItemAdapter extends ArrayAdapter <ToDoItem>{
    private Context context;
    private int layoutResourceId;
    private ToDoItem data[] = null;



    public ToDoItemAdapter( Context context,  int layoutResourceId,  ToDoItem[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
       ToDoItemHolder  holder;

        if(row == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            row = layoutInflater.inflate(layoutResourceId, parent, false);

            holder = new ToDoItemHolder();
            holder.colorView = row.findViewById(R.id.rectangle_at_the_top);
            holder.titleTextView = (TextView) row.findViewById(R.id.titleTextView);
            holder.contentTextView = (TextView) row.findViewById(R.id.contentTextView);
            holder.dayTextView = (TextView) row.findViewById(R.id.dayTextView);
            holder.monthTextView = (TextView) row.findViewById(R.id.monthTextView);
            holder.yearTextView = (TextView) row.findViewById(R.id.yearTextView);
            row.setTag(holder);
        }else{
            holder = (ToDoItemHolder)row.getTag();
        }

        ToDoItem toDoItem = data[position];

        if(!toDoItem.getCompleted()){
            holder.colorView.setBackgroundColor(toDoItem.getToDoPriority().getUIColor()[0]);
        }else{
            holder.colorView.setBackgroundColor(ContextCompat.getColor(context, R.color.completed));
        }

        holder.titleTextView.setText(toDoItem.getTitle());
        holder.contentTextView.setText(toDoItem.getContent());

        holder.dayTextView.setText(toDoItem.getDay());
        holder.monthTextView.setText(toDoItem.getMonth());
        holder.yearTextView.setText(toDoItem.getYear());

        return row;

    }

    private static class ToDoItemHolder{
        View colorView;

        TextView titleTextView;
        TextView contentTextView;

        TextView dayTextView;
        TextView monthTextView;
        TextView yearTextView;
    }
}
