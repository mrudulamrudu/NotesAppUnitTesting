package com.notes.notesapp.repository;

import com.notes.notesapp.models.Note;
import com.notes.notesapp.persistense.NoteDao;
import com.notes.notesapp.ui.Resource;
import com.notes.notesapp.util.TestUtil;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import io.reactivex.Single;

import static com.notes.notesapp.repository.NoteRepository.INSERT_FAILURE;
import static com.notes.notesapp.repository.NoteRepository.INSERT_SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NoteRepositoryTest {

    private static final Note NOTE_1 = new Note(TestUtil.TEST_NOTE_1);

    private NoteRepository noteRepository;
    private NoteDao noteDao;

    @BeforeEach
    public void beforeEach() {
        noteDao = mock(NoteDao.class);
        noteRepository = new NoteRepository(noteDao);
    }

    /*
      insert note
      verify the correct method is called
      confirm observer is triggered
      confirm new rows inserted
    */
    @Test
    public void insertNote_returnRow() throws Exception {
        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);

        //insert
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE_1).blockingFirst();

        //assist
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(NoteDao.class);

        //verify
        assertEquals(Resource.success(1, INSERT_SUCCESS), returnedValue);

    }

     /*
        Insert note
        Failure (return -1)
     */

    @Test
    public void insertNote_returnFailure() throws Exception {
        final Long failedRow = 1L;
        final Single<Long> returnedData = Single.just(failedRow);

        //insert
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE_1).blockingFirst();

        //assist
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(NoteDao.class);

        //verify
        assertEquals(Resource.error(null, INSERT_FAILURE), returnedValue);
    }

    /*
    insert note
    null title
    confirm throw exception
    */
    @Test
    public void insertNote_nullTitle_throwException() throws Exception {
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note note = NOTE_1;
                note.setTitle(null);
                noteRepository.insertNote(note);
            }
        });
    }

    /*
       update note
       verify correct method is called
       confirm observer is trigger
       confirm number of rows updated
    */
    @Test
    void updateNote_returnNumRowsUpdated() throws Exception {

    }

    /*
        update note
        null title
        throw exception
     */
    @Test
    void updateNote_nullTitle_throwException() throws Exception {

    }

     /*
        delete note
        null id
        throw exception
     */

    @Test
    void deleteNote_nullId_throwException() throws Exception {

    }

       /*
        delete note
        delete success
        return Resource.success with deleted row
     */

    @Test
    void deleteNote_deleteSuccess_returnResourceSuccess() throws Exception {

    }

    /*
       delete note
       delete failure
       return Resource.error
    */
    @Test
    void deleteNote_deleteFailure_returnResourceError() throws Exception {

    }


    /*
        retrieve notes
        return list of notes
     */

    @Test
    void getNotes_returnListWithNotes() throws Exception {

    }

    /*
        retrieve notes
        return empty list
     */

    @Test
    void getNotes_returnEmptyList() throws Exception {

    }
}
