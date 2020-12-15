package com.arun.icclimbsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arun.icclimbsystem.R
import com.arun.icclimbsystem.db.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val notes: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
/* var subCode: String = "",
    var subName: String = "",
    var faculty: String = "",
    var periodType: String = "",
    var subType: String = "",
    var classTime: Long = 0L,
    var classDate: Long = 0L*/
        holder.itemView.tvTime.text = notes[position].classTime.toString()
        holder.itemView.tvSubCode.text = notes[position].subCode
        holder.itemView.tvSubName.text = notes[position].subName
        holder.itemView.tvFacultyName.text = notes[position].faculty
        holder.itemView.tvPeriodType.text = notes[position].periodType
        holder.itemView.tvSubType.text = notes[position].subType
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}