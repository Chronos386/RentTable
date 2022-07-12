package ru.bscmsk.renttable.di

import dagger.Component
import ru.bscmsk.renttable.di.module.AppModule
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.RentInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    val userInteractor: UserInteractor
    val cityInteractor: CityInteractor
    val rentInteractor: RentInteractor
}