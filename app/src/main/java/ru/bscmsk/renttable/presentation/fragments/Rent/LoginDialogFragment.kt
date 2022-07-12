package ru.bscmsk.renttable.presentation.fragments.Rent

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.databinding.LoginDialogFragmentBinding
import ru.bscmsk.renttable.presentation.adapters.DaysRentFewTableAdapter
import ru.bscmsk.renttable.presentation.adapters.HorisontalSpaceItemDecoration
import ru.bscmsk.renttable.presentation.adapters.TablesAdapter
import java.time.LocalDate

class LoginDialogFragment: DialogFragment() {
    private lateinit var binding: LoginDialogFragmentBinding

    lateinit var vmFactory: RentViewModelFactory
    private lateinit var vm: RentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View {
        binding = LoginDialogFragmentBinding.inflate(LayoutInflater.from(context))

        binding.UserName.text ="Пользователь: Зубенко Михаил Петрович"

        var list: ArrayList<LocalDate> = ArrayList()
        var date = LocalDate.of(2022,7,6)
        list.add(date)
        date = LocalDate.of(2022,7,7)
        list.add(date)
        date = LocalDate.of(2022,7,8)
        list.add(date)
        date = LocalDate.of(2022,7,9)
        list.add(date)
        date = LocalDate.of(2022,7,10)
        list.add(date)
        date = LocalDate.of(2022,7,11)
        list.add(date)
        date = LocalDate.of(2022,7,12)
        list.add(date)


        var linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewPlace.layoutManager = linearLayoutManager
        binding.recyclerViewPlace.addItemDecoration(HorisontalSpaceItemDecoration(10))
        //val adapter = DaysRentFewTableAdapter(context = requireContext(),list = list)
        //binding.recyclerViewPlace.adapter = adapter

        return binding.root
    }


}