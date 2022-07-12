package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.fragments.Rent.ModelRentItem
import java.time.LocalDate


class DaysRentFewTableAdapter (
    private val context: Context,
    private val list: ArrayList<ModelRentItem>,
    private var recView1: RecyclerView,
    private var recView2: RecyclerView
    ):
    RecyclerView.Adapter<DaysRentFewTableAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var day = view.findViewById<TextView>(R.id.day) as TextView
        var date = view.findViewById<TextView>(R.id.date) as TextView
        var but = view.findViewById<Button>(R.id.button) as Button

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.reserved_seats_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]

        val russianWeeksName:List<String> = listOf("Пн","Вт","Ср","Чт","Пт","Сб","Вс")

        holder.day.text = russianWeeksName[data.day.dayOfWeek.value-1]
        holder.date.text = data.day.dayOfMonth.toString()
        holder.but.text = data.list[0]

        holder.but.setOnClickListener{
            val adapter = Tables2Adapter(context = context, list = data.list,hold = holder)
            if (position.mod(2) == 0){
                recView1.visibility = View.VISIBLE
                recView2.visibility = View.INVISIBLE
                recView1.scrollToPosition(holder.but.text.toString().toInt()-1)
                recView1.adapter = adapter
            }
            else
            {
                recView1.visibility = View.INVISIBLE
                recView2.visibility = View.VISIBLE
                recView2.scrollToPosition(holder.but.text.toString().toInt()-1)
                recView2.adapter = adapter
            }
        }
        holder.but.doOnTextChanged { text, start, before, count ->
            recView1.visibility = View.INVISIBLE
            recView2.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
