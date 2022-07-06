package ru.bscmsk.renttable.data.Network.implementations

import ru.bscmsk.renttable.domain.repository.RentRepository

class RentRepositoryImpl(): RentRepository {
    override suspend fun clearDataBase() {
        println("Очищено")
    }
}