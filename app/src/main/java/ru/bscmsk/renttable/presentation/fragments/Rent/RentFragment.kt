package ru.bscmsk.renttable.presentation.fragments.rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.FragmentRentBinding
import ru.bscmsk.renttable.presentation.adapters.SpinnerCityAdapter
import ru.bscmsk.renttable.presentation.models.CityPresentation
import ru.bscmsk.renttable.presentation.viewModels.CommonRentViewModel
import ru.bscmsk.renttable.presentation.viewModels.RentViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.CommonRentViewModelFactory
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.RentViewModelFactory

class RentFragment : Fragment() {
    private val viewModel: RentViewModel by viewModels {
        RentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor,
            userInteractor = requireActivity().appComponent.userInteractor,
            rentInteractor = requireActivity().appComponent.rentInteractor
        )
    }
    private val commonVM: CommonRentViewModel by activityViewModels {
        CommonRentViewModelFactory(
            cityInteractor = requireActivity().appComponent.cityInteractor
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val baseAPI = "25.54.65.89"
        val binding = FragmentRentBinding.inflate(inflater, container, false)

        viewModel.getCityList()
        viewModel.cityListLive.observe(viewLifecycleOwner) {cityList->
            val cityListAdapter = SpinnerCityAdapter(requireContext(), cityList)
            binding.spinnerCity.adapter = cityListAdapter
            val index: Int = cityList.indexOf(viewModel.getCityFromDB())
            binding.spinnerCity.setSelection(index)

            binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (parent != null) {
                        commonVM.rewriteCity(parent.getItemAtPosition(position) as CityPresentation)
                        viewModel.getCityInform()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        viewModel.getCityInform()
        viewModel.cityInformLive.observe(viewLifecycleOwner){ cityInf->
            val text = "http://" + baseAPI + ":9000/bucket/" + cityInf.imageUrl
            Glide.with(requireContext())
                .load(text)
                .fitCenter()
                .into(binding.map)

            binding.map.setOnClickListener{
                val bundle = Bundle()
                bundle.putString("ImageName", text)
                findNavController().navigate(R.id.action_rentFragment_to_imageFragment, bundle)
            }
        }

        binding.userImage.setOnClickListener {
            findNavController().navigate(RentFragmentDirections.actionRentFragmentToUserFragment())
        }

        viewModel.exitAccountLive.observe(viewLifecycleOwner) {
            (activity as MainActivity).gotoLoginFragment()
        }
        return binding.root
    }
}

