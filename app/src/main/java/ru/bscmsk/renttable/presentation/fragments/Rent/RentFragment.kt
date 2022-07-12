package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.FragmentRentBinding
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.presentation.fragments.BaseViewModel
import ru.bscmsk.renttable.presentation.fragments.BaseViewModelFactory

class RentFragment: Fragment() {
    private lateinit var binding: FragmentRentBinding
    val vmFactory: RentViewModelFactory = RentViewModelFactory()
    private lateinit var vm: RentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRentBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this,vmFactory).get(RentViewModel::class.java)
        //vm.getCityList() надо получить список городов

        vm.CityListLive.observe(viewLifecycleOwner, Observer {
            setSpinner(it)
        })

        binding.userImage.setOnClickListener{
            val myDialogFragment = LoginDialogFragment()
            val manager = parentFragmentManager
            myDialogFragment.show(manager, "userDialog")
        }

        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .add(binding.PlacesFragmentContainer.id, ChoiseofPlacesFragment())
                .commit()
        }



        return binding.root
    }

    fun createList(){


    }
    fun setSpinner(citylist: List<CityDataModel>){
        val list:ArrayList<String> = ArrayList<String>()
        citylist.forEach{
            it -> list.add(it.name)
        }
        val citilistAdapter: ArrayAdapter<String> = ArrayAdapter<String> (requireContext(),R.layout.spinner_city_item,list)
        citilistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCity.adapter = citilistAdapter
        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //vm.rewriteCity(city:String) Здесь надо будет перезаписать город В БД
                //Также нужна будет переменная, которая будет отслеживаться ChoiseOfPlacesFragment

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

}