package com.acha.haha.Todo

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.acha.haha.R
import com.acha.haha.databinding.FragmentTodoBinding
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Calendar
import java.util.Locale




class TodoFragment : Fragment(){

    private var _binding : FragmentTodoBinding? = null
    private val binding get() = _binding!!

    lateinit var todoAdapter : TodoAdapter
    private var todoCalendarList = ArrayList<TodoCalendarVO>()

    private val todoViewModel : TodoViewModel by viewModels()

    private val eventAdapter : TodoEventAdapter by lazy { TodoEventAdapter() }

    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0




    companion object {
        fun newInstance() = TodoFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)

        _binding!!.todofabMain.setOnClickListener {
            Toast.makeText(activity, "테스트", Toast.LENGTH_SHORT).show()

        }
        _binding!!.todoWeekContent.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        _binding!!.todoWeekContent.adapter = eventAdapter


        todoViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            eventAdapter.setData(it)
        })



        val root: View = binding.root


        return root

    }




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var week_day : Array<String> = resources.getStringArray(R.array.todo_calendar_day)
        todoAdapter = TodoAdapter(todoCalendarList)

        todoCalendarList.apply{
            val dateFormat = DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko"))
            val monthFormat = DateTimeFormatter.ofPattern("yyyy년 MM월").withLocale(Locale.forLanguageTag("ko"))

            val localDate = LocalDateTime.now().format(monthFormat)
            binding.todoTextYearMonth.text = localDate

            val preSunday = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY))

            for(i in 0..6){

                todoCalendarList.apply {
                    add(TodoCalendarVO(preSunday.plusDays(i.toLong()).format(dateFormat), week_day[i]))
                }

            }

        }

        binding.todoWeekRecycler.adapter = todoAdapter
        binding.todoWeekRecycler.layoutManager = GridLayoutManager(context, 7)

        todoAdapter.setItemClickListener(object : TodoAdapter.OnItemClickListener{

            override fun onClick(v: View, position: Int) {
                Toast.makeText(view.context,"클릭",
                    Toast.LENGTH_SHORT).show()

            }
        })
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }





}







