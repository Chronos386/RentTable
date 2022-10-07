package ru.bscmsk.renttable.presentation.fragments.rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.FragmentImageBinding

class ImageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImageBinding.inflate(inflater, container, false)
        Glide.with(requireContext())
            .load(requireArguments().getString("ImageName"))
            .fitCenter()
            .into(binding.map)
        binding.map.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }
}