package com.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();
    }


    private void initWidgets() {
        noteListView = findViewById(R.id.todoListView);
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter() {
        ToDoAdapter noteAdapter = new ToDoAdapter(getApplicationContext(), ToDo.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }


    private void setOnClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ToDo selectedNote = (ToDo) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), DetailActivity.class);
                editNoteIntent.putExtra(ToDo.NOTE_EDIT_EXTRA, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }


    public void newNote(View view) {
        Intent newNoteIntent = new Intent(this, DetailActivity.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNoteAdapter();
    }
}