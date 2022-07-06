package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.bscmsk.renttable.databinding.FragmentChoicedaysperiodBinding



class ChoiseDayPeriodFragment: Fragment() {
    private lateinit var binding: FragmentChoicedaysperiodBinding

    lateinit var vmFactory: RentViewModelFactory
    private lateinit var vm: RentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoicedaysperiodBinding.inflate(inflater, container, false)
        return binding.root
    }
}