package com.arun.icclimbsystem.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.arun.icclimbsystem.db.Note
import com.arun.icclimbsystem.db.NoteDao
import com.arun.icclimbsystem.remote.Api
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val api: Api
) {
    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getAllNoteSortedByTime(date: Long): LiveData<List<Note>> =
        noteDao.getAllNoteSortedByTime(date)

    suspend fun getFaculty(id: Int) {

    }
}