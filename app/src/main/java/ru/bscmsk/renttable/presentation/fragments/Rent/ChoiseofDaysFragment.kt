package ru.bscmsk.renttable.presentation.fragments.Rent

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.FragmentChoicedaysperiodBinding
import ru.bscmsk.renttable.databinding.FragmentChoiseofdaysBinding
import ru.bscmsk.renttable.presentation.adapters.*
import ru.bscmsk.renttable.presentation.checkData
import ru.bscmsk.renttable.presentation.getMonth
import ru.bscmsk.renttable.presentation.models.*
import ru.bscmsk.renttable.presentation.translateMonthtoInt
import ru.bscmsk.renttable.presentation.viewModels.CommonRentViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.CommonRentViewModelFactory
import java.time.LocalDate


class ChoiseofDaysFragment: Fragment() {
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
        var DataStart = ""
        var DataEnd = ""

        commonVm.NewCityLive.observe(viewLifecycleOwner){
            city = it
            if (checkData(DataStart) && checkData(DataEnd))
                MakeScreen(city,DataStart,DataEnd)
        }

        viewModel.DataDiapozonLive.observe(viewLifecycleOwner){
           DataStart = it[0]
           DataEnd = it[1]
        }


        viewModel.FreeTablesListLive.observe(viewLifecycleOwner){
            val dates = ArrayList<LocalDate>()
            it.forEach {
                dates.add(it.Date)
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
                    val MonthDate = it.filter{it.Date.month.value == translateMonthtoInt(month.name)}
                    val adapter = DaysRentFewTableAdapter(
                        context = requireContext(),
                        list = MonthDate,
                        recView1 = binding.recyclerViewBonus1,
                        recView2 = binding.recyclerViewBonus2,
                        vm = viewModel
                    )
                    binding.recyclerView.adapter = adapter

                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

        viewModel.ExitAccountLive.observe(viewLifecycleOwner){
            (activity as MainActivity).gotoLoginFragment()
        }

        binding.changeDateButton.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = FragmentChoicedaysperiodBinding.inflate(inflater, container, false)
            view.button.setOnClickListener {
                DataStart =  view.editTextDateBegin.text.toString()
                DataEnd = view.editTextDateEnd.text.toString()

                if (checkData(DataStart) && checkData(DataEnd)) {
                    viewModel.saveData(DataStart,DataEnd)
                    MakeScreen(city,DataStart,DataEnd)
                    binding.button.visibility = View.VISIBLE
                    binding.changeDateButton.text = getString(R.string.changeDates)
                    dialog.dismiss()
                } else
                    view.textalert.setTextColor(Color.RED)
            }
            dialog.setContentView(view.root)
            dialog.show()
        }

        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.button.setOnClickListener{
            val DataforSaving = ArrayList<DateWithPlace>()
            viewModel.FreeTablesListLive.value?.forEach {
                Log.e("AAA","Почему"+it.currentIndex.toString())
                DataforSaving.add(DateWithPlace(it.Date,it.Tablelist[it.currentIndex].number))
            }
            viewModel.rentListTables(NewBookingPresentation(region = city.name, datesWithPlaces = DataforSaving ))
            viewModel.saveSuccessLive.observe(viewLifecycleOwner){
                if (it) {
                    val toast = Toast.makeText(
                        requireContext(),
                        "Успешно сохранено",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                    findNavController().popBackStack()
                } else {
                    val toast = Toast.makeText(
                        activity,
                        "Сохранение не удалось. Обновляем данные",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                    MakeScreen(city,DataStart,DataEnd)
                }
            }
        }

        binding.recyclerViewBonus1.addItemDecoration(VerticalSpaceItemDecoration(11))
        binding.recyclerViewBonus2.addItemDecoration(VerticalSpaceItemDecoration(11))
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(22))
        binding.recyclerView.addItemDecoration(HorisontalSpaceItemDecoration(15))

        var linearLayoutManager = GridLayoutManager(context, 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager
        return binding.root
    }


    fun MakeScreen(city:CityPresentation,DataStart: String, DataEnd: String) {
        viewModel.getDaysWithAllTables(city = city, firstDate = DataStart, endDate = DataEnd)
    }
}

