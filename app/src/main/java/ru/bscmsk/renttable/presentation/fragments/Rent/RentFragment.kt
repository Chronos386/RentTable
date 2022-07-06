package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        Log.e("AAA","AB3")

        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .add(binding.PlacesFragmentContainer.id, ChoiseofPlacesFragment())
                .commit()
        }
        return binding.root
    }

}