package com.ehsankolivand.mvvmtodo.Utility

import com.ehsankolivand.mvvmtodo.database.TaskModel

interface DialogListinner {
    fun addTaskToDatabase(task: TaskModel)
}