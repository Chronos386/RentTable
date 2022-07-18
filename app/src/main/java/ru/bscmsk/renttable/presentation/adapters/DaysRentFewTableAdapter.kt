package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.GetDayofWeek
import ru.bscmsk.renttable.presentation.fragments.Rent.RentViewModel
import ru.bscmsk.renttable.presentation.models.RentFewTables
import ru.bscmsk.renttable.presentation.models.Table


class DaysRentFewTableAdapter (
    private val context: Context,
    private val list: List<RentFewTables>,
    private var recView1: RecyclerView,
    private var recView2: RecyclerView,
    var vm: RentViewModel
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
        holder.day.text = GetDayofWeek(data.Date)
        holder.date.text = data.Date.dayOfMonth.toString()
        holder.but.text = data.Tablelist[data.currentIndex].number.toString()

        holder.but.setOnClickListener{
            var linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            linearLayoutManager.scrollToPositionWithOffset(data.Tablelist.indexOf(Table(holder.but.text.toString().toInt())),0)
            val adapter = TablesInCurrentDayAdapter(context,list= data.Tablelist,hold = holder)
            if (position.mod(2) == 0){
                recView1.layoutManager = linearLayoutManager
                recView1.visibility = View.VISIBLE
                recView2.visibility = View.INVISIBLE
                recView1.adapter = adapter
            }
            else
            {
                recView2.layoutManager = linearLayoutManager
                recView1.visibility = View.INVISIBLE
                recView2.visibility = View.VISIBLE
                recView2.scrollToPosition(holder.but.text.toString().toInt()-1)
                recView2.adapter = adapter
            }
        }
        holder.but.doOnTextChanged { text, start, before, count ->
            recView1.visibility = View.INVISIBLE
            recView2.visibility = View.INVISIBLE
            vm.FreeTablesListLive.value!![vm.FreeTablesListLive.value!!.indexOf(list[position])].currentIndex =
                list[position].Tablelist.indexOf(data.Tablelist.filter
                { it.number == holder.but.text.toString().toInt()}[0])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
