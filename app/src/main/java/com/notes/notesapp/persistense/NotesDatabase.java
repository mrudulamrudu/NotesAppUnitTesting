package com.notes.notesapp.persistense;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.notes.notesapp.models.Note;

@Database(entities = {Note.class}, version = 2)
public abstract class NotesDatabase extends RoomDatabase {

    public static final String DB_NAME = "notes_db";

    public abstract NoteDao getNoteDao();
}
