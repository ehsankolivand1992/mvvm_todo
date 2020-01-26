package com.ehsankolivand.mvvmtodo.Adapters

import android.content.Context
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ehsankolivand.mvvmtodo.Fragments.FragDone
import com.ehsankolivand.mvvmtodo.Fragments.FragToDo
import com.ehsankolivand.mvvmtodo.R
import com.ehsankolivand.mvvmtodo.Utility.AdapterEventistinner
import com.ehsankolivand.mvvmtodo.Utility.DialogListinner
import com.ehsankolivand.mvvmtodo.database.TaskModel

class TodoAdapter internal constructor(fragToDo: FragToDo,fragDone: FragDone,page: Int):
    RecyclerView.Adapter<TodoAdapter.TaskItemHolder>() {

    private var tasks = emptyList<TaskModel>()
     var adapterEvent: AdapterEventistinner
    init {
        when(page) {
           1->{adapterEvent = fragToDo}

            2->{adapterEvent = fragDone}
            else ->{
                adapterEvent = fragToDo
            }
        }
    }

    inner class  TaskItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.rv_txt_title)
        val txtDescription = itemView.findViewById<TextView>(R.id.rv_txt_description)
        val cardEdit = itemView.findViewById<CardView>(R.id.rv_card_edit)
        val cardDone = itemView.findViewById<CardView>(R.id.rv_card_done)
        val cardDelete = itemView.findViewById<CardView>(R.id.rv_card_delete)
        val cardMain = itemView.findViewById<CardView>(R.id.rv_card_main)






    }
    internal fun setTask(taskModel: List<TaskModel>)
    {
        this.tasks = taskModel

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        val itemView = inflater.inflate(R.layout.rv_item,parent,false)
        return TaskItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskItemHolder, position: Int) {
        val currentTask = tasks[position]
        holder.txtTitle.text = currentTask.title
        holder.txtDescription.text = currentTask.description
        holder.cardMain

        holder.cardDelete.setOnClickListener {
            adapterEvent.delete(currentTask)
        }
        holder.cardDone.setOnClickListener {
            adapterEvent.done(currentTask)
        }

        holder.cardEdit.setOnClickListener {
            adapterEvent.edit(currentTask)
        }

    }
}


