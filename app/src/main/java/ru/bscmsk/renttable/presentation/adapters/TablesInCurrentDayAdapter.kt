package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.models.Table

class TablesInCurrentDayAdapter(
    private val context: Context,
    private val list: List<Table>,
    private var hold:DaysRentFewTableAdapter.MyViewHolder
    ):
    RecyclerView.Adapter<TablesInCurrentDayAdapter.MyViewHolder>() {

    private var selectedItemPosition: Int = hold.but.text.toString().toInt()-1


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var table = view.findViewById<RelativeLayout>(R.id.table) as RelativeLayout
        var text = view.findViewById<TextView>(R.id.text) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.table2_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list.get(position)

        holder.text.text = data.number.toString()

        if (selectedItemPosition == position)
        {
            holder.text.setTextColor(Color.WHITE)
            holder.table.setBackgroundResource(R.drawable.table_style_push)
        }
        else
        {
            holder.table.setBackgroundResource(R.drawable.table_style_default)
            holder.text.setTextColor(Color.parseColor("#FF7558"))
        }

        holder.table.setOnClickListener {
            if (selectedItemPosition != position)
                hold.but.text = data.number.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}