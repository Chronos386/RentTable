package ru.bscmsk.renttable.presentation.fragments.rent

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.UserFragmentBinding
import ru.bscmsk.renttable.presentation.adapters.*
import ru.bscmsk.renttable.app.getMonth
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.models.DateWithPlace
import ru.bscmsk.renttable.presentation.models.Month
import ru.bscmsk.renttable.app.translateMonthToInt
import ru.bscmsk.renttable.presentation.interfaces.RentInterface
import ru.bscmsk.renttable.presentation.models.NewBookingPresentation
import ru.bscmsk.renttable.presentation.viewModels.RentViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.RentViewModelFactory

import java.time.LocalDate

class UserFragment : Fragment() {
    private val viewModel: RentViewModel by viewModels {
        RentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor,
            userInteractor = requireActivity().appComponent.userInteractor,
            rentInteractor = requireActivity().appComponent.rentInteractor
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View {
        val binding = UserFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.show()

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        findNavController().navigate(UserFragmentDirections.actionUserFragmentToRentFragment())
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        binding.progressBar.visibility = View.INVISIBLE
        binding.userName.text = "Пользователь: " + viewModel.getUser().login
        viewModel.getUserCityList()

        binding.recyclerViewPlace.addItemDecoration(VerticalSpaceItemDecoration(22 * requireContext().resources.displayMetrics.density))
        binding.recyclerViewPlace.addItemDecoration(HorizontalSpaceItemDecoration(15 * requireContext().resources.displayMetrics.density))
        val linearLayoutManager = GridLayoutManager(context, 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewPlace.layoutManager = linearLayoutManager
        hideAll(binding)
        viewModel.userCityListLive.observe(viewLifecycleOwner) { cityList ->
            val cityListAdapter = SpinnerCityAdapter(requireContext(), cityList)
            binding.spinnerCity.adapter = cityListAdapter
            showAll(binding)
            binding.spinnerCity.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val city = parent?.getItemAtPosition(position) as CityPresentation
                        viewModel.getUserData(city)
                        viewModel.userDataListLive.observe(viewLifecycleOwner) { list ->
                            val newList = list.sortedBy { it.date }
                            val dates = ArrayList<LocalDate>()
                            newList.forEach { dates.add(it.date) }
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
                                        val monthDate =
                                            newList.filter {
                                                it.date.month.value == translateMonthToInt(
                                                    month.name
                                                )
                                            } as ArrayList<DateWithPlace>
                                        val adapter =
                                            UserRentTablesAdapter(
                                                list = monthDate,
                                                object : RentInterface {
                                                    override fun onDeleteClicked(
                                                        position: Int
                                                    ) {
                                                        val dataList = ArrayList<DateWithPlace>()
                                                        dataList.add(monthDate[position])
                                                        viewModel.deleteRent(
                                                            NewBookingPresentation(
                                                                region = city.name,
                                                                datesWithPlaces = dataList
                                                            )
                                                        )
                                                    }

                                                    override fun onAddClicked(position: Int) {}
                                                })
                                        binding.recyclerViewPlace.adapter = adapter
                                        //showAll(binding)
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                }
                        }
                        binding.deleteAllRent.setOnClickListener {
                            viewModel.deleteAllRent(city = city)
                            viewModel.deleteSuccessLive.value.let {
                                if (it == true) {
                                    binding.recyclerViewPlace.adapter = null
                                    viewModel.getUserCityList()
                                }
                            }
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }

        binding.buttonExit.setOnClickListener {
            viewModel.exitAccount()
        }

        viewModel.exitAccountLive.observe(viewLifecycleOwner) {
            (activity as MainActivity).gotoLoginFragment()
        }
        return binding.root
    }

    private fun hideAll(binding: UserFragmentBinding) {
        binding.progressBar.visibility = View.VISIBLE

        binding.userName.visibility = View.INVISIBLE
        binding.spinnerCity.visibility = View.INVISIBLE
        binding.list.visibility = View.INVISIBLE
        binding.spinnerMonth.visibility = View.INVISIBLE
        binding.recyclerViewPlace.visibility = View.INVISIBLE
        binding.deleteAllRent.visibility = View.INVISIBLE
        binding.buttonExit.visibility = View.INVISIBLE
    }

    private fun showAll(binding: UserFragmentBinding) {
        binding.progressBar.visibility = View.INVISIBLE

        binding.userName.visibility = View.VISIBLE
        binding.spinnerCity.visibility = View.VISIBLE
        binding.list.visibility = View.VISIBLE
        binding.spinnerMonth.visibility = View.VISIBLE
        binding.recyclerViewPlace.visibility = View.VISIBLE
        binding.deleteAllRent.visibility = View.VISIBLE
        binding.buttonExit.visibility = View.VISIBLE
    }
}