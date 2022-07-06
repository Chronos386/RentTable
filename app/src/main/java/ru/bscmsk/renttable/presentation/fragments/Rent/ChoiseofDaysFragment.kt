package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.databinding.FragmentChoiseofdaysBinding
import ru.bscmsk.renttable.presentation.adapters.DaysRentFewTableAdapter
import ru.bscmsk.renttable.presentation.adapters.HorisontalSpaceItemDecoration
import ru.bscmsk.renttable.presentation.adapters.TablesAdapter
import ru.bscmsk.renttable.presentation.adapters.VerticalSpaceItemDecoration
import java.time.LocalDate

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



        var gridLayoutManager = GridLayoutManager(requireContext(), 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.addItemDecoration(HorisontalSpaceItemDecoration(15))
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(10))
        val adapter = DaysRentFewTableAdapter(context = requireContext(),list = list2)
        binding.recyclerView.adapter = adapter



        return binding.root
    }
}
