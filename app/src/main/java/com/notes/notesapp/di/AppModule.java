package com.notes.notesapp.di;

import android.app.Application;

import androidx.room.Room;

import com.notes.notesapp.persistense.NoteDao;
import com.notes.notesapp.persistense.NotesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    @Singleton
    @Provides
    static NotesDatabase providesDatabase(Application application) {
        return Room.databaseBuilder(application, NotesDatabase.class, NotesDatabase.DB_NAME).build();
    }

    @Singleton
    @Provides
    static NoteDao providesNoteDao(NotesDatabase notesDatabase) {
        return notesDatabase.getNoteDao();
    }
}
