package com.arun.icclimbsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arun.icclimbsystem.R
import kotlinx.android.synthetic.main.item_date.view.*

class DayOfMonthAdapter(private val dayOfMonths: List<Int>) :
    RecyclerView.Adapter<DayOfMonthAdapter.DayOfMonthViewHolder>() {

    class DayOfMonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayOfMonthViewHolder {
        return DayOfMonthViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_date,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DayOfMonthViewHolder, position: Int) {
        holder.itemView.tvDate.text = dayOfMonths[position].toString()
    }

    override fun getItemCount(): Int {
        return dayOfMonths.size
    }
}