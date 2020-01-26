package com.ehsankolivand.mvvmtodo.Utility

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.ehsankolivand.mvvmtodo.MainActivity
import com.ehsankolivand.mvvmtodo.R
import com.ehsankolivand.mvvmtodo.database.TaskModel

class TaskDialog(context: Activity, mainActivity: MainActivity): View.OnClickListener{

    private val context: Context
    val utilites = Utilites()
    val labels = arrayListOf<Boolean>(false,true,false)
    var labelValue: Int = 2
   lateinit var ll_first: LinearLayout
    lateinit var ll_second: LinearLayout
    lateinit var ll_third: LinearLayout

    val listinner:DialogListinner


    init {
        this.context = context
        this.listinner =mainActivity

    }

    fun ShowTaskDialog()
    {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.task_dialog)
        var customWindow = dialog.window
        customWindow?.setLayout(ActionBar.LayoutParams.MATCH_PARENT , ActionBar.LayoutParams.WRAP_CONTENT)
        val ed_tite = dialog.findViewById<EditText>(R.id.dig_ed_title)
        val ed_description = dialog.findViewById<EditText>(R.id.dig_ed_description)
        val btn_save = dialog.findViewById<Button>(R.id.dig_btn_add)
         ll_first = dialog.findViewById<LinearLayout>(R.id.dig_ll_first)
         ll_second = dialog.findViewById<LinearLayout>(R.id.dig_ll_second)
         ll_third = dialog.findViewById<LinearLayout>(R.id.dig_ll_third)

        ll_first.setOnClickListener(this)
        ll_second.setOnClickListener(this)
        ll_third.setOnClickListener(this)


        btn_save.setOnClickListener {
            getData(ed_tite.text.toString(),ed_description.text.toString())
            dialog.dismiss()
        }





        dialog.show()
    }

    private fun getData(title: String, desctipion: String) {
        if (title.isNotEmpty())
        {
            if (desctipion.isNotEmpty())
            {
                val task = TaskModel(0,title,desctipion,labelValue,false)
                listinner.addTaskToDatabase(task)



            }else{
                Toast.makeText(context,"Please Enter a Description",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(context,"Please Enter a title",Toast.LENGTH_LONG).show()

        }

    }

    override fun onClick(p0: View?) {

        when(p0?.id)
        {
            ll_first.id -> {
                setlabel(1)
            }
            ll_second.id->{
                setlabel(2)

            }
            ll_third.id->{
                setlabel(3)

            }
        }
    }

    private fun setlabel(i: Int) {
        when(i)
        {
            1->{

                labelValue = 1
                ll_first.setBackgroundColor(Color.parseColor("#CAC8C8"))
                ll_second.setBackgroundColor(Color.parseColor("#f2f2f2"))
                ll_third.setBackgroundColor(Color.parseColor("#f2f2f2"))

                labels[0] = true
                labels[1] = false
                labels[2] = false
            }
            2->{
                labelValue = 2

                ll_first.setBackgroundColor(Color.parseColor("#f2f2f2"))
                ll_second.setBackgroundColor(Color.parseColor("#CAC8C8"))
                ll_third.setBackgroundColor(Color.parseColor("#f2f2f2"))
                labels[0] = false
                labels[1] = true
                labels[2] = false
            }
            3->{
                labelValue = 3
                ll_first.setBackgroundColor(Color.parseColor("#f2f2f2"))
                ll_second.setBackgroundColor(Color.parseColor("#f2f2f2"))
                ll_third.setBackgroundColor(Color.parseColor("#CAC8C8"))
                labels[0] = false
                labels[1] = false
                labels[2] = true
            }

        }
    }

}

