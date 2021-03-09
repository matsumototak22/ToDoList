package com.example.android_todolist.todoDetail

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android_todolist.R
import androidx.databinding.DataBindingUtil
import com.example.android_todolist.data.ToDo
import com.example.android_todolist.databinding.DetailTodoBinding
import com.example.android_todolist.todoList.TODO_ID
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ToDoDetailActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var viewModel: ToDoDetailViewModel
    private lateinit var binding: DetailTodoBinding
    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_todo)
        binding = DetailTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var currentDocumentId: String? = null
        val todoTitle: TextView = findViewById(R.id.detail_todo_title)
        val todoDetail: TextView = findViewById(R.id.detail_todo_detail)
        val completedButton: Button = findViewById(R.id.completed_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentDocumentId = bundle.getString(TODO_ID)
        }
        viewModel = ViewModelProvider(this) [ToDoDetailViewModel::class.java]

        launch {
            currentDocumentId?.let {
                val currentToDo = viewModel.getToDoForId(it)
                todoTitle.text = currentToDo?.title
                todoDetail.text = currentToDo?.detail
                Log.d("********ToDoDetail********","${currentToDo?.title}, ${currentToDo?.detail}")
                Log.d("********ToDoDetail********", "$currentDocumentId")
            }
        }


    }
}