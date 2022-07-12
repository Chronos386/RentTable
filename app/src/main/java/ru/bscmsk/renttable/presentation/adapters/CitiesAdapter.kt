package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.interfaces.CityInterface
import ru.bscmsk.renttable.presentation.models.CityPresentation

class CitiesAdapter(
    private val context: Context,
    private val list: List<CityPresentation>,
    private val myOnClick: CityInterface
):
    RecyclerView.Adapter<CitiesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.city_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        val but = holder.itemView.findViewById<TextView>(R.id.button)
        but.text= data.name
        but.setOnClickListener {
            myOnClick.onClicked(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}