package com.example.android_todolist.data

import android.util.Log
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentSnapshot
import java.lang.Exception

data class ToDo(
        @DocumentId
        val documentId: String?,
        val title: String?,
        val detail: String?
) {
    companion object {
        fun DocumentSnapshot.toToDo(): ToDo? {
            return try {
                val documentId = getString("documentId")
                val title = getString("title")
                val detail = getString("detail")
                ToDo(documentId ?:"", title ?:"", detail ?:"")
            } catch (e: Exception) {
                Log.e(TAG, "Error converting ToDo", e)
                null
            }
        }
        private const val TAG = "**********ToDo**********"
    }
}