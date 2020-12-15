package com.arun.icclimbsystem.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM classNote_table WHERE classDate == :date")
    fun getAllNoteSortedByTime(date:Long):LiveData<List<Note>>
}