package com.notes.notesapp.di;

import com.notes.notesapp.ui.notes.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

   @ContributesAndroidInjector
    abstract NotesListActivity contributesNotesListActivity();
}
