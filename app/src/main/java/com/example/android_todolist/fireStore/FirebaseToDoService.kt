package com.example.android_todolist.fireStore

import android.util.Log
import com.example.android_todolist.authentication.COLLECTION_ID
import com.example.android_todolist.data.ToDo
import com.example.android_todolist.data.ToDo.Companion.toToDo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

object FirebaseToDoService {
    private const val TAG ="**********FirebaseToDoService**********"

    private fun setup() {
        val db = Firebase.firestore
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        db.firestoreSettings = settings
    }

    suspend fun getToDoData(
            collectionId: String
    ): List<ToDo> {
        val db = Firebase.firestore
        setup()
        return try {
            db.collection(collectionId)
                .get().await()
                .mapNotNull { it.toToDo() }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting ToDo Data", e)
            emptyList()
        }
    }

    fun addToDoData(
        collectionId: String,
        todoTitle: String,
        todoDetail: String
    ) {
        setup()
        val db = Firebase.firestore
        val ref = db.collection(collectionId).document()
        ref.set(
                hashMapOf(
                        "documentId" to ref.id,
                        "title" to todoTitle,
                        "detail" to todoDetail
                )
        )
    }

    suspend fun getToDoForId(
        documentId: String
    ): ToDo? {
        setup()
        val db = Firebase.firestore
        val ref = db.collection(COLLECTION_ID).document(documentId)
        return try {
            ref.get().await().toToDo()
        } catch (e: Exception) {
            Log.e(TAG, "Error getting ToDo Data", e)
            null
        }
    }
}