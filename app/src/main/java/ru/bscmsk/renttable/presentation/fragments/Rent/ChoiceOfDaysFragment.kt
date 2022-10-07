package ru.bscmsk.renttable.presentation.fragments.rent

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.app.checkDate
import ru.bscmsk.renttable.app.getMonth
import ru.bscmsk.renttable.app.translateMonthToInt
import ru.bscmsk.renttable.databinding.FragmentChoicedaysperiodBinding
import ru.bscmsk.renttable.databinding.FragmentChoiseofdaysBinding
import ru.bscmsk.renttable.presentation.adapters.*
import ru.bscmsk.renttable.presentation.models.*
import ru.bscmsk.renttable.presentation.viewModels.CommonRentViewModel
import ru.bscmsk.renttable.presentation.viewModels.RentViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.CommonRentViewModelFactory
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.RentViewModelFactory
import java.time.LocalDate
import java.util.*

class ChoiceOfDaysFragment : Fragment() {
    private val myCalendar: Calendar = Calendar.getInstance()
    private val viewModel: RentViewModel by viewModels {
        RentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor,
            userInteractor = requireActivity().appComponent.userInteractor,
            rentInteractor = requireActivity().appComponent.rentInteractor
        )
    }
    private val commonVm: CommonRentViewModel by activityViewModels {
        CommonRentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChoiseofdaysBinding.inflate(inflater, container, false)
        var city = CityPresentation("")
        var dateStart = ""
        var dateEnd = ""
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.show()

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        findNavController().navigate(ChoiceOfDaysFragmentDirections.actionChoiceOfDaysFragmentToChoiceOfPlacesFragment())
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        commonVm.newCityLive.observe(viewLifecycleOwner) {
            city = it
            if (checkDate(dateStart) && checkDate(dateEnd))
                makeScreen(dateStart, dateEnd)
            viewModel.getCityInform()
        }

        viewModel.dataRangeLive.observe(viewLifecycleOwner) {
            dateStart = it[0]
            dateEnd = it[1]
        }

        viewModel.freeTablesListLive.observe(viewLifecycleOwner) {
            val dates = ArrayList<LocalDate>()
            it.forEach { rent -> dates.add(rent.date) }
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
                        val month = parent!!.getItemAtPosition(position) as Month
                        val monthDate =
                            it.filter { it1 -> it1.date.month.value == translateMonthToInt(month.name) }
                        val adapter = DaysRentFewTableAdapter(
                            context = requireContext(),
                            list = monthDate,
                            recView1 = binding.recyclerViewBonus1,
                            recView2 = binding.recyclerViewBonus2,
                            vm = viewModel
                        )
                        binding.recyclerView.adapter = adapter
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }

        viewModel.exitAccountLive.observe(viewLifecycleOwner) {
            (activity as MainActivity).gotoLoginFragment()
        }

        binding.changeDateButton.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = FragmentChoicedaysperiodBinding.inflate(inflater, container, false)
            val myFormat = "dd.MM.yyyy"
            val dateFormat = SimpleDateFormat(myFormat, Locale.getDefault())
            val firstDate = OnDateSetListener { _, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                view.editTextDateBegin.setText(dateFormat.format(myCalendar.time))
            }
            val secondDate = OnDateSetListener { _, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                view.editTextDateEnd.setText(dateFormat.format(myCalendar.time))
            }
            view.editTextDateBegin.setOnClickListener {
                val dateOne = DatePickerDialog(
                    requireContext(), firstDate,
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
                )
                dateOne.show()
                dateOne.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK)
                dateOne.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
                dateOne.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            }
            view.editTextDateEnd.setOnClickListener {
                val dateTwo = DatePickerDialog(
                    requireContext(), secondDate,
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
                )
                dateTwo.show()
                dateTwo.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK)
                dateTwo.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
                dateTwo.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            }
            view.button.setOnClickListener {
                dateStart = view.editTextDateBegin.text.toString()
                dateEnd = view.editTextDateEnd.text.toString()
                if (checkDate(dateStart) && checkDate(dateEnd)) {
                    viewModel.saveData(dateStart, dateEnd)
                    makeScreen(dateStart, dateEnd)
                    binding.button.visibility = View.VISIBLE
                    binding.changeDateButton.text = getString(R.string.changeDates)
                    dialog.dismiss()
                }
            }
            dialog.setContentView(view.root)
            dialog.show()
        }

        binding.button.setOnClickListener {
            val dataSaving = ArrayList<DateWithPlace>()
            viewModel.freeTablesListLive.value?.forEach {
                dataSaving.add(DateWithPlace(it.date, it.tableList[it.currentIndex].number))
            }
            viewModel.rentListTables(
                NewBookingPresentation(
                    region = city.name,
                    datesWithPlaces = dataSaving
                )
            )
            viewModel.saveSuccessLive.value.let {
                if (it == true) {
                    val toast = Toast.makeText(
                        requireContext(),
                        "Успешно сохранено",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                    findNavController().navigate(ChoiceOfDaysFragmentDirections.actionChoiceOfDaysFragmentToChoiceOfPlacesFragment())
                } else {
                    val toast = Toast.makeText(
                        activity,
                        "Сохранение не удалось. Обновляем данные",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                    makeScreen(dateStart, dateEnd)
                }
            }
        }

        binding.recyclerViewBonus1.addItemDecoration(VerticalSpaceItemDecoration(11 * requireContext().resources.displayMetrics.density))
        binding.recyclerViewBonus2.addItemDecoration(VerticalSpaceItemDecoration(11 * requireContext().resources.displayMetrics.density))
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(22 * requireContext().resources.displayMetrics.density))
        binding.recyclerView.addItemDecoration(HorizontalSpaceItemDecoration(15 * requireContext().resources.displayMetrics.density))

        val linearLayoutManager = GridLayoutManager(context, 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager
        return binding.root
    }

    private fun makeScreen(DataStart: String, DataEnd: String) {
        viewModel.getDaysWithAllTables(firstDate = DataStart, endDate = DataEnd)
    }
}