package ru.bscmsk.renttable.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.SpinnerCityItemBinding
import ru.bscmsk.renttable.presentation.models.CityPresentation
import java.text.FieldPosition


class SpinnerCityAdapter(
    context: Context,
    val list: List<CityPresentation>) :
    ArrayAdapter<CityPresentation>(context, 0, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup):View{
        val data = list[position]
        var retView = SpinnerCityItemBinding.inflate(
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