package com.ehsankolivand.mvvmtodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope


@Database(entities = [TaskModel::class],version = 1)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun taskDao():TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope):TodoDatabase {
            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }

}