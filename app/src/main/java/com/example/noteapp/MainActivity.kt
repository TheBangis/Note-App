package com.example.noteapp

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity(), NoteRVAdapter.NoteClickInterface, NoteRVAdapter.NoteClickDeleteInterface {

    //creating variable for viewmodel, recycleview and FabButton
    lateinit var viewModel: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this, this, this)

        //we are setting  adapter to our recycler view.
        notesRV.adapter = noteRVAdapter


        // initialize viewmodel

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        viewModel.allNotes.observe(this, androidx.lifecycle.Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.update(it)
            }
        })

        addFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)


        }

    }

    override fun onDeleteIconClick(note: Note) {

        viewModel.deleteNote(note)

        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteContent)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }
}