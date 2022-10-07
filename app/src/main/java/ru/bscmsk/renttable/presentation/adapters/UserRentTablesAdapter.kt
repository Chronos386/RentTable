package ru.bscmsk.renttable.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.databinding.ReservedSeatsItemBinding
import ru.bscmsk.renttable.app.getDayOfWeek
import ru.bscmsk.renttable.presentation.interfaces.RentInterface
import ru.bscmsk.renttable.presentation.models.DateWithPlace

class UserRentTablesAdapter(
    private val list: ArrayList<DateWithPlace>,
    private var MyOnClick: RentInterface
) : RecyclerView.Adapter<UserRentTablesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: ReservedSeatsItemBinding) : RecyclerView.ViewHolder(view.root) {
        var day = view.day
        var date = view.date
        var but = view.button
        var font = view.item
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserRentTablesAdapter.MyViewHolder {
        val itemView =
            ReservedSeatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserRentTablesAdapter.MyViewHolder, position: Int) {
        val data = list[position]

        holder.date.text = data.date.dayOfMonth.toString()
        holder.day.text = getDayOfWeek(data.date)
        holder.but.text = data.place.toString()

        holder.font.setOnClickListener {
            MyOnClick.onDeleteClicked(position)
            list.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, list.size)

        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

}