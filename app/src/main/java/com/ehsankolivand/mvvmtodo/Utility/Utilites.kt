package com.ehsankolivand.mvvmtodo.Utility

import android.graphics.Color

class Utilites {

    fun getColor(value: Int):Int{
        when(value)
        {
            1-> return Color.parseColor("#FFAED581")
            2-> return Color.parseColor("#FFDCE775")
            3-> return Color.parseColor("#FFE57373")
        }
        return Color.parseColor("#FFAED581")
    }
}