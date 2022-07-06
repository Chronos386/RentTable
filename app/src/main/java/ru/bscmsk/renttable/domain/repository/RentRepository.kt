package ru.bscmsk.renttable.domain.repository

interface RentRepository {
    suspend fun clearDataBase()
}