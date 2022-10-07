package ru.bscmsk.renttable.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.getDayOfWeek
import ru.bscmsk.renttable.databinding.ReservedDaysItemBinding
import ru.bscmsk.renttable.presentation.interfaces.RentInterface
import ru.bscmsk.renttable.presentation.models.*

class DayRentOneTableAdapter(
    var list: List<RentOneTable>,
    private val user: UserPresentation,
    private val userData: ArrayList<DateWithPlace>,
    private val myOnClick: RentInterface
) :
    RecyclerView.Adapter<DayRentOneTableAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: ReservedDaysItemBinding) : RecyclerView.ViewHolder(view.root) {
        var day = view.day
        var date = view.date
        var name = view.name
        var font = view.item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            ReservedDaysItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.day.text = getDayOfWeek(data.date)
        holder.date.text = data.date.dayOfMonth.toString()


        if (data.login != "") {
            holder.name.text = data.login
            if (data.login == user.login) {
                holder.font.setBackgroundResource(R.drawable.reserved_days_user)
                holder.font.setOnClickListener {
                    myOnClick.onDeleteClicked(
                        position = position,
                    )
                    notifyDataSetChanged()
                }
            } else {
                holder.font.setBackgroundResource(R.drawable.reserved_days)
            }
        } else {
            holder.name.text = "Свободно"
            if (userData.none { it.date == data.date }) {
                holder.font.setBackgroundResource(R.drawable.table_style_default)
                holder.font.setOnClickListener {
                    myOnClick.onAddClicked(
                        position = position,
                    )
                    notifyDataSetChanged()
                }
            } else {
                holder.font.setBackgroundResource(R.drawable.reversed_days_block)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}