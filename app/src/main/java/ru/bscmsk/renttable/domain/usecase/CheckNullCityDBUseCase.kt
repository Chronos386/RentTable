package ru.bscmsk.renttable.domain.usecase

import android.content.Context
import ru.bscmsk.renttable.domain.repository.DBCityRepository

class CheckNullCityDBUseCase(private val dbCityRepository: DBCityRepository) {
    fun execute(context: Context):Boolean {
        return dbCityRepository.check(context=context)
    }
}