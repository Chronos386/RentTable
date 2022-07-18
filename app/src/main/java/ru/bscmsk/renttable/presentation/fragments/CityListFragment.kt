package ru.bscmsk.renttable.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.FragmentCitylistBinding
import ru.bscmsk.renttable.presentation.adapters.CitiesAdapter
import ru.bscmsk.renttable.presentation.interfaces.CityInterface
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.viewModels.CityListViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.CityListViewModelFactory


class CityListFragment : Fragment() {
    private lateinit var binding: FragmentCitylistBinding
    private var cityList = ArrayList<CityPresentation>()
    private val viewModel: CityListViewModel by viewModels {
        CityListViewModelFactory(
            requireActivity().appComponent.cityInteractor,
            requireActivity().appComponent.userInteractor
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCitylistBinding.inflate(inflater, container, false)
        viewModel.getCities()
        viewModel.resultLive.observe(viewLifecycleOwner) {
            cityList = it
            createList()
        }

        viewModel.gotoRentLive.observe(viewLifecycleOwner){
            findNavController().navigate(CityListFragmentDirections.actionCityListFragmentToRentFragment())
        }

        viewModel.ExitAccountLive.observe(viewLifecycleOwner){
            (activity as MainActivity).gotoLoginFragment()
        }
        return binding.root
    }

    private fun createList() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager
        val adapter = CitiesAdapter(context = requireContext(), list = cityList, object :
            CityInterface {
            override fun onClicked(city: CityPresentation) {
                viewModel.getCityClick(city)
            }
        })
        binding.recyclerView.adapter = adapter
    }
}