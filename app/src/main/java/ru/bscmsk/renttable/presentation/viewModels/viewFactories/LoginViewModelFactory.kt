package ru.bscmsk.renttable.presentation.viewModels.viewFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.viewModels.BaseViewModel
import ru.bscmsk.renttable.presentation.viewModels.LoginViewModel

class LoginViewModelFactory(
    private val userInteractor: UserInteractor
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return LoginViewModel(
            userInteractor = userInteractor
        ) as T
    }
}