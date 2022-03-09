package com.example.notesapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapplication.modal.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
    companion object {


        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            //return INSTANCE ?: synchronized(this)
            //{
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    NoteDatabase::class.java,
//                    "note_database"
//                ).build()
//                INSTANCE = instance
//                instance

                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(
                        context,NoteDatabase::class.java,
                        "jokesDB"
                    ).build()
                }
            return INSTANCE!!
            }

        }

    }
