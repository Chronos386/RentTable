package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.FragmentChoiseofplacesBinding

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
        Log.e("AAA","AB4")


        return binding.root

    }
}