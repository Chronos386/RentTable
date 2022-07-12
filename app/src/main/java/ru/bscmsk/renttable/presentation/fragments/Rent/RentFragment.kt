package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.FragmentRentBinding
import ru.bscmsk.renttable.presentation.fragments.CityList.CityListViewModel
import ru.bscmsk.renttable.presentation.fragments.CityList.CityListViewModelFactory

class RentFragment: Fragment() {
    private lateinit var binding: FragmentRentBinding

    lateinit var vmFactory: RentViewModelFactory
    private lateinit var vm: RentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRentBinding.inflate(inflater, container, false)

        val list:List<String>  = listOf("Москва","Санкт-Петербург","Белгород")
        val citilistAdapter: ArrayAdapter<String> = ArrayAdapter<String> (requireContext(),R.layout.spinner_city_item,list)
        citilistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCity.adapter = citilistAdapter

        binding.userImage.setOnClickListener{
            val myDialogFragment = LoginDialogFragment()
            val manager = parentFragmentManager
            myDialogFragment.show(manager, "userDialog")
        }

        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .add(binding.PlacesFragmentContainer.id, ChoiseofDaysFragment())
                .commit()
        }



        return binding.root
    }

}