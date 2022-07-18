package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.bscmsk.renttable.databinding.SpinnerMonthItemBinding
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.models.Month

class SpinnerMonthAdapter(
    context: Context,
    val list: List<Month>) :
    ArrayAdapter<Month>(context, 0, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val data = list[position]
        var retView = SpinnerMonthItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        retView.font.text = data.name
        return retView.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val data = list[position]
        val itemView = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val text = itemView.findViewById<TextView>(android.R.id.text1)
        text.text = data.name
        return itemView
    }
}