package com.example.daniel.todoapp.core;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.daniel.todoapp.R;

public class ToDoPriorityAdapter extends ArrayAdapter <ToDoPriority> {

    private Context context;
    private int layoutResourceId;
    private ToDoPriority data[] = null;

    public ToDoPriorityAdapter(Context context, int layoutResourceId, int layoutTextView, ToDoPriority[] data) {
        super(context, layoutResourceId, layoutTextView, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ToDoPriorityAdapter.ToDoPriorityHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ToDoPriorityAdapter.ToDoPriorityHolder();
            holder.colorView = row.findViewById(R.id.rectangle_at_the_top);
            holder.nameTextView = (TextView) row.findViewById(R.id.nameTextView);

            row.setTag(holder);
        } else {
            holder = (ToDoPriorityAdapter.ToDoPriorityHolder) row.getTag();
        }

        ToDoPriority toDoPriority = data[position];
        holder.colorView.setBackgroundColor(toDoPriority.getUIColor()[0]);
        holder.nameTextView.setText(toDoPriority.getName());

        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return  getView(position, convertView, parent);
    }

    private static class ToDoPriorityHolder {
        View colorView;
        TextView nameTextView;
    }
}
