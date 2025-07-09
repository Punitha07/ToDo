package com.example.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo> {
    public ToDoAdapter(Context context, List<ToDo> notes){
        super(context, 0,notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ToDo note = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_cell, parent, false);

        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDescription);
        TextView start = convertView.findViewById(R.id.cellStart);
        TextView end = convertView.findViewById(R.id.cellEnd);

        title.setText(note.getTitle());
        desc.setText(note.getDescription());
        start.setText(note.getStartDate());
        end.setText(note.getEndDate());

        return convertView;
    }
}
