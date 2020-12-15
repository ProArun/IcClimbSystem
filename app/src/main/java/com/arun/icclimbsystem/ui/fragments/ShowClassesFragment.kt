package com.arun.icclimbsystem.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arun.icclimbsystem.R
import com.arun.icclimbsystem.adapters.DayOfMonthAdapter
import com.arun.icclimbsystem.adapters.DayOfWeekAdapter
import com.arun.icclimbsystem.adapters.NoteAdapter
import com.arun.icclimbsystem.db.Note
import com.arun.icclimbsystem.ui.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.fragment_show_classes.*
import java.util.*


class ShowClassesFragment : Fragment(R.layout.fragment_show_classes) {

    private val montList = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )
    private val dayList = listOf(
        "WED",
        "THU",
        "FRI",
        "SAT",
        "SUN",
        "MON",
        "TUE"
    )
    private var daysOfCurrentWeek = mutableListOf<Int>()
    private var noteList = mutableListOf<Note>()

    val note1: Note = Note(
        "B19EE3060", "Microcontrollers and Applications", "Arun Aditya",
        "Regular", "Theory", 830, 1252020
    )
    val note2: Note = Note(
        "B19EE3060", "Microcontrollers and Applications", "Arun Aditya",
        "Regular", "Theory", 830, 1252020
    )
    val note3: Note = Note(
        "B19EE3060", "Microcontrollers and Applications", "Arun Aditya",
        "Regular", "Theory", 830, 1252020
    )
    val note4: Note = Note(
        "B19EE3060", "Microcontrollers and Applications", "Arun Aditya",
        "Regular", "Theory", 830, 1252020
    )
    val note5: Note = Note(
        "B19EE3060", "Microcontrollers and Applications", "Arun Aditya",
        "Regular", "Theory", 830, 1252020
    )


    private val viewModel: NoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteList.add(note1)
        noteList.add(note2)
        noteList.add(note3)
        noteList.add(note4)
        noteList.add(note5)
        setupNoteRecyclerView(noteList)
        val cuCal = Calendar.getInstance()
        val cuDayOfMonth = cuCal.get(Calendar.DAY_OF_MONTH)
        val cuDayOfWeek = cuCal.get(Calendar.DAY_OF_WEEK)
        val cuMonth = cuCal.get(Calendar.MONTH)
        val cuYear = cuCal.get(Calendar.YEAR)
        val cuDate = "${montList[cuMonth]}($cuYear)"
        tvSelectDate.text = cuDate

        /*
        * Position of selected day is cuDayOfWeek*/
        setupDayOfWeekRecyclerView()
        //setupDayOfMonthRecyclerView(cuCal)
        tvSelectDate.setOnClickListener {
            val cal = Calendar.getInstance()

            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                requireContext(),
                { _, _year: Int, _month: Int, _day: Int ->
                    val date = "${montList[_month]}($_year)"
                    tvSelectDate.text = date
                },
                year, month, day
            )
            dialog.show()
        }

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_showClassesFragment_to_addClassFragment)
        }
    }

    private fun setupNoteRecyclerView(noteList: MutableList<Note>) {
        rvNotes.apply {
            adapter = NoteAdapter(noteList)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupDayOfMonthRecyclerView(cuCal: Calendar) {
        val dayOfWeek = cuCal.get(Calendar.DAY_OF_WEEK)
        val cuDate = cuCal.get(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek: Calendar = returnFirstDayOfWeek(cuCal)

        for (i in 1..7) {
            val nextDay = firstDayOfWeek.add(Calendar.DAY_OF_MONTH, i) as Int
            daysOfCurrentWeek.add(nextDay)
        }
        rvDate.apply {
            adapter = DayOfMonthAdapter(daysOfCurrentWeek)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun returnFirstDayOfWeek(cuCal: Calendar): Calendar {
        val month = cuCal.get(Calendar.MONTH)
        val year = cuCal.get(Calendar.YEAR)

        val dayOfWeek = cuCal.get(Calendar.DAY_OF_WEEK)
        val cuDate = cuCal.get(Calendar.DAY_OF_MONTH)

        val leapYear: Boolean = checkLeapYear(year)

        var current: Int? = null
        var next: Int? = null
        var previous: Int? = null

        when (month) {
            0 -> {
                current = 31
                next = if (leapYear) {
                    29
                } else {
                    28
                }
                previous = 31
            }
            1 -> {
                current = if (leapYear) {
                    29
                } else {
                    28
                }
                next = 31
                previous = 31
            }
            2 -> {
                current = 31
                next = 31
                previous = if (leapYear) {
                    29
                } else {
                    28
                }
            }
            3 -> {
                current = 30
                next = 31
                previous = 31
            }
            4 -> {
                current = 31
                next = 30
                previous = 30
            }
            5 -> {
                current = 30
                next = 31
                previous = 31
            }
            6 -> {
                current = 31
                next = 31
                previous = 30
            }
            7 -> {
                current = 31
                next = 30
                previous = 31
            }
            8 -> {
                current = 30
                next = 31
                previous = 31
            }
            9 -> {
                current = 31
                next = 30
                previous = 30
            }
            10 -> {
                current = 30
                next = 31
                previous = 31
            }
            11 -> {
                current = 31
                next = 31
                previous = 30
            }
        }
        var firstDay: Int? = null
        var firstMonth: Int? = null
        var firstYear: Int? = null

        if (cuDate < dayOfWeek) {
            val rem = dayOfWeek - cuDate
            firstMonth = month - 1
            firstYear = year
            if (previous != null) {
                firstDay = previous - rem
            }
            if (month == 0) {
                firstMonth = 11
                firstYear = year - 1
            }
        }

        val firstCal = Calendar.getInstance()
        firstCal.set(firstYear!!, firstMonth!!, firstDay!!)
        return firstCal
    }

    private fun checkLeapYear(year: Int): Boolean {
        return if (year % 4 == 0) {
            if (year % 100 == 0) {
                year % 400 == 0
            } else
                true
        } else
            false
    }

    private fun setupDayOfWeekRecyclerView() {
        rvDay.apply {
            adapter = DayOfWeekAdapter(dayList)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }
    }

}