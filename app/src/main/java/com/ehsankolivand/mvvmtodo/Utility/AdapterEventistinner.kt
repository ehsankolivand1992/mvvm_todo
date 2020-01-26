package com.ehsankolivand.mvvmtodo.Utility

import com.ehsankolivand.mvvmtodo.database.TaskModel

interface AdapterEventistinner {

    fun delete(task: TaskModel)
    fun edit(task: TaskModel)
    fun done(task: TaskModel)
}