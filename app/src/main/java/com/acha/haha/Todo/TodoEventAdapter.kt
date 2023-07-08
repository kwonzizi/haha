package com.acha.haha.Todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acha.haha.databinding.FragmentTodoBinding
import com.acha.haha.databinding.TodoItemBinding

class TodoEventAdapter : RecyclerView.Adapter<TodoEventAdapter.MyViewHolder>() {

    private var todoList = emptyList<Todo>()

    class MyViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = todoList[position]
        val currentContent = currentItem.content
        val currentYear = currentItem.year
        val currentMonth = currentItem.month
        val currentDay = currentItem.day

        val s_currentYear = currentYear.toString()
        var s_currentMonth = currentMonth.toString()
        var s_currentDay = currentDay.toString()

        if(currentMonth < 10) s_currentMonth = "0$currentMonth"
        if(currentDay < 10) s_currentDay = "0$currentDay"

        holder.binding.todoCheckBox.text = currentContent
        holder.binding.dateTextView.text = "$s_currentYear/$s_currentMonth/$s_currentDay"

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(todo : List<Todo>){
        todoList = todo
        notifyDataSetChanged()
    }
}