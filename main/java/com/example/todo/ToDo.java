package com.example.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ToDo {

    public static ArrayList<ToDo> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA =  "noteEdit";
    public static HashMap<Object, Object> ArrayList;
    //public static HashMap<Object, Object> NoteArrayList;

    private int id;
    

    private String title;
    private String description;

    public String getStartDate() {
        return startDate;
    }

    private String startDate;
    private String endDate;
    private Date deleted;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    

    public ToDo(int id, String title, String description,String startDate, String endDate, Date deleted)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deleted = deleted;
    }

    public ToDo(int id, String title, String description,String startDate, String endDate)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        deleted = null;
    }

    public static ToDo getNoteForID(int passedNoteID)
    {
        for (ToDo note : noteArrayList)
        {
            if(note.getId() == passedNoteID)
                return note;
        }

        return null;
    }

    public static ArrayList<ToDo> nonDeletedNotes()
    {
        ArrayList<ToDo> nonDeleted = new ArrayList<>();
        for(ToDo note : noteArrayList)
        {
            if(note.getDeleted() == null)
                nonDeleted.add(note);
        }

        return nonDeleted;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }

    
}
