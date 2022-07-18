package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.FragmentChoiseofplacesBinding
import ru.bscmsk.renttable.presentation.adapters.*
import ru.bscmsk.renttable.presentation.getMonth
import ru.bscmsk.renttable.presentation.interfaces.*
import ru.bscmsk.renttable.presentation.models.*
import ru.bscmsk.renttable.presentation.translateMonthtoInt
import ru.bscmsk.renttable.presentation.viewModels.CommonRentViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.CommonRentViewModelFactory
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class ChoiseofPlacesFragment: Fragment() {

    private val viewModel: RentViewModel by viewModels {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var user = UserPresentation("", "")
        var index = -1

        viewModel.getUser().observe(viewLifecycleOwner) {
            user = it
        }

        viewModel.ExitAccountLive.observe(viewLifecycleOwner){
            (activity as MainActivity).gotoLoginFragment()
        }


        val binding = FragmentChoiseofplacesBinding.inflate(inflater, container, false)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewPlace.layoutManager = linearLayoutManager
        binding.recyclerViewPlace.addItemDecoration(HorisontalSpaceItemDecoration(8))

        val linearLayoutManager2 = LinearLayoutManager(requireContext())
        linearLayoutManager2.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewDay.layoutManager = linearLayoutManager2
        binding.recyclerViewDay.addItemDecoration(VerticalSpaceItemDecoration(12))

        binding.button.setOnClickListener() {
            findNavController().navigate(ChoiseofPlacesFragmentDirections.actionChoiseofPlacesFragmentToChoiseofDaysFragment())
        }

        commonvm.NewCityLive.observe(viewLifecycleOwner)
        { city ->
            val list = viewModel.getTables(city)
            viewModel.CurrentTableIndexLive.observe(viewLifecycleOwner) {
                index = it
                val table = Table(it + 1)
                val dates = ArrayList<LocalDate>()
                var date = LocalDate.now()
                while (date <= LocalDate.now().plusDays(30)) {
                    dates.add(date)
                    date = date.plusDays(1)
                }
                val monthes = getMonth(dates)
                val monthlistAdapter = SpinnerMonthAdapter(requireContext(), monthes)
                binding.spinnerMonth.adapter = monthlistAdapter

                binding.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
                {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val month = parent!!.getItemAtPosition(position) as Month
                        viewModel.getDaysListWithOneTable(city, table, month)
                        viewModel.RentOneTableListLive.observe(viewLifecycleOwner) {MonthDate->


                            val adapter = DayRentOneTableAdapter(
                                context = requireContext(),
                                list = MonthDate as ArrayList<RentOneTable>,
                                user = user,
                                table = table,
                                city = city,
                                object : RentInterface {
                                    override fun onClicked(datalist: NewBookingPresentation) {
                                        viewModel.rentListTables(datalist)
                                        viewModel.saveSuccessLive.observe(viewLifecycleOwner) {
                                            if (it) {
                                                val toast = Toast.makeText(
                                                    requireContext(),
                                                    "Успешно сохранено",
                                                    Toast.LENGTH_LONG
                                                )
                                                toast.show()
                                            } else {
                                                val toast = Toast.makeText(
                                                    requireContext(),
                                                    "Сохранение не удалось. Обновляем данные",
                                                    Toast.LENGTH_LONG
                                                )
                                                toast.show()
                                            }
                                            viewModel.getDaysListWithOneTable(city, table,month)
                                        }

                                    }
                                })
                            binding.recyclerViewDay.adapter = adapter
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }
            }
            val adapter = TablesAdapter(context = requireContext(), list = list, index,viewModel)
            binding.recyclerViewPlace.adapter = adapter
        }
        return binding.root
    }
}