package com.example.daniel.todoapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.daniel.todoapp.core.ToDoItem;

public class MainActivity extends AppCompatActivity {
    ViewPager vPager;
    TabLayout tabLayout;
    ToDoPagerAdapter pagerAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager)findViewById(R.id.vpPager);
        pagerAdapter = new ToDoPagerAdapter(getSupportFragmentManager());
        vPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(vPager);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddUpdateItemActivity.class);
                intent.putExtra("link.todo.ToDoItem", new ToDoItem(MainActivity.this));
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}
