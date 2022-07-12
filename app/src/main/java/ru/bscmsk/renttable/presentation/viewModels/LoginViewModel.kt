package ru.bscmsk.renttable.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bscmsk.renttable.app.sealed.UserAuthorized
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.models.UserPresentation

class LoginViewModel(
    private val userInteractor: UserInteractor
): ViewModel() {
    var resultEnter = MutableLiveData<Boolean>()

    fun enterToAccount(login: String, password: String) {
        viewModelScope.launch {
            when (userInteractor.enterAccount(UserPresentation(login, password))) {
                is UserAuthorized.IsAuthorized -> {
                    println("Входим на следующую страницу")
                    resultEnter.value = true
                }
                is UserAuthorized.NotAuthorized -> resultEnter.value = false
            }
        }
    }
}