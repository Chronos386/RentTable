package ru.bscmsk.renttable.presentation.fragments.Rent

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.LoginDialogFragmentBinding
import ru.bscmsk.renttable.presentation.adapters.*
import ru.bscmsk.renttable.presentation.getMonth
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.models.DateWithPlace
import ru.bscmsk.renttable.presentation.models.Month
import ru.bscmsk.renttable.presentation.translateMonthtoInt

import java.time.LocalDate

class LoginDialogFragment: DialogFragment() {
    private lateinit var binding: LoginDialogFragmentBinding

    private val vm: RentViewModel by viewModels {
        RentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor,
            userInteractor = requireActivity().appComponent.userInteractor,
            rentInteractor = requireActivity().appComponent.rentInteractor
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View {
        binding = LoginDialogFragmentBinding.inflate(LayoutInflater.from(context),container,false)

        vm.getUserCityList() //надо получить список городов

        vm.getUser().observe(viewLifecycleOwner){
            binding.userName.text ="Пользователь: "+it.login.toString()
        }

        vm.UserCityListLive.observe(viewLifecycleOwner){
            setSpinner(it)
        }


        //Можешь LiveData не использовать


        binding.recyclerViewPlace.addItemDecoration(HorisontalSpaceItemDecoration(8))
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewPlace.layoutManager = linearLayoutManager

        binding.buttonexit.setOnClickListener{
            vm.ExitAccount()
        }

        vm.ExitAccountLive.observe(viewLifecycleOwner){
            (activity as MainActivity).gotoLoginFragment()
            this.dismiss()
        }

        return binding.root
    }

    fun setSpinner(citylist: List<CityPresentation>){

        val citylistAdapter = SpinnerCityAdapter(requireContext(),citylist)
        binding.spinnerCity.adapter = citylistAdapter

        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                getMonthes(city = parent!!.getItemAtPosition(position) as CityPresentation)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    fun getMonthes(city: CityPresentation) {
        vm.getUserData(city)
        vm.UserDataListLive.observe(viewLifecycleOwner){list->
            val newlist = list.sortedBy { it.date }
            val dates = ArrayList<LocalDate>()
            newlist.forEach {
                dates.add(it.date)
            }
            val monthes = getMonth(dates)
            val monthlistAdapter = SpinnerMonthAdapter(requireContext(),monthes)
            binding.spinnerMonth.adapter = monthlistAdapter

            binding.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                )
                {
                    val month = parent!!.getItemAtPosition(position) as Month
                    val MonthDate = newlist.filter{it.date.month.value == translateMonthtoInt(month.name)} as ArrayList<DateWithPlace>
                    val adapter = UserRentTablesAdapter(context = requireContext(),list = MonthDate , vm)
                    binding.recyclerViewPlace.adapter = adapter
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }
}