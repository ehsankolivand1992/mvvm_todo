package com.ehsankolivand.mvvmtodo.database

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao){

    val allTask: LiveData<List<TaskModel>> = taskDao.getAllTask()
    val allWookedDoneTask: LiveData<List<TaskModel>> = taskDao.getTaskWorkDone()


    suspend fun Insert(taskModel: TaskModel){
        taskDao.insertTask(taskModel)
    }
    suspend fun delete(taskModel: TaskModel){
        taskDao.delete(taskModel)
    }
    suspend fun deleteAll(){
        taskDao.deleteAll()
    }
    suspend fun update(taskModel: TaskModel)
    {
        taskDao.update(taskModel)
    }

}