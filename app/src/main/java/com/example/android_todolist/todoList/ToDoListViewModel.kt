package com.example.android_todolist.todoList

import androidx.lifecycle.*
import com.example.android_todolist.fireStore.FirebaseToDoService
import com.example.android_todolist.data.ToDo
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ToDoListViewModel: ViewModel(), CoroutineScope {
    private val _todo = MutableLiveData<List<ToDo>>()
    val todoLiveData: LiveData<List<ToDo>> = _todo
    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

//    init {
//        viewModelScope.launch {
//            _todo.value = FirebaseToDoService.getToDoData()
//        }
//    }

    suspend fun getToDoList(
            collectionId: String
    ): List<ToDo>? {
        return FirebaseToDoService.getToDoData(collectionId)
    }

    fun addToDo(
        currentEmail: String?,
        todoTitle: String?,
        todoDetail: String?
    ) {
        if (currentEmail == null || todoTitle == null || todoDetail == null) {
            return
        }
        FirebaseToDoService.addToDoData(
                collectionId = currentEmail,
                todoTitle = todoTitle,
                todoDetail = todoDetail)
    }

}

