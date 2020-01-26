package com.ehsankolivand.mvvmtodo.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class TaskModel(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String,
    var description: String,
    var value: Int,
    var done: Boolean

)