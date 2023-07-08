package com.acha.haha.Todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Month
import java.time.YearMonth

@Entity
data class Todo(
    val check : Boolean,
    val content : String,
    val year : Int,
    val month: Int,
    val day : Int){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

