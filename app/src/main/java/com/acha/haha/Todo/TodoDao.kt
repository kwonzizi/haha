package com.acha.haha.Todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo : Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM Todo ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun readAllData() : Flow<List<Todo>>

    @Query("SELECT * FROM Todo WHERE year= :year AND month = :month AND day = :day OR DESC by id DESC")
    fun readDateData(year : Int, month : Int, day : Int) : Flow<List<Todo>>


}