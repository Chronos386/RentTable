package ru.bscmsk.renttable.domain.usecase

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.implementations.DBTablesRepositoryImpl

class ClearDBUseCase(private val dbTablesRepositoryImpl: DBTablesRepositoryImpl) {
    fun execute(context: Context) {
        dbTablesRepositoryImpl.clearDB(context = context)
    }
}