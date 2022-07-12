package ru.bscmsk.renttable.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.FragmentLoginBinding
import ru.bscmsk.renttable.presentation.viewModels.LoginViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.LoginViewModelFactory

class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            requireActivity().appComponent.userInteractor
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        Log.e("AAA","БЛЭТ")
        val button = binding.ButtonEnterLogin
        val errorText = binding.ErorText
        val loginEditText = binding.loginEditText
        val passwordEditText = binding.passwordEditText

        Log.e("AAA","БЛЭТ2")

        button.setOnClickListener {
            if(loginEditText.text.isEmpty()) {
                errorText.setTextColor(resources.getColor(R.color.red, null))
                errorText.text = "Введите логин."
            }
            else {
                if(passwordEditText.text.isEmpty()) {
                    errorText.setTextColor(resources.getColor(R.color.red, null))
                    errorText.text = "Введите пароль."
                }
                else
                    viewModel.enterToAccount(loginEditText.text.toString(),
                        passwordEditText.text.toString())
            }

        }

        viewModel.resultEnter.observe(viewLifecycleOwner) {
            Log.e("AAA","HEY")
            if (it == false) {
                errorText.setTextColor(resources.getColor(R.color.red, null))
                errorText.text = "Логин или пароль введены неверно. Повторите попытку."
            }
            else
            {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCityListFragment())

            }
        }
        return binding.root
    }
}