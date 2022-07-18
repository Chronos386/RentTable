package ru.bscmsk.renttable.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.fragments.Rent.RentViewModel
import ru.bscmsk.renttable.presentation.models.Table

class TablesAdapter(
    private val context: Context,
    private val list: List<Table>,
    private var index: Int,
    private var vm: RentViewModel
    ):
    RecyclerView.Adapter<TablesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var table = view.findViewById<RelativeLayout>(R.id.table) as RelativeLayout
        var text = view.findViewById<TextView>(R.id.text) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.table_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val data = list.get(position)

        holder.text.text = data.number.toString()

        if (index == position)
        {
            holder.text.setTextColor(Color.WHITE)
            holder.table.setBackgroundResource(R.drawable.table_style_push)
        }
        else
        {
            holder.table.setBackgroundResource(R.drawable.table_style_default)
            holder.text.setTextColor(Color.BLACK)
        }

        holder.table.setOnClickListener {
            val newindex = holder.text.text.toString().toInt()-1
            index = newindex
            vm.changeIndex(newindex)

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}