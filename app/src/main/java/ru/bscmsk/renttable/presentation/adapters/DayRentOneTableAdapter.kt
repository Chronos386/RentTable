package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.GetDayofWeek
import ru.bscmsk.renttable.presentation.fragments.Rent.RentViewModel
import ru.bscmsk.renttable.presentation.interfaces.CityInterface
import ru.bscmsk.renttable.presentation.interfaces.RentInterface
import ru.bscmsk.renttable.presentation.models.*
import java.time.LocalDate
import java.time.MonthDay

class DayRentOneTableAdapter(
    private val context: Context,
    var list: ArrayList<RentOneTable>,
    private val user: UserPresentation,
    private val table: Table,
    private val city: CityPresentation,
    private val myOnClick: RentInterface
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

        holder.day.text = GetDayofWeek(data.Date)
        holder.date.text = data.Date.dayOfMonth.toString()
        holder.name.text = data.login


        if (data.login == user.login)
        {
            holder.font.setBackgroundResource(R.drawable.reserved_days_user)
            holder.font.setOnClickListener(){
                val DataforSaving = ArrayList<DateWithPlace>()
                DataforSaving.add(DateWithPlace(data.Date,table.number))
            }
        }
        else
        {
            if (holder.name.text != ""){
                holder.font.setBackgroundResource(R.drawable.reversed_days)
            }
            else
            {
                holder.font.setBackgroundResource(R.drawable.table_style_default)
                holder.font.setOnClickListener(){
                    val DataforSaving = ArrayList<DateWithPlace>()
                    DataforSaving.add(DateWithPlace(data.Date,table.number))
                    myOnClick.onClicked(NewBookingPresentation(region = city.name, datesWithPlaces = DataforSaving))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}