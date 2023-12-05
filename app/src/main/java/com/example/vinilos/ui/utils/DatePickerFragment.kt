package com.example.vinilos.ui.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.vinilos.MainActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DatePickerFragment (val listener: ( year:Int, month:Int, day:Int) -> Unit): DialogFragment(),
DatePickerDialog.OnDateSetListener{


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // default date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // return new DatePickerDialog instance
        return DatePickerDialog(MainActivity as Context, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth,month,year)
        /*calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(calendar.time)

        val selectedDateBundle = Bundle()
        selectedDateBundle.putString("SELECTED_DATE", selectedDate)*/
        //show()
    }

}