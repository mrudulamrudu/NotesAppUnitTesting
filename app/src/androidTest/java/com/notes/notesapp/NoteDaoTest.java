package com.notes.notesapp;

import com.notes.notesapp.models.Note;
import com.notes.notesapp.util.TestUtil;

import org.junit.Test;

public class NoteDaoTest extends NotesDatabaseTest {

    /*
        Insert, Read, Delete
     */
    @Test
    public void insertReadDelete() throws Exception {
        Note note = new Note(TestUtil.TEST_NOTE_1);
    }

     /*
        Insert, Read, Update, Read, Delete
     */

      /*
        Insert note with null title, throws exception
     */

      /*
        Insert , Update null title, throws exception
       */

}