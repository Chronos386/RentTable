package ru.bscmsk.renttable.app

import ru.bscmsk.renttable.data.storage.localDB.entity.*
import ru.bscmsk.renttable.data.storage.models.*
import ru.bscmsk.renttable.domain.models.*
import ru.bscmsk.renttable.presentation.models.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val pattern: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


fun TokensData.toAccess() = AccessTokenData(
    token = this.accessToken
)

fun TokensData.toRefresh() = RefreshTokenData(
    token = this.refreshToken
)


fun AccessTokenModel.toData() = AccessTokenData(
    token = this.token
)

fun AccessTokenData.toTable() = AccessTokenModel(
    id = 1,
    token = this.token
)


fun RefreshTokenModel.toData() = RefreshTokenData(
    token = this.token
)

fun RefreshTokenData.toTable() = RefreshTokenModel(
    id = 1,
    token = this.token
)


fun CityModel.toData() = CityData(
    name = this.name
)

fun CityData.toTable() = CityModel(
    id = 1,
    name = this.name
)

fun CityData.toDomain() = CityDomain(
    name = this.name
)

fun CityDomain.toData() = CityData(
    name = this.name
)

fun CityDomain.toPresentation() = CityPresentation(
    name = this.name
)

fun CityPresentation.toDomain() = CityDomain(
    name = this.name
)


fun UserModel.toData() = UserData(
    login = this.login,
    password = ""
)

fun UserData.toTable() = UserModel(
    id = 1,
    login = this.login
)

fun UserData.toDomain() = UserDomain(
    login = this.login,
    password = this.password
)

fun UserDomain.toData() = UserData(
    login = this.login,
    password = this.password
)

fun UserDomain.toPresentation() = UserPresentation(
    login = this.login,
    password = this.password
)

fun UserPresentation.toDomain() = UserDomain(
    login = this.login,
    password = this.password
)


fun String.toDate(): LocalDate = LocalDate.parse(
    this,
    pattern
)

fun BookingData.toDomain() = BookingDomain(
    region = this.region,
    user = this.user,
    places = this.dateWithPlace.values.toList().map { it.toInt() },
    dates = this.dateWithPlace.keys.toList().map { it.toDate() }
)

fun BookingDomain.toPresentation() = BookingPresentation(
    region = this.region,
    user = this.user,
    places = this.places,
    dates = this.dates
)


fun NewBookingDomain.toData() = NewBookingData(
    region = this.region,
    dateWithPlace = this.dates.map { it.toString() }.zip(this.places.map { it.toString() }).toMap()
)

fun NewBookingPresentation.toDomain() = NewBookingDomain(
    region = this.region,
    places = this.places,
    dates = this.dates
)