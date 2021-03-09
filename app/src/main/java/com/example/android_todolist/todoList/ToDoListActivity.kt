package com.example.android_todolist.todoList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android_todolist.R
import com.example.android_todolist.addToDo.AddToDoActivity
import com.example.android_todolist.addToDo.TODO_DETAIL
import com.example.android_todolist.addToDo.TODO_TITLE
import com.example.android_todolist.authentication.COLLECTION_ID
import com.example.android_todolist.data.ToDo
import com.example.android_todolist.todoDetail.ToDoDetailActivity
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

const val TODO_ID = "todo id"

class ToDoListActivity: AppCompatActivity(), CoroutineScope {
    private val newToDoActivityRequestCode = 1
    private lateinit var viewModel: ToDoListViewModel
    private var currentEmail: String? = null
    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentEmail = bundle.getString(COLLECTION_ID)
        }
        viewModel = ViewModelProvider(this) [ToDoListViewModel::class.java]

        launch {
            currentEmail?.let {
            val todoAdapter = ToDoAdapter { todo -> adapterOnClick(todo) }
            val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
            recyclerView.adapter = todoAdapter
            val currentToDoList = viewModel.getToDoList(it)
            todoAdapter.submitList(currentToDoList as MutableList<ToDo>)
            Log.d("********ToDoList********", "$currentEmail")
            Log.d("********ToDoList********", "$currentToDoList")
            }
        }


//        viewModel.todoLiveData.observe(this, {
//            it?.let {
//                todoAdapter.submitList(it as MutableList<ToDo>)
//            }
//        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    private fun adapterOnClick(todo: ToDo) {
        val intent = Intent(this, ToDoDetailActivity::class.java)
        intent.putExtra(TODO_ID, todo.documentId)
        startActivity(intent)
    }

    private fun fabOnClick() {
        val intent = Intent(this, AddToDoActivity::class.java)
        startActivityForResult(intent, newToDoActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newToDoActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let { data ->
                val todoTitle = data.getStringExtra(TODO_TITLE)
                val todoDetail = data.getStringExtra(TODO_DETAIL)

                viewModel.addToDo(currentEmail, todoTitle, todoDetail)
            }
        }
    }


}