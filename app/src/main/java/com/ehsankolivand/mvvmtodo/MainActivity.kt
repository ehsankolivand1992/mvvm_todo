package com.ehsankolivand.mvvmtodo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ehsankolivand.mvvmtodo.Adapters.ViewPagerFragments
import com.ehsankolivand.mvvmtodo.Fragments.FragDone
import com.ehsankolivand.mvvmtodo.Fragments.FragToDo
import com.ehsankolivand.mvvmtodo.Utility.DialogListinner
import com.ehsankolivand.mvvmtodo.Utility.TaskDialog
import com.ehsankolivand.mvvmtodo.ViewModel.MainTaskViewModel
import com.ehsankolivand.mvvmtodo.database.TaskModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),FragDone.OnFragmentInteractionListener,FragToDo.OnFragmentInteractionListener,DialogListinner {

    private lateinit var mainTaskViewModel: MainTaskViewModel
    lateinit var mydialog: TaskDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainTaskViewModel = ViewModelProvider(this).get(MainTaskViewModel::class.java)

        initMain()
        initFab()
    }


    override fun onFragmentInteraction(uri: Uri) {
    }

    private fun initMain() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val adapter = ViewPagerFragments(supportFragmentManager)
        adapter.addFragment(FragToDo(), "Todo")
        adapter.addFragment(FragDone(), "Done")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun initFab()
    {
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        fab.setOnClickListener {

             mydialog = TaskDialog(this,this)
            mydialog.ShowTaskDialog()
        }
    }

    override fun addTaskToDatabase(task: TaskModel) {
        mainTaskViewModel.Insert(task)


    }
}
