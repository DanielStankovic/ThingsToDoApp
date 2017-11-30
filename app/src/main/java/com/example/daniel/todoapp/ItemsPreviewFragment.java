package com.example.daniel.todoapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daniel.todoapp.core.ToDoItem;
import com.example.daniel.todoapp.core.ToDoItemAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsPreviewFragment extends Fragment {

    ListView listView;
    String mode;


    public ItemsPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_items_preview, container, false);

        listView = (ListView)view.findViewById(R.id.itemsListView);
        TextView textView = (TextView)view.findViewById(R.id.emptyTextView);
        listView.setEmptyView(textView);

        Bundle args = getArguments();
        mode = args.getString("mode", "active");
        if(mode.equals("active")){
            loadActiveItems(listView);
        }else {
            loadCompltedItems(listView);
        }
        return view;
    }

    private void loadActiveItems(ListView listView){
        ToDoItem [] items = ToDoItem.readAllActive(getActivity());
        final ToDoItemAdapter toDoItemAdapter = new ToDoItemAdapter(getActivity(), R.layout.todo_item_layout, items);
        listView.setAdapter(toDoItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem toDoItem = toDoItemAdapter.getItem(position);

                Intent intent = new Intent(getContext(), ItemPreviewActivity.class);
                intent.putExtra("link.todo.ToDoItem", toDoItem);
                startActivity(intent);
            }
        });

    }

    private void loadCompltedItems(ListView listView){
        ToDoItem[]items = ToDoItem.readAllCompleted(getActivity());
        final ToDoItemAdapter toDoItemAdapter = new ToDoItemAdapter(getActivity(), R.layout.todo_item_layout, items);
        listView.setAdapter(toDoItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem toDoItem = toDoItemAdapter.getItem(position);

                Intent intent = new Intent(getContext(), ItemPreviewActivity.class);
                intent.putExtra("link.todo.ToDoItem", toDoItem);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mode.equals("active")){
            loadActiveItems(listView);
        }else{
            loadCompltedItems(listView);
        }
    }
}
