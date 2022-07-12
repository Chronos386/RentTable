package ru.bscmsk.renttable.data.repository

import ru.bscmsk.renttable.app.sealed.DataPosted
import ru.bscmsk.renttable.app.sealed.RentInform
import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.app.toAccess
import ru.bscmsk.renttable.app.toData
import ru.bscmsk.renttable.app.toDomain
import ru.bscmsk.renttable.app.toRefresh
import ru.bscmsk.renttable.data.storage.localDB.DataBaseStorage
import ru.bscmsk.renttable.data.storage.network.NetworkStorage
import ru.bscmsk.renttable.domain.models.BookingDomain
import ru.bscmsk.renttable.domain.models.CityDomain
import ru.bscmsk.renttable.domain.models.NewBookingDomain
import ru.bscmsk.renttable.domain.repository.RentRepository
import javax.inject.Inject

class RentRepositoryImpl @Inject constructor(
    private val networkStorage: NetworkStorage,
    private val dataBaseStorage: DataBaseStorage
) : RentRepository {
    override suspend fun getRentByCity(city: CityDomain): Returnable {
        val listOfRent = ArrayList<BookingDomain>()
        networkStorage.getRentTables(city.toData(), dataBaseStorage.getAccessToken()).let { rent ->
            when (rent) {
                is RentInform.RentReceived -> {
                    rent.rentList.forEach { i -> listOfRent.add(i.toDomain()) }
                    return RentInform.RentDomainReceived(listOfRent)
                }
                else -> {
                    networkStorage.checkRefreshToken(dataBaseStorage.getRefreshToken()).let {
                        return when (it) {
                            is Returnable.NewTokens -> {
                                dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                networkStorage.getRentTables(
                                    city.toData(),
                                    dataBaseStorage.getAccessToken()
                                )
                                    .let { it1 ->
                                        when (it1) {
                                            is RentInform.RentReceived -> {
                                                it1.rentList.forEach { i -> listOfRent.add(i.toDomain()) }
                                                RentInform.RentDomainReceived(listOfRent)
                                            }
                                            else -> it1
                                        }
                                    }
                            }
                            else -> it
                        }
                    }
                }
            }
        }
    }

    override suspend fun getMyRentByCity(city: CityDomain): Returnable {
        val listOfRent = ArrayList<BookingDomain>()
        networkStorage.getMyRent(city.toData(), dataBaseStorage.getAccessToken()).let { rent ->
            when (rent) {
                is RentInform.RentReceived -> {
                    rent.rentList.forEach { i -> listOfRent.add(i.toDomain()) }
                    return RentInform.RentDomainReceived(listOfRent)
                }
                else -> {
                    networkStorage.checkRefreshToken(dataBaseStorage.getRefreshToken()).let {
                        return when (it) {
                            is Returnable.NewTokens -> {
                                dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                networkStorage.getMyRent(
                                    city.toData(),
                                    dataBaseStorage.getAccessToken()
                                )
                                    .let { it1 ->
                                        when (it1) {
                                            is RentInform.RentReceived -> {
                                                it1.rentList.forEach { i -> listOfRent.add(i.toDomain()) }
                                                RentInform.RentDomainReceived(listOfRent)
                                            }
                                            else -> it1
                                        }
                                    }
                            }
                            else -> it
                        }
                    }
                }
            }
        }
    }

    override suspend fun sendNewListRentByCity(newBooking: NewBookingDomain): Returnable {
        networkStorage.sendNewListRent(newBooking.toData(), dataBaseStorage.getAccessToken())
            .let { myRent ->
                return when (myRent) {
                    is DataPosted.IsPosted -> myRent
                    is DataPosted.NotPosted -> myRent
                    else -> {
                        networkStorage.checkRefreshToken(dataBaseStorage.getRefreshToken()).let {
                            when (it) {
                                is Returnable.NewTokens -> {
                                    dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                    dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                    networkStorage.sendNewListRent(
                                        newBooking.toData(),
                                        dataBaseStorage.getAccessToken()
                                    )
                                }
                                else -> it
                            }
                        }
                    }
                }
            }
    }
}