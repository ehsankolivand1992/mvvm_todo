package com.ehsankolivand.mvvmtodo.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ehsankolivand.mvvmtodo.database.TaskModel
import com.ehsankolivand.mvvmtodo.database.TaskRepository
import com.ehsankolivand.mvvmtodo.database.TodoDatabase
import kotlinx.coroutines.launch

class MainTaskViewModel(application: Application): AndroidViewModel(application){
    private val repository: TaskRepository
    val allTask: LiveData<List<TaskModel>>
    val workedDoneTask: LiveData<List<TaskModel>>

    init {
        val taskDao = TodoDatabase.getDatabase(application,viewModelScope).taskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.allTask
        workedDoneTask = repository.allWookedDoneTask
    }

    fun Insert(taskModel: TaskModel) = viewModelScope.launch {
        repository.Insert(taskModel)
    }

    fun Delete(taskModel: TaskModel) = viewModelScope.launch {
        repository.delete(taskModel)
    }

    fun Update(taskModel: TaskModel) = viewModelScope.launch {
        repository.update(taskModel)
    }
    fun DeleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}