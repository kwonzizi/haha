package com.acha.haha

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.acha.haha.databinding.ActivityMainBinding
import java.util.Calendar

class AddPlanActivity : AppCompatActivity() {


    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

//    private var datetext : TextView = findViewById(R.id.plus_date_text)
//
//    private var datebtn : Button = findViewById(R.id.plus_date_btn)

    @SuppressLint("SetText|18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus_plan)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val datebtn = findViewById<Button>(R.id.plus_date_btn)
        val datetext = findViewById<TextView>(R.id.plus_date_text)
        datebtn.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this,{ _, year, month, day ->
                datetext.text = year.toString() + "/" + (month + 1).toString()+"/" + day.toString()},
                year, month,day)
            datePickerDialog.show()
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
