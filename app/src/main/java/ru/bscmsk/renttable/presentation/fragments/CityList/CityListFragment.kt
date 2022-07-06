package ru.bscmsk.renttable.presentation.fragments.CityList


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.models.CityListDataModel
import ru.bscmsk.renttable.databinding.FragmentCitylistBinding
import javax.inject.Inject
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_citylist.*


class CityListFragment: Fragment() {
    private lateinit var binding: FragmentCitylistBinding

    lateinit var vmFactory: CityListViewModelFactory
    private lateinit var vm: CityListViewModel
    lateinit var citylist: List<CityDataModel>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCitylistBinding.inflate(inflater, container, false)

        vm = ViewModelProvider(this,vmFactory).get(CityListViewModel::class.java)
        vm.getList()//Видимо я тут делаю что-то не так, так как это вызывает проблемы

        vm.resultLive.observe(viewLifecycleOwner, Observer {
            citylist = it
            createList()
        })
        return binding.root
    }


    private fun createList() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        val adapter = CitiesAdapter(context = requireContext(),list = citylist,object : CityInterface {
            override fun onClicked(city: CityDataModel)
            {
                vm.getCityClick(city)
            }

        })
        recyclerView.adapter = adapter
    }
}