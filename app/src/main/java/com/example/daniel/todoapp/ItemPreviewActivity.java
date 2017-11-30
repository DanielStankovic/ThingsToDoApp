package com.example.daniel.todoapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.todoapp.core.ToDoItem;

public class ItemPreviewActivity extends AppCompatActivity {

    private ToDoItem toDoItem;

    TextView titleTextView;
    TextView contentTextView;

    View priorityColorView;
    TextView priorityNameTextView;

    TextView dayTextView;
    TextView monthTextView;
    TextView yearTextView;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_preview);

        setActionBar();

        Intent intent = getIntent();
        toDoItem = intent.getParcelableExtra("link.todo.ToDoItem");
        toDoItem.setContext(this);
        placeData();
    }

    private void placeData() {

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        contentTextView = (TextView) findViewById(R.id.contentTextView);

        priorityColorView = findViewById(R.id.priorityColorView);
        priorityNameTextView = (TextView) findViewById(R.id.priorityNameTextView);

        dayTextView = (TextView) findViewById(R.id.dayTextView);
        monthTextView = (TextView) findViewById(R.id.monthTextView);
        yearTextView = (TextView) findViewById(R.id.yearTextView);

        titleTextView.setText(toDoItem.getTitle());
        contentTextView.setText(toDoItem.getContent());

        priorityColorView.setBackgroundColor(toDoItem.getToDoPriority().getUIColor()[0]);
        priorityNameTextView.setText(toDoItem.getToDoPriority().getName());

        dayTextView.setText(toDoItem.getDay());
        monthTextView.setText(toDoItem.getMonth());
        yearTextView.setText(toDoItem.getYear());

        if (!toDoItem.getCompleted()) {
            actionBar.setBackgroundDrawable(new ColorDrawable(toDoItem.getToDoPriority().getUIColor()[0]));
            titleTextView.setBackgroundColor(toDoItem.getToDoPriority().getUIColor()[0]);

            Window window = this.getWindow();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(toDoItem.getToDoPriority().getUIColor()[1]);
            }
        } else {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.completed)));
            titleTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.completed));

            Window window = this.getWindow();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.completed_dark));
            }
        }
    }


    /**************SETUP ACTION BAR**********************/

    private void setActionBar() {
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (toDoItem.getCompleted()) {
            getMenuInflater().inflate(R.menu.preview_ab_menu_stripped, menu);
        } else {
            getMenuInflater().inflate(R.menu.preview_actionbar_menu, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_item:
                Intent intent = new Intent(this, AddUpdateItemActivity.class);
                intent.putExtra("link.todo.ToDoItem", toDoItem);
                startActivity(intent);

                this.finish();

                return true;
            case R.id.delete_item:
                if (toDoItem.delete()) {
                    Toast.makeText(this, "ToDo Item deleted.", Toast.LENGTH_SHORT).show();
                    this.finish();
                }
                return true;
            case R.id.complete_item:
                if (toDoItem.markCompleted()) {
                    Toast.makeText(this, "ToDo Item marked as completed.", Toast.LENGTH_SHORT).show();
                    this.finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
