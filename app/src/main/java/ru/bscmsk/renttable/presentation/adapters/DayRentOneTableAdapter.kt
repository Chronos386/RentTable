package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.fragments.Rent.RentViewModel
import java.time.LocalDate

class DayRentOneTableAdapter(
    private val context: Context,

    var list: ArrayList<LocalDate>,
    private val user: String,
    private val table: Int,
    vm: RentViewModel
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

        //getdayofWeek(day:string)
        //надо перевести день недели
        //Сам день недели писать сокращённо до 2 букв
        //Пример результата "Пн"

        holder.day.text = data.dayOfWeek.toString()
        holder.date.text = data.dayOfMonth.toString()
        holder.name.text = "ABOBA"//data.user


        if (holder.name.text == user)
        {
            holder.font.setBackgroundResource(R.drawable.reserved_days_user)
        }
        if (holder.name.text != ""){
            holder.font.setBackgroundResource(R.drawable.reversed_days)
        }
        else
        {
            holder.font.setOnClickListener(){

                //vm.savedata(data,table,user)
                //Так я передаю тебе день, стол и user-а который хочет это забить
                //Ты сохраняешь это на сервер
                //Мне возвращаешь получилоcь сохранить или нет

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}