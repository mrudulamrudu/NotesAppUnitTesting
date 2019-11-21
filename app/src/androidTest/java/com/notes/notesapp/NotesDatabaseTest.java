package com.notes.notesapp;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.notes.notesapp.persistense.NoteDao;
import com.notes.notesapp.persistense.NotesDatabase;

import org.junit.After;
import org.junit.Before;

public abstract class NotesDatabaseTest {

    /*
     Instrumentation tests are written using Junit4 as Junit5 does support devices below API level 25
     */

    private NotesDatabase notesDatabase;

    public NoteDao getNoteDao() {
        return notesDatabase.getNoteDao();
    }

    // Create temporory database
    @Before
    public void init() {
        notesDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NotesDatabase.class
        ).build();
    }

    // Will be executed after every test
    // Close the DB
    @After
    public void finish() {
        notesDatabase.close();
    }
}
