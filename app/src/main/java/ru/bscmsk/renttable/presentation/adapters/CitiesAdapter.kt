package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.presentation.fragments.CityList.CityInterface

class CitiesAdapter(private val context: Context, private val list: List<CityDataModel>,
                    val MyonClick: CityInterface):
    RecyclerView.Adapter<CitiesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.city_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("AAA","hui4")
        val data = list.get(position)
        val but = holder.itemView.findViewById<Button>(R.id.button)
        but.text= data.name
        but.setOnClickListener {
            MyonClick.onClicked(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}