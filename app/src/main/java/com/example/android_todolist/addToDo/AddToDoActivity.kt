package com.example.android_todolist.addToDo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android_todolist.R
import com.google.android.material.textfield.TextInputEditText

const val TODO_TITLE = "title"
const val TODO_DETAIL = "detail"

class AddToDoActivity: AppCompatActivity() {
    private lateinit var addToDoTitle: TextInputEditText
    private lateinit var addToDoDetail: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addToDo()
        }
        addToDoTitle = findViewById(R.id.add_todo_title)
        addToDoDetail = findViewById(R.id.add_todo_detail)
    }

    private fun addToDo() {
        val resultIntent = Intent()

        if (addToDoTitle.text.isNullOrEmpty() || addToDoDetail.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val title = addToDoTitle.text.toString()
            val detail = addToDoDetail.text.toString()
            resultIntent.putExtra(TODO_TITLE, title)
            resultIntent.putExtra(TODO_DETAIL, detail)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}