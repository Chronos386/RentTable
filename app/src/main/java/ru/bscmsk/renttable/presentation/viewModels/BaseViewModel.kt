package ru.bscmsk.renttable.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(
): ViewModel() {
    var errorLiveMutable = MutableLiveData<Int>()
    var errorLive: LiveData<Int> = errorLiveMutable
}