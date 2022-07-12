package ru.bscmsk.renttable.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.presentation.viewModels.BaseViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.BaseViewModelFactory


class BaseFragment: Fragment() {
    val vmFactory: BaseViewModelFactory = BaseViewModelFactory()
    private lateinit var vm: BaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!.rootView
        vm = ViewModelProvider(this,vmFactory).get(BaseViewModel::class.java)
        vm.errorLive.observe(viewLifecycleOwner, Observer {

        })
        return view
    }


}