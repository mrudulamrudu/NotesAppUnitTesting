package com.notes.notesapp;

import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.notes.notesapp.models.Note;
import com.notes.notesapp.util.LiveDataTesttUtil;
import com.notes.notesapp.util.TestUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NoteDaoTest extends NotesDatabaseTest {

    public static final String TEST_TITLE = "Test Ttile";
    public static final String TEST_CONTENT = "Test Content";
    public static final String TEST_TIMESTAMP = "10-2019";

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new
            InstantTaskExecutorRule(); // Used when working with background threads in instrumentation tests

    /*
        Insert, Read, Delete
     */
    @Test
    public void insertReadDelete() throws Exception {
        Note note = new Note(TestUtil.TEST_NOTE_1);

        // Insert
        getNoteDao().insertNote(note).blockingGet(); // Wait unit inserted

        // Read

        LiveDataTesttUtil<List<Note>> listLiveDataTesttUtil = new LiveDataTesttUtil<>();
        List<Note> insertedNotes = listLiveDataTesttUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);

        assertEquals(note.getContent(), insertedNotes.get(0).getContent());
        assertEquals(note.getTitle(), insertedNotes.get(0).getTitle());
        assertEquals(note.getTimestamp(), insertedNotes.get(0).getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note, insertedNotes.get(0));

        //Delete
        getNoteDao().deleteNote(note).blockingGet();

        //confirm the DB is empty
        insertedNotes = listLiveDataTesttUtil.getValue(getNoteDao().getNotes());
        assertEquals(0, insertedNotes.size());

    }

    /*
       Insert, Read, Update, Read, Delete
    */
    @Test
    public void insertReadDUpdateReadelete() throws Exception {
        Note note = new Note(TestUtil.TEST_NOTE_1);

        // Insert
        getNoteDao().insertNote(note).blockingGet(); // Wait unit inserted

        // Read
        LiveDataTesttUtil<List<Note>> listLiveDataTesttUtil = new LiveDataTesttUtil<>();
        List<Note> insertedNotes = listLiveDataTesttUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);

        assertEquals(note.getContent(), insertedNotes.get(0).getContent());
        assertEquals(note.getTitle(), insertedNotes.get(0).getTitle());
        assertEquals(note.getTimestamp(), insertedNotes.get(0).getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note, insertedNotes.get(0));

        note.setTitle(TEST_TITLE);
        note.setTitle(TEST_CONTENT);
        note.setTitle(TEST_TIMESTAMP);

        getNoteDao().updateNote(note).blockingGet();

        insertedNotes = listLiveDataTesttUtil.getValue(getNoteDao().getNotes());
        assertEquals(note.getContent(), insertedNotes.get(0).getContent());
        assertEquals(note.getTitle(), insertedNotes.get(0).getTitle());
        assertEquals(note.getTimestamp(), insertedNotes.get(0).getTimestamp());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note, insertedNotes.get(0));

        //Delete
        getNoteDao().deleteNote(note).blockingGet();

        //confirm the DB is empty
        insertedNotes = listLiveDataTesttUtil.getValue(getNoteDao().getNotes());
        assertEquals(0, insertedNotes.size());

    }

    /*
      Insert note with null title, throws exception
   */
    @Test(expected = SQLiteConstraintException.class)
    public void insertNullTitleThrowsException() throws Exception {
        Note note = new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);

        //insert
        getNoteDao().insertNote(note).blockingGet();
    }

    /*
      Insert , Update null title, throws exception
     */
    @Test(expected = SQLiteConstraintException.class)
    public void insertUpdateNoteNullTitleThrowsSQLiteConstraintException() throws Exception {
        Note note = new Note(TestUtil.TEST_NOTE_1);

        //insert
        getNoteDao().insertNote(note).blockingGet();

        // Read
        LiveDataTesttUtil<List<Note>> listLiveDataTesttUtil = new LiveDataTesttUtil<>();
        List<Note> insertedNotes = listLiveDataTesttUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);

        // Update

        note.setTitle(null);

        getNoteDao().updateNote(note).blockingGet();

    }
}