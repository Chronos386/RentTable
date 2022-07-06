package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import java.time.LocalDate


class DaysRentFewTableAdapter (private val context: Context, private val list: ArrayList<LocalDate>):
    RecyclerView.Adapter<DaysRentFewTableAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var day = view.findViewById<TextView>(R.id.day) as TextView
        var date = view.findViewById<TextView>(R.id.date) as TextView
        //var but = view.findViewById<Button>(R.id.button) as Button

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.suggested_place_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.day.text = data.dayOfWeek.toString()
        holder.date.text = data.dayOfMonth.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }

}