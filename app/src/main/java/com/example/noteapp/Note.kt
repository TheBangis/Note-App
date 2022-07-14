package com.example.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")

class Note(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "content") val noteContent: String,
    @ColumnInfo(name = "timestamp") val timeStamp: String
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}
