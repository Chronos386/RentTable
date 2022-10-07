package ru.bscmsk.renttable.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.TableItemBinding
import ru.bscmsk.renttable.presentation.viewModels.RentViewModel
import ru.bscmsk.renttable.presentation.models.TablePresentation

class TablesAdapter(
    private val context: Context,
    private val list: List<TablePresentation>,
    private var index: Int,
    private var vm: RentViewModel
) :
    RecyclerView.Adapter<TablesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: TableItemBinding) : RecyclerView.ViewHolder(view.root) {
        var table = view.table
        var text = view.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = TableItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            LayoutInflater.from(context)
            .inflate(R.layout.table_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val data = list[position]
        holder.text.text = data.number.toString()
        if (index == position) {
            holder.text.setTextColor(Color.WHITE)
            holder.table.setBackgroundResource(R.drawable.table_style_push)
        } else {
            holder.table.setBackgroundResource(R.drawable.table_style_default)
            holder.text.setTextColor(Color.BLACK)
        }
        holder.table.setOnClickListener {
            val newIndex = holder.text.text.toString().toInt() - 1
            index = newIndex
            vm.changeTableIndex(newIndex)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}