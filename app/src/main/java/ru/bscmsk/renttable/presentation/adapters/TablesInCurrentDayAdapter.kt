package ru.bscmsk.renttable.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.TableInCurrentDayItemBinding
import ru.bscmsk.renttable.presentation.models.TablePresentation

class TablesInCurrentDayAdapter(
    private val list: List<TablePresentation>,
    private var hold: DaysRentFewTableAdapter.MyViewHolder
) :
    RecyclerView.Adapter<TablesInCurrentDayAdapter.MyViewHolder>() {
    private var selectedItemPosition: Int = hold.but.text.toString().toInt() - 1

    inner class MyViewHolder(view: TableInCurrentDayItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        var table = view.table
        var text = view.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            TableInCurrentDayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.text.text = data.number.toString()
        if (selectedItemPosition == position) {
            holder.text.setTextColor(Color.WHITE)
            holder.table.setBackgroundResource(R.drawable.table_style_push)
        } else {
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