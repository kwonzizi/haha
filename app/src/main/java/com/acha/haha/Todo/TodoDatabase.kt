package com.acha.haha.Todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object{
        private var instance : TodoDatabase? = null

        fun getDatabase(context: Context) : TodoDatabase? {
            if(instance == null) {
                synchronized(TodoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo_database"
                    ).build()
                }
            }

            return instance
        }
    }
}