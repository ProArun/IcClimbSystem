package com.arun.icclimbsystem.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classNote_table")
data class Note(
    var subCode: String = "",
    var subName: String = "",
    var faculty: String = "",
    var periodType: String = "",
    var subType: String = "",
    var classTime: Long = 0L,
    var classDate: Long = 0L
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
