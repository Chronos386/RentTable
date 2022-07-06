package ru.bscmsk.renttable.presentation.fragments.CityList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.bscmsk.renttable.domain.models.CityDataModel
import ru.bscmsk.renttable.domain.models.CityListDataModel
import ru.bscmsk.renttable.domain.useCase.GetCityListUseCase
import ru.bscmsk.renttable.domain.useCase.GoToOfficeFragmentUseCase
import ru.bscmsk.renttable.domain.useCase.SaveCityUseCase
import kotlinx.coroutines.launch
import ru.bscmsk.renttable.domain.interactors.CityInteractor
import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.useCase.GetAccessTokenUseCase

class CityListViewModel(
    private val cityInteractor: CityInteractor,
    private val saveCityUseCase: SaveCityUseCase,
    private val goToOfficeFragmentUseCase: GoToOfficeFragmentUseCase

): ViewModel()
{
    private val resultLiveMutable = MutableLiveData<List<CityDataModel>>()
    val resultLive: LiveData<List<CityDataModel>> = resultLiveMutable

    fun getCityClick(city: CityDataModel){
        viewModelScope.launch{
            saveCityUseCase.execute()
            goToOfficeFragmentUseCase.execute()
        }
    }
    fun getList(){
        lateinit var list:List<CityDataModel>
        viewModelScope.launch{
            list = getCityList()
        }
        resultLiveMutable.value = list
    }
    suspend fun getCityList():List<CityDataModel> {
        val citylist = cityInteractor.getCitiesList()!!
        return citylist
    }

}





