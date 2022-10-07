package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.app.getDayOfWeek
import ru.bscmsk.renttable.databinding.ReservedSeatsItemBinding
import ru.bscmsk.renttable.presentation.viewModels.RentViewModel
import ru.bscmsk.renttable.presentation.models.RentFewTables
import ru.bscmsk.renttable.presentation.models.TablePresentation


class DaysRentFewTableAdapter(
    private val context: Context,
    private val list: List<RentFewTables>,
    private var recView1: RecyclerView,
    private var recView2: RecyclerView,
    var vm: RentViewModel
) :
    RecyclerView.Adapter<DaysRentFewTableAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: ReservedSeatsItemBinding) : RecyclerView.ViewHolder(view.root) {
        var day = view.day
        var date = view.date
        var but = view.button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ReservedSeatsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.day.text = getDayOfWeek(data.date)
        holder.date.text = data.date.dayOfMonth.toString()
        holder.but.text = data.tableList[data.currentIndex].number.toString()

        holder.but.setOnClickListener {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            linearLayoutManager.scrollToPositionWithOffset(
                data.tableList.indexOf(
                    TablePresentation(
                        holder.but.text.toString().toInt()
                    )
                ),
                0
            )
            val adapter = TablesInCurrentDayAdapter(list = data.tableList, hold = holder)
            if (position.mod(2) == 0) {
                recView1.layoutManager = linearLayoutManager
                recView1.visibility = View.VISIBLE
                recView2.visibility = View.INVISIBLE
                recView1.adapter = adapter
            } else {
                recView2.layoutManager = linearLayoutManager
                recView1.visibility = View.INVISIBLE
                recView2.visibility = View.VISIBLE
                recView2.scrollToPosition(holder.but.text.toString().toInt() - 1)
                recView2.adapter = adapter
            }
        }
        holder.but.doOnTextChanged { _, _, _, _ ->
            recView1.visibility = View.INVISIBLE
            recView2.visibility = View.INVISIBLE
            vm.changeDataIndex(
                data.date,
                data.tableList.indexOf(data.tableList.filter { it.number == holder.but.text.toString().toInt() }[0]))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
