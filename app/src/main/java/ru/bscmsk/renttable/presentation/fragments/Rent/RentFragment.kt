package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.FragmentRentBinding
import ru.bscmsk.renttable.presentation.adapters.SpinnerCityAdapter
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.viewModels.CommonRentViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.CommonRentViewModelFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class RentFragment: Fragment() {
    private lateinit var binding: FragmentRentBinding
    private val vm: RentViewModel by viewModels {
        RentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor,
            userInteractor = requireActivity().appComponent.userInteractor,
            rentInteractor = requireActivity().appComponent.rentInteractor
        )
    }
    private val commonvm: CommonRentViewModel by activityViewModels {
        CommonRentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRentBinding.inflate(inflater, container, false)

        vm.getCityList()
        vm.CityListLive.observe(viewLifecycleOwner, Observer {
            setSpinner(it)
        })

        binding.userImage.setOnClickListener{
            val myDialogFragment = LoginDialogFragment()
            val manager = parentFragmentManager
            myDialogFragment.show(manager, "userDialog")

        }
        vm.ExitAccountLive.observe(viewLifecycleOwner){
            (activity as MainActivity).gotoLoginFragment()
        }

        return binding.root
    }
    fun setSpinner(citylist: List<CityPresentation>){

        val citylistAdapter = SpinnerCityAdapter(requireContext(),citylist)
        binding.spinnerCity.adapter = citylistAdapter

        vm.getCityFromDB().observe(viewLifecycleOwner) {
            val index:Int = citylist.indexOf(it)
            binding.spinnerCity.setSelection(index)
        }
        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                commonvm.rewriteCity(parent!!.getItemAtPosition(position) as CityPresentation)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

        }

    }


}

