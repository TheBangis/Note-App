package com.example.noteapp

import android.content.Context
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface

) :  RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       val noteContentTv = itemView.findViewById<TextView>(R.id.noteContent)
       val noteDateTv = itemView.findViewById<TextView>(R.id.noteDate)
       val deleteIcon = itemView.findViewById<ImageView>(R.id.deleteIcon)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRVAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_note_holder, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteRVAdapter.ViewHolder, position: Int) {
        holder.noteContentTv.setText(allNotes.get(position).noteTitle)

        holder.noteDateTv.setText("Last Updated : " + allNotes.get(position).timeStamp)

        holder.deleteIcon.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    //create method for update

    fun update(newList: List<Note>){

        allNotes.clear()

        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

    interface NoteClickDeleteInterface{

    //  action on delete image view.
        fun onDeleteIconClick(note: Note)
    }

    interface NoteClickInterface{

    // recycler view item for updating it.
        fun onNoteClick(note: Note)
    }

}