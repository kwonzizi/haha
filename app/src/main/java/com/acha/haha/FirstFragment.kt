package com.acha.haha

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acha.haha.MainActivity
import com.acha.haha.R
import androidx.viewpager2.adapter.FragmentStateAdapter

import androidx.viewpager2.widget.ViewPager2


class FirstFragment : BaseFragment() {

    lateinit var mContext: Context

    lateinit var calendarViewPager: ViewPager2

    companion object {
        var instance: FirstFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        calendarViewPager = view.findViewById(R.id.calendarViewPager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        val calendarPagerFragmentStateAdapter = CalendarPagerFragmentStateAdapter(requireActivity())
        calendarViewPager.adapter = calendarPagerFragmentStateAdapter
        calendarViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        calendarPagerFragmentStateAdapter.apply {
            calendarViewPager.setCurrentItem(this.firstFragmentPosition, false)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

}


