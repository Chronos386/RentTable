package ru.bscmsk.renttable.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.bscmsk.renttable.MainActivity
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.FragmentLoginBinding
import ru.bscmsk.renttable.presentation.viewModels.LoginViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.LoginViewModelFactory

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            requireActivity().appComponent.userInteractor
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val button = binding.ButtonEnterLogin
        val errorText = binding.ErrorText
        val loginText = binding.LoginEnterField
        val passwordText = binding.PasswordEnterField
        (activity as MainActivity).supportActionBar?.hide()

        button.setOnClickListener {
            if (loginText.text.toString().isEmpty()) {
                errorText.setTextColor(resources.getColor(R.color.red, null))
                errorText.text = "Введите логин."
            } else {
                if (loginText.text.toString().isEmpty()) {
                    errorText.setTextColor(resources.getColor(R.color.red, null))
                    errorText.text = "Введите пароль."
                } else
                    viewModel.enterToAccount(
                        loginText.text.toString(),
                        passwordText.text.toString()
                    )
            }
        }

        viewModel.resultEnter.observe(viewLifecycleOwner) {
            if (it == false) {
                errorText.setTextColor(resources.getColor(R.color.red, null))
                errorText.text = "Логин или пароль введены неверно. Повторите попытку."
            } else {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCityListFragment())

            }
        }
        return binding.root
    }
}