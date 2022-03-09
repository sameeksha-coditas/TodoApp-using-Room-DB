package com.example.notesapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapplication.modal.Note

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

//    @Query("UPDATE notesTable SET title = :value1, description = :value2, timestamp= :value3 WHERE id = :id) "
//            suspend fun update(String value1,String value2,String value3,int id)


    @Delete
    suspend fun delete(note: Note)

    @Query(value = "Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}