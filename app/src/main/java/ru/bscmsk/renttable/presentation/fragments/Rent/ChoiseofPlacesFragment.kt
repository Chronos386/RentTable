package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.databinding.FragmentChoiseofplacesBinding
import ru.bscmsk.renttable.presentation.adapters.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class ChoiseofPlacesFragment: Fragment() {
    private lateinit var binding: FragmentChoiseofplacesBinding

    lateinit var vmFactory: RentViewModelFactory
    private lateinit var vm: RentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiseofplacesBinding.inflate(inflater, container, false)
        val list:List<String> = listOf("1","2","3","4","5","6","7","8","9","10")

        var linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewPlace.layoutManager = linearLayoutManager
        binding.recyclerViewPlace.addItemDecoration(HorisontalSpaceItemDecoration(8))
        val adapter = TablesAdapter(context = requireContext(),list = list)
        binding.recyclerViewPlace.adapter = adapter


        var list2: ArrayList<LocalDate> = ArrayList()
        var date = LocalDate.of(2022,7,6)
        list2.add(date)
        date = LocalDate.of(2022,7,7)
        list2.add(date)
        date = LocalDate.of(2022,7,8)
        list2.add(date)
        date = LocalDate.of(2022,7,9)
        list2.add(date)
        date = LocalDate.of(2022,7,10)
        list2.add(date)
        date = LocalDate.of(2022,7,11)
        list2.add(date)
        date = LocalDate.of(2022,7,12)
        list2.add(date)


        linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewDay.layoutManager = linearLayoutManager
        binding.recyclerViewDay.addItemDecoration(VerticalSpaceItemDecoration(12))
        val adapter2 = DayRentOneTableAdapter(context = requireContext(),list = list2)
        binding.recyclerViewDay.adapter = adapter2

        return binding.root

    }
}