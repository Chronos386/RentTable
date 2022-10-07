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
        dataBaseStorage.getAccessToken().let { accessToken ->
            when (accessToken) {
                null -> return Returnable.OldTokens
                else -> {
                    networkStorage.getRentTables(city.toData(), accessToken).let { rent ->
                        when (rent) {
                            is RentInform.RentReceived -> {
                                rent.rentList.forEach { i -> listOfRent.add(i.toDomain()) }
                                return RentInform.RentDomainReceived(listOfRent)
                            }
                            else -> {
                                dataBaseStorage.getRefreshToken().let { refreshToken ->
                                    when (refreshToken) {
                                        null -> return Returnable.OldTokens
                                        else -> {
                                            networkStorage.checkRefreshToken(refreshToken).let {
                                                return when (it) {
                                                    is Returnable.NewTokens -> {
                                                        dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                                        dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                                        networkStorage.getRentTables(
                                                            city.toData(),
                                                            it.tokens.toAccess()
                                                        ).let { it1 ->
                                                            when (it1) {
                                                                is RentInform.RentReceived -> {
                                                                    it1.rentList.forEach { i ->
                                                                        listOfRent.add(i.toDomain())
                                                                    }
                                                                    RentInform.RentDomainReceived(
                                                                        listOfRent
                                                                    )
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
                        }
                    }
                }
            }
        }
    }

    override suspend fun getMyRentByCity(city: CityDomain): Returnable {
        val listOfRent = ArrayList<BookingDomain>()
        dataBaseStorage.getAccessToken().let { accessToken ->
            when (accessToken) {
                null -> return Returnable.OldTokens
                else -> {
                    networkStorage.getMyRent(city.toData(), accessToken).let { rent ->
                        when (rent) {
                            is RentInform.RentReceived -> {
                                rent.rentList.forEach { i -> listOfRent.add(i.toDomain()) }
                                return RentInform.RentDomainReceived(listOfRent)
                            }
                            else -> {
                                dataBaseStorage.getRefreshToken().let { refreshToken ->
                                    when (refreshToken) {
                                        null -> return Returnable.OldTokens
                                        else -> {
                                            networkStorage.checkRefreshToken(refreshToken).let {
                                                return when (it) {
                                                    is Returnable.NewTokens -> {
                                                        dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                                        dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                                        networkStorage.getMyRent(
                                                            city.toData(),
                                                            it.tokens.toAccess()
                                                        ).let { it1 ->
                                                            when (it1) {
                                                                is RentInform.RentReceived -> {
                                                                    it1.rentList.forEach { i ->
                                                                        listOfRent.add(
                                                                            i.toDomain()
                                                                        )
                                                                    }
                                                                    RentInform.RentDomainReceived(
                                                                        listOfRent
                                                                    )
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
                        }
                    }
                }
            }
        }
    }

    override suspend fun sendNewRentByCity(newBooking: NewBookingDomain): Returnable {
        dataBaseStorage.getAccessToken().let { accessToken ->
            when (accessToken) {
                null -> return Returnable.OldTokens
                else -> {
                    networkStorage.sendNewRent(newBooking.toData(), accessToken).let { myRent ->
                        return when (myRent) {
                            is DataPosted.IsPosted -> myRent
                            is DataPosted.NotPosted -> myRent
                            else -> {
                                dataBaseStorage.getRefreshToken().let { refreshToken ->
                                    when (refreshToken) {
                                        null -> return Returnable.OldTokens
                                        else -> {
                                            networkStorage.checkRefreshToken(refreshToken).let {
                                                when (it) {
                                                    is Returnable.NewTokens -> {
                                                        dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                                        dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                                        networkStorage.sendNewRent(
                                                            newBooking.toData(),
                                                            it.tokens.toAccess()
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
                    }
                }
            }
        }
    }

    override suspend fun clearMyRent(city: CityDomain): Returnable {
        dataBaseStorage.getAccessToken().let { accessToken ->
            when (accessToken) {
                null -> return Returnable.OldTokens
                else -> {
                    networkStorage.clearMyRent(city.toData(), accessToken).let { cleaning ->
                        return when (cleaning) {
                            is DataPosted.IsPosted -> cleaning
                            is DataPosted.NotPosted -> cleaning
                            else -> {
                                dataBaseStorage.getRefreshToken().let { refreshToken ->
                                    when (refreshToken) {
                                        null -> return Returnable.OldTokens
                                        else -> {
                                            networkStorage.checkRefreshToken(refreshToken).let {
                                                when (it) {
                                                    is Returnable.NewTokens -> {
                                                        dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                                        dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                                        networkStorage.clearMyRent(
                                                            city.toData(),
                                                            it.tokens.toAccess()
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
                    }
                }
            }
        }
    }

    override suspend fun deleteRent(booking: NewBookingDomain): Returnable {
        dataBaseStorage.getAccessToken().let { accessToken ->
            when (accessToken) {
                null -> return Returnable.OldTokens
                else -> {
                    networkStorage.deleteRent(booking.toData(), accessToken).let { myRent ->
                        return when (myRent) {
                            is DataPosted.IsPosted -> myRent
                            is DataPosted.NotPosted -> myRent
                            else -> {
                                dataBaseStorage.getRefreshToken().let { refreshToken ->
                                    when (refreshToken) {
                                        null -> return Returnable.OldTokens
                                        else -> {
                                            networkStorage.checkRefreshToken(refreshToken).let {
                                                when (it) {
                                                    is Returnable.NewTokens -> {
                                                        dataBaseStorage.refreshAccessToken(it.tokens.toAccess())
                                                        dataBaseStorage.refreshRefreshToken(it.tokens.toRefresh())
                                                        networkStorage.deleteRent(
                                                            booking.toData(),
                                                            it.tokens.toAccess()
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
                    }
                }
            }
        }
    }
}