package com.arun.icclimbsystem.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arun.icclimbsystem.db.Note
import com.arun.icclimbsystem.repositories.MainRepository
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(
    private val repository: MainRepository
) : ViewModel() {
    private val _curDayNote = MutableLiveData<List<Note>>()
    val curDayNote: LiveData<List<Note>> = _curDayNote

    fun insertNote(
        subCode: String,
        subName: String,
        periodType: String,
        subType: String,
        classTime: Long?,
        classDate: Long?
    ) {
        if (subCode.isEmpty() || subName.isEmpty() || periodType.isEmpty() || subType.isEmpty() || classTime == null || classDate == null) {
            return
        }
        val rnds = (0..10).random() // generated random from 1 to 9 included
        searchForFaculty(rnds)
    }

    private fun searchForFaculty(rnds: Int) = viewModelScope.launch {
        repository.getFaculty(rnds)
    }

    fun insertNoteIntoDb(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getAllNoteSortedByTime(date: Date) {
        val dateInMillis: Long = convertDateToMillis(date)
        val cNote = repository.getAllNoteSortedByTime(dateInMillis)
        // _curDayNote.postValue()
    }

    private fun convertDateToMillis(date: Date): Long {
        return 1L
    }
}