package com.example.noteapp

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao : NoteDao) {

    //create variable and get all note list from our Dao Interface

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    //insert method for adding note to d db
    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    //delete method for deleting a note in the db
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    //update method for updating note in the db
    suspend fun update(note: Note){
        notesDao.update(note)
    }

}