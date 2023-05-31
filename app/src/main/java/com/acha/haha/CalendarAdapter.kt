package com.acha.haha

import android.content.ClipData.Item
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarAdapter(val context:Context, val calendarLayout: LinearLayout, val date: Date):
    RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>(){

    var dataList : ArrayList<Int> = arrayListOf()

    var furangCalendar : FurangCalendar = FurangCalendar(date)

    init {
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
    }

    interface ItemClick {
        fun onClick(view:View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {

        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h


        holder?.bind(dataList[position], position, context)

        if (itemClick != null) {
            holder?.itemView?.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarItemHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.list_item_calendar, parent, false)
        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int = dataList.size
    inner class CalendarItemHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!){
        var itemCalendarDateText : TextView = itemView!!.findViewById(R.id.item_calendar_date_text)
        var itemCalendarDotView : View = itemView!!.findViewById(R.id.item_calendar_dot_view)

        fun bind(data: Int, position: Int, context: Context){
            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead - 1

            //반복문 사용해서 하면 될듯 ..? 토일 색상 지정
            //if(dataList.size % 7 == 0){
            //    itemCalendarDateText.setTextAppearance(R.style.Sunday)
            //}
            itemCalendarDateText.setText(data.toString())
            // 오늘 날짜 처리
            var dateString : String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()

            if(dataList[position] == dateInt) {
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            if(position < firstDateIndex || position > lastDateIndex){
                // itemCalendarDateText.setTextAppearance()
                itemCalendarDateText.setTextAppearance(R.style.LightColorTextViewStyle)
                itemCalendarDotView.background = null

            }
        }

        }
    }


