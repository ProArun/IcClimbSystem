package com.arun.icclimbsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arun.icclimbsystem.R
import kotlinx.android.synthetic.main.item_day.view.*

class DayOfWeekAdapter(private val dayOfWeeks: List<String>) :
    RecyclerView.Adapter<DayOfWeekAdapter.DayOfWeekViewHolder>() {
    class DayOfWeekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayOfWeekViewHolder {
        return DayOfWeekViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_day,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DayOfWeekViewHolder, position: Int) {
        holder.itemView.tvDay.text = dayOfWeeks[position]
    }

    override fun getItemCount(): Int {
        return dayOfWeeks.size
    }
}