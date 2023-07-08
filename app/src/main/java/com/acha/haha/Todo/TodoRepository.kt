package com.acha.haha.Todo

import kotlinx.coroutines.flow.Flow


class TodoRepository(private val todoDao: TodoDao) {

    val readAllData : Flow<List<Todo>> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }
    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }
    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    fun readDateData(year : Int, month : Int, day : Int) : Flow<List<Todo>> {
        return todoDao.readDateData(year,month, day)
    }


 }