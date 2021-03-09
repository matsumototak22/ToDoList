package com.example.android_todolist.todoDetail

import androidx.lifecycle.*
import com.example.android_todolist.fireStore.FirebaseToDoService
import com.example.android_todolist.data.ToDo
import com.example.android_todolist.todoList.TODO_ID
import kotlinx.coroutines.launch

class ToDoDetailViewModel: ViewModel() {
    private val _todoDetail = MutableLiveData<ToDo>()
    var todoDetailLiveData: LiveData<ToDo> = _todoDetail

//    init {
//        viewModelScope.launch {
//            _todoDetail.value = FirebaseToDoService.getToDoForId(TODO_ID)
//        }
//    }


    suspend fun getToDoForId(
        documentId: String
    ): ToDo? {
        return FirebaseToDoService.getToDoForId(documentId)
    }
}