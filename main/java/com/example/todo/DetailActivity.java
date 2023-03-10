package com.example.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.BreakIterator;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    private EditText titleEditText, descEditText,startEditText,endEditText;
    private Button deleteButton;
    private ToDo selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initWidgets();
        checkForEditNote();
    }

    private void initWidgets()
    {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        startEditText = findViewById(R.id.startEditText);
        endEditText = findViewById(R.id.endEditText);
        deleteButton = findViewById(R.id.delete);
    }

    private void checkForEditNote()
    {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(ToDo.NOTE_EDIT_EXTRA, -1);
        selectedNote = ToDo.getNoteForID(passedNoteID);

        if (selectedNote != null)
        {
            titleEditText.setText(selectedNote.getTitle());
            descEditText.setText(selectedNote.getDescription());
            startEditText.setText(selectedNote.getStartDate());
            endEditText.setText(selectedNote.getEndDate());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveNote(View view)
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());
        String start = String.valueOf(startEditText.getText());
        String end = String.valueOf(endEditText.getText());

        if(selectedNote == null)
        {
            int id = ToDo.ArrayList.size();
            ToDo newNote = new ToDo(id, title, desc,start,end);
            ToDo.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
        }
        else
        {
            selectedNote.setTitle(title);
            selectedNote.setDescription(desc);
            selectedNote.setTitle(start);
            selectedNote.setTitle(end);
            sqLiteManager.updateNoteInDB(selectedNote);
        }

        finish();
    }

    public void deleteNote(View view)
    {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }
}