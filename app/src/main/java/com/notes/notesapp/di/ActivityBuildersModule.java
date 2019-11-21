package com.notes.notesapp.di;

import com.notes.notesapp.NotesListActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

   @ContributesAndroidInjector
    abstract NotesListActivity contributesNotesListActivity();
}
