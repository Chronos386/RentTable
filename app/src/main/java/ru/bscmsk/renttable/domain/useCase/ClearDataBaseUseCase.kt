package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.repository.RentRepository

class ClearDataBaseUseCase(private val rentRepo: RentRepository) {
    suspend fun execute() = rentRepo.clearDataBase()
}