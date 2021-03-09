package com.example.android_todolist.todoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_todolist.R
import com.example.android_todolist.data.ToDo

class ToDoAdapter(private val onClick: (ToDo) -> Unit):
        ListAdapter<ToDo, ToDoAdapter.ToDoViewHolder>(ToDoDiffCallback) {

    class ToDoViewHolder(itemView: View, val onClick: (ToDo) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val documentId: TextView = itemView.findViewById(R.id.document_id)
        private var currentToDo: ToDo? = null

        init {
            itemView.setOnClickListener {
                currentToDo?.let {
                    onClick(it)
                }
            }
        }

        fun bind(todo: ToDo) {
            currentToDo = todo

            title.text = todo.title
            documentId.text = todo.documentId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        return ToDoViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }


}

object ToDoDiffCallback : DiffUtil.ItemCallback<ToDo>() {
    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem.documentId == newItem.documentId
    }
}