package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.FragmentChoiseofdaysBinding
import ru.bscmsk.renttable.presentation.adapters.DaysRentFewTableAdapter
import ru.bscmsk.renttable.presentation.adapters.HorisontalSpaceItemDecoration
import ru.bscmsk.renttable.presentation.adapters.TablesAdapter
import ru.bscmsk.renttable.presentation.adapters.VerticalSpaceItemDecoration
import java.time.LocalDate


data class ModelRentItem (
    val day:LocalDate,
    val list: List<String>
)




class ChoiseofDaysFragment: Fragment() {
    private lateinit var binding: FragmentChoiseofdaysBinding

    lateinit var vmFactory: RentViewModelFactory
    private lateinit var vm: RentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiseofdaysBinding.inflate(inflater, container, false)
        val russianMonthName:List<String> = listOf(
                "Январь","Февраль","Март","Апрель",
                "Май","Июнь","Июль","Август",
                "Сентябрь","Октябрь","Ноябрь","Декабрь" )

        var list: ArrayList<ModelRentItem> = ArrayList()
        val listofplaces:List<String> = listOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25")
        var date = ModelRentItem(LocalDate.of(2022,7,6),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,7),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,8),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,9),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,10),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,11),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,12),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,13),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,14),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,15),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,16),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,17),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,18),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,19),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,8,20),listofplaces)
        list.add(date)


        val russianmonthList:ArrayList<String> = ArrayList<String>()
        russianmonthList.let{ it->
            list.distinctBy {item -> item.day.month }.forEach {
                item ->it.add(russianMonthName[item.day.month.value])
            }
        }



            val list3:List<String>  = listOf("Июль","Август")
            val monthlistAdapter: ArrayAdapter<String> = ArrayAdapter<String> (requireContext(),
                R.layout.spinner_month_item,list3)
            monthlistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerMonth.adapter = monthlistAdapter


            val linearLayoutManagerdop1 = LinearLayoutManager(context)
            linearLayoutManagerdop1.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerViewBonus1.layoutManager = linearLayoutManagerdop1
            binding.recyclerViewBonus1.addItemDecoration(VerticalSpaceItemDecoration(11))


            val linearLayoutManagerdop2 = LinearLayoutManager(context)
            linearLayoutManagerdop2.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerViewBonus2.layoutManager = linearLayoutManagerdop2
            binding.recyclerViewBonus2.addItemDecoration(VerticalSpaceItemDecoration(11))


            var linearLayoutManager = GridLayoutManager(context,2)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerView.layoutManager = linearLayoutManager
            binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(22))
            binding.recyclerView.addItemDecoration(HorisontalSpaceItemDecoration(15))
            val adapter = DaysRentFewTableAdapter(context = requireContext(),list = list, recView1 = binding.recyclerViewBonus1, recView2 = binding.recyclerViewBonus2)
            binding.recyclerView.adapter = adapter

            return binding.root



    }
}
