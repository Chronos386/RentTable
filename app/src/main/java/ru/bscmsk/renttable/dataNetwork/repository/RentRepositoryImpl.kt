package ru.bscmsk.renttable.dataNetwork.repository

import ru.bscmsk.renttable.domain.repository.RentRepository

class RentRepositoryImpl(): RentRepository {
    override suspend fun clearDataBase() {
        println("Очищено")
    }
}