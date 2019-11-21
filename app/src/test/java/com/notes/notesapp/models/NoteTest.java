package com.notes.notesapp.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NoteTest {

    private static final String TIMESTAMP_1 = "14-11-2019";
    private static final String TIMESTAMP_2 = "15-11-2019";

    /*
       Test two equal notes
     */
    @Test
    public void note_testEqualNotes_returnsTrue() throws Exception {
        Note note1 = new Note(1, "Note one", "This is note one", TIMESTAMP_1);
        Note note2 = new Note(1, "Note one", "This is note one", TIMESTAMP_1);

        assertEquals(note1, note2);
    }

    /*
      Test notes with different ids
    */
    @Test
    public void note_testNotes_returnsTrue() throws Exception {
        Note note1 = new Note(1, "Note one", "This is note one", TIMESTAMP_1);
        Note note2 = new Note(2, "Note one", "This is note one", TIMESTAMP_1);

        assertNotEquals(note1, note2);
    }

    /*
      Test notes with different title
    */
    @Test
    public void note_testEqualNotesWithDifTitle_returnsFalse() throws Exception {
        Note note1 = new Note(1, "Note one", "This is note one", TIMESTAMP_1);
        Note note2 = new Note(1, "Note Two", "This is note one", TIMESTAMP_1);

        assertNotEquals(note1, note2);
    }

    /*
      Test notes with different content
    */
    @Test
    public void note_testEqualNotesWithDifContent_returnsFalse() throws Exception {
        Note note1 = new Note(1, "Note one", "This is note one", TIMESTAMP_1);
        Note note2 = new Note(1, "Note one", "This is note two", TIMESTAMP_1);

        assertNotEquals(note1, note2);
    }

    /*
      Test notes with different timestamps
    */
    @Test
    public void note_testEqualNotesWithDifTimeStamp_returnsFalse() throws Exception {
        Note note1 = new Note(1, "Note one", "This is note one", TIMESTAMP_1);
        Note note2 = new Note(1, "Note one", "This is note one", TIMESTAMP_2);

        assertNotEquals(note1, note2);
    }
}
