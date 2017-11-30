package com.example.daniel.todoapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.daniel.todoapp.core.ToDoItem;
import com.example.daniel.todoapp.core.ToDoPriority;
import com.example.daniel.todoapp.core.ToDoPriorityAdapter;

public class AddUpdateItemActivity extends AppCompatActivity {

    Spinner prioritySpinner;
    ToDoPriorityAdapter spinnerAdapter;
    EditText titleEditText;
    EditText contentEditText;
    ToDoPriority[] priorityArray;
    ToDoPriority selectedToDoPriority;
    ToDoItem toDoItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_item);
        setupActionBar();

        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);
        priorityArray = ToDoPriority.readAll(this);
        spinnerAdapter = new ToDoPriorityAdapter(this, R.layout.todo_priority_layout, R.id.nameTextView, priorityArray);
        prioritySpinner.setAdapter(spinnerAdapter);
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedToDoPriority = spinnerAdapter.getItem(position);
                changeHeaderColor();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toDoItem = getIntent().getParcelableExtra("link.todo.ToDoItem");
        //activity is in Update mode, so place data
        if (toDoItem.getId() != 0) {
            giveDataToUi();
        }
        setupActionBar();
    }
    private void giveDataToUi() {
        titleEditText.setText(toDoItem.getTitle());
        contentEditText.setText(toDoItem.getContent());
        for (int i = 0; i < spinnerAdapter.getCount(); i++) {
            ToDoPriority toDoPriority = spinnerAdapter.getItem(i);
            if (toDoPriority.getId() == this.toDoItem.getToDoPriority().getId()) {
                prioritySpinner.setSelection(i);
            }
        }
    }

    ActionBar actionBar;
    private void setupActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setHomeButtonEnabled(true);
    }
    private void changeHeaderColor() {
        actionBar.setBackgroundDrawable(new ColorDrawable(selectedToDoPriority.getUIColor()[0]));
        titleEditText.setBackgroundColor(selectedToDoPriority.getUIColor()[0]);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(selectedToDoPriority.getUIColor()[1]);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                toDoItem.setTitle(titleEditText.getText().toString());
                toDoItem.setContent(contentEditText.getText().toString());
                toDoItem.setToDoPriority(selectedToDoPriority);
                toDoItem.setContext(this);
                if (toDoItem.isValid()) {
                    if (toDoItem.getId() == 0) {
                        if (toDoItem.insert()) {
                            Toast.makeText(this, "New ToDo created!", Toast.LENGTH_SHORT).show();
                            this.finish();
                        }
                    } else {
                        if (toDoItem.update()) {
                            Toast.makeText(this, "ToDo updated!", Toast.LENGTH_SHORT).show();
                            this.finish();
                        }
                    }
                } else {
                    Toast.makeText(this, toDoItem.validationMessage, Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
