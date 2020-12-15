package com.arun.icclimbsystem.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arun.icclimbsystem.R
import com.arun.icclimbsystem.ui.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_class.*
import java.util.*
import java.util.concurrent.TimeUnit

class AddClassFragment : Fragment(R.layout.fragment_add_class) {

    lateinit var viewModel: NoteViewModel
    var dateInMillis: Long? = null
    var timeInMillis: Long? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        tvClassTime.setOnClickListener {
            handleTimeButton()
        }
        tvClassDate.setOnClickListener {
            handleDateButton()
        }

        btnAddNote.setOnClickListener {
            viewModel.insertNote(
                etSubCode.text.toString(),
                etSubName.text.toString(),
                etPeriodType.text.toString(),
                etSubType.text.toString(),
                timeInMillis,
                dateInMillis
            )
        }
    }

    private fun handleTimeButton() {
        val cal = Calendar.getInstance()

        val hour = cal.get(Calendar.HOUR)
        val minute = cal.get(Calendar.MINUTE)

        val dialog = TimePickerDialog(
            requireContext(),
            { _, _hour: Int, _minute: Int ->
                val timeString = "$_hour : $_minute"
                timeInMillis = getTimeInMillis(_hour, _minute)
                tvClassTime.text = timeString
            },
            hour, minute, true
        )
        dialog.show()

    }

    private fun getTimeInMillis(_hour: Int, _minute: Int): Long {
        val hour = _hour * 60 * 60
        val minute = _minute * 60
        val timeInSec:Long = (hour + minute).toLong()
        return TimeUnit.SECONDS.toMillis(timeInSec)
    }

    private fun handleDateButton() {
        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(
            requireContext(),
            { _, _year: Int, _month: Int, _day: Int ->
                val date = "$_month/$_day/$_year"
                dateInMillis = getDateInMillis(_year, _month, _day)
                tvClassDate.text = date
            },
            year, month, day
        )
        dialog.show()
    }

    private fun getDateInMillis(_year: Int, _month: Int, _day: Int): Long {
        val cal = Calendar.getInstance()
        cal.set(_year, _month, _day)
        return cal.timeInMillis
    }

    private fun subscribeToObservers() {

    }

}