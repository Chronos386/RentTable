package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.databinding.ReservedSeatsItemBinding
import ru.bscmsk.renttable.presentation.fragments.Rent.RentViewModel
import java.time.LocalDate

class UserRentTablesAdapter(
    private val context: Context,
    private val list: ArrayList<LocalDate>,
    val user: String,
    var vm: RentViewModel
): RecyclerView.Adapter<UserRentTablesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: ReservedSeatsItemBinding) : RecyclerView.ViewHolder(view.root) {
        var day = view.day
        var date = view.date
        var but = view.button
        var font = view.item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRentTablesAdapter.MyViewHolder {
        val itemView = ReservedSeatsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserRentTablesAdapter.MyViewHolder, position: Int) {
        val data = list[position]

        holder.date.text = data.dayOfMonth.toString()
        holder.day.text = data.dayOfWeek.toString()
        holder.but.text = "0"

        holder.font.setOnClickListener(){
            //vm.deleterent(date,user,city)
            //Надо удалить запись Юзера по этой дате (можно ещё передать table если тебе так удобнее)

            list.removeAt(holder.getAdapterPosition())
            notifyItemRemoved(holder.getAdapterPosition())
            notifyItemRangeChanged(holder.getAdapterPosition(),list.size)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

}