package com.acha.haha.Todo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.acha.haha.R

class MyTodoDialog(context: Context, myInterface: MyTodoDialogInterface) : Dialog(context){

    private var myTodoDialogInterface : MyTodoDialogInterface = myInterface

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus_todo)

        var okButton : Button = findViewById(R.id.okButton)
        var todoEditView : EditText = findViewById(R.id.plus_todo_context)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener{
            val todo = todoEditView.text.toString()
        }

    }
}