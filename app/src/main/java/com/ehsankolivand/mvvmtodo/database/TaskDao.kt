package com.ehsankolivand.mvvmtodo.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks where done = 0 Order by id DESC")
    fun getAllTask(): LiveData<List<TaskModel>>

    @Query("SELECT * FROM Tasks where done = 1 Order by id DESC ")
    fun getTaskWorkDone(): LiveData<List<TaskModel>>

    @Insert
    suspend fun insertTask(taskModel: TaskModel)

    @Delete
    suspend fun delete(taskModel: TaskModel)

    @Query("DELETE FROM Tasks")
    suspend fun deleteAll()

    @Update
    suspend fun update(taskModel: TaskModel)

}