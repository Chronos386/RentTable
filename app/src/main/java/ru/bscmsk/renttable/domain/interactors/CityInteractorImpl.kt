package ru.bscmsk.renttable.domain.interactors

import ru.bscmsk.renttable.app.sealed.CitiesList
import ru.bscmsk.renttable.app.sealed.CityInform
import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.app.toDomain
import ru.bscmsk.renttable.app.toPresentation
import ru.bscmsk.renttable.domain.repository.CityRepository
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.models.CityPresentation
import javax.inject.Inject

class CityInteractorImpl @Inject constructor(
    private val cityRepository: CityRepository
) : CityInteractor {
    override suspend fun getCitiesList(): Returnable =
        cityRepository.getCitiesList().let {
            when (it) {
                is CitiesList.ListDomainReceived -> {
                    val listOfCityName = ArrayList<CityPresentation>()
                    it.citiesList.forEach { i -> listOfCityName.add(i.toPresentation()) }
                    CitiesList.ListPresentationReceived(listOfCityName)
                }
                else -> it
            }
        }

    override suspend fun getCityInform(): Returnable =
        cityRepository.getCityInform().let {
            when (it) {
                is CityInform.InformDomainReceived -> CityInform.InformPresentationReceived(it.cityInformDomain.toPresentation())
                else -> it
            }
        }

    override suspend fun saveCity(city: CityPresentation) =
        cityRepository.saveCity(city.toDomain())

    override suspend fun getCity(): CityPresentation =
        cityRepository.getCity().toPresentation()

    override suspend fun checkCity(): Boolean =
        when (cityRepository.getCity().name) {
            "" -> false
            else -> true
        }

    override suspend fun updateCity(city: CityPresentation) =
        cityRepository.updateCity(city.toDomain())
}