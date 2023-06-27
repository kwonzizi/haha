package com.acha.haha.Todo

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.acha.haha.AddPlanActivity
import com.acha.haha.R
import com.acha.haha.databinding.TodoCalendarListBinding

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale



class TodoAdapter(private val cList: List<TodoCalendarVO>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(private val binding: TodoCalendarListBinding) :

        RecyclerView.ViewHolder(binding.root){

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item : TodoCalendarVO){
        binding.todoDate.text = item.todo_date
        binding.todoDay.text = item.todo_day


        var today = binding.todoDate.text

        val now = LocalDate.now().format(DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko")))
        if(today == now ) {
            binding.weekCardview.setBackgroundResource(R.drawable.background_blue)
        }
            itemView.setOnClickListener{


            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TodoViewHolder {
        val binding = TodoCalendarListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(cList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    override fun getItemCount(): Int {
        return cList.size
    }
}