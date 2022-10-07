package ru.bscmsk.renttable.presentation.fragments.rent

import android.os.Bundle
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
import ru.bscmsk.renttable.app.getMonth
import ru.bscmsk.renttable.app.translateMonthToString
import ru.bscmsk.renttable.presentation.interfaces.*
import ru.bscmsk.renttable.presentation.models.*
import ru.bscmsk.renttable.presentation.viewModels.CommonRentViewModel
import ru.bscmsk.renttable.presentation.viewModels.RentViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.CommonRentViewModelFactory
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.RentViewModelFactory
import java.time.LocalDate
import kotlin.collections.ArrayList

class ChoiceOfPlacesFragment : Fragment() {
    private val viewModel: RentViewModel by viewModels {
        RentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor,
            userInteractor = requireActivity().appComponent.userInteractor,
            rentInteractor = requireActivity().appComponent.rentInteractor
        )
    }

    private val commonViewModel: CommonRentViewModel by activityViewModels {
        CommonRentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.hide()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val user = viewModel.getUser()
        var index = -1
        viewModel.exitAccountLive.observe(viewLifecycleOwner) {
            (activity as MainActivity).gotoLoginFragment()
        }
        val binding = FragmentChoiseofplacesBinding.inflate(inflater, container, false)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.progressBar.visibility = View.INVISIBLE
        binding.recyclerViewPlace.layoutManager = linearLayoutManager
        binding.recyclerViewPlace.addItemDecoration(HorizontalSpaceItemDecoration(8 * requireContext().resources.displayMetrics.density))

        val linearLayoutManager2 = LinearLayoutManager(requireContext())
        linearLayoutManager2.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewDay.layoutManager = linearLayoutManager2
        binding.recyclerViewDay.addItemDecoration(VerticalSpaceItemDecoration(12 * requireContext().resources.displayMetrics.density))

        binding.button.setOnClickListener {
            findNavController().navigate(ChoiceOfPlacesFragmentDirections.actionChoiceOfPlacesFragmentToChoiceOfDaysFragment())
        }

        commonViewModel.newCityLive.observe(viewLifecycleOwner) { city ->
            viewModel.getCityInform()
            val list = viewModel.getTables()
            viewModel.currentTableIndexLive.observe(viewLifecycleOwner) {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerViewDay.visibility = View.INVISIBLE
                index = it
                val table = TablePresentation(it + 1)
                val dates = ArrayList<LocalDate>()
                var date = LocalDate.now()
                date = date.withDayOfMonth(1)
                while (date <= LocalDate.now().plusDays(30)) {
                    dates.add(date)
                    date = date.plusMonths(1)
                }
                val months = getMonth(dates)
                val monthListAdapter = SpinnerMonthAdapter(requireContext(), months)
                binding.spinnerMonth.adapter = monthListAdapter

                binding.spinnerMonth.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val month = parent?.getItemAtPosition(position) as Month
                            viewModel.getDaysListWithOneTable(city, table, month)
                            viewModel.rentOneTableListLive.observe(viewLifecycleOwner) { MonthDate ->
                                val userData = ArrayList<DateWithPlace>()
                                viewModel.getUserData(city)
                                viewModel.userDataListLive.value.let { list ->
                                    list?.forEach { it1 ->
                                        if (translateMonthToString(it1.date.month.value) == month.name)
                                            userData.add(it1)
                                    }
                                }
                                val adapter = DayRentOneTableAdapter(
                                    list = MonthDate,
                                    user = user,
                                    userData = userData,
                                    object : RentInterface {
                                        override fun onAddClicked(
                                            position: Int
                                        ) {
                                            val dataList = ArrayList<DateWithPlace>()
                                            val data = DateWithPlace(
                                                MonthDate[position].date,
                                                table.number
                                            )
                                            dataList.add(data)
                                            viewModel.rentListTables(
                                                NewBookingPresentation(
                                                    region = city.name,
                                                    datesWithPlaces = dataList
                                                )
                                            )
                                            viewModel.saveSuccessLive.value.let { it1 ->
                                                if (it1 == true) {
                                                    val toast = Toast.makeText(
                                                        requireContext(),
                                                        "Успешно сохранено",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    toast.show()
                                                    MonthDate[position].login =
                                                        viewModel.getUser().login
                                                    userData.add(data)
                                                } else {
                                                    val toast = Toast.makeText(
                                                        requireContext(),
                                                        "Сохранение не удалось. Обновляем данные",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    toast.show()
                                                    viewModel.getDaysListWithOneTable(
                                                        city,
                                                        table,
                                                        month
                                                    )
                                                }

                                            }
                                        }

                                        override fun onDeleteClicked(
                                            position: Int
                                        ) {
                                            val dataList = ArrayList<DateWithPlace>()
                                            val data = DateWithPlace(
                                                MonthDate[position].date,
                                                table.number
                                            )
                                            dataList.add(data)
                                            viewModel.deleteRent(
                                                NewBookingPresentation(
                                                    region = city.name,
                                                    datesWithPlaces = dataList
                                                )
                                            )
                                            viewModel.deleteSuccessLive.value.let { it1 ->
                                                if (it1 == true) {
                                                    val toast = Toast.makeText(
                                                        requireContext(),
                                                        "Успешно удалено",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    toast.show()
                                                    MonthDate[position].login = ""
                                                    userData.removeAt(
                                                        userData.indexOf(data)
                                                    )
                                                } else {
                                                    val toast = Toast.makeText(
                                                        requireContext(),
                                                        "Простите, удалить данные не удалось. Обновляем данные",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    toast.show()
                                                    viewModel.getDaysListWithOneTable(
                                                        city,
                                                        table,
                                                        month
                                                    )
                                                }
                                            }
                                        }
                                    }
                                )
                                binding.recyclerViewDay.adapter = adapter
                                binding.progressBar.visibility = View.INVISIBLE
                                binding.recyclerViewDay.visibility = View.VISIBLE
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }
            }
            val adapter = TablesAdapter(context = requireContext(), list = list, index, viewModel)
            binding.recyclerViewPlace.adapter = adapter
        }
        return binding.root
    }
}