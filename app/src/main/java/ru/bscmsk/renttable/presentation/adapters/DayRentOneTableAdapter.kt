package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import java.time.LocalDate

class DayRentOneTableAdapter(
    private val context: Context,
    private val list: ArrayList<LocalDate>,

):
    RecyclerView.Adapter<DayRentOneTableAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var day = view.findViewById<TextView>(R.id.day) as TextView
        var date = view.findViewById<TextView>(R.id.date) as TextView
        var name = view.findViewById<TextView>(R.id.name) as TextView
        var font = view.findViewById<ConstraintLayout>(R.id.item) as ConstraintLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.reserved_days_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.day.text = data.dayOfWeek.toString()
        holder.date.text = data.dayOfMonth.toString()
        holder.name.text = "Свободен"

        if (holder.name.text == "Свободен")
        {
            holder.font.setBackgroundResource(R.drawable.reserved_days_push)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}