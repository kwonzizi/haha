package com.acha.haha

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setDate")
fun TextView.setDate(item: CalendarInfo?){
    item?.let{
        text = it.dayOfMonth.toString()
    }
}