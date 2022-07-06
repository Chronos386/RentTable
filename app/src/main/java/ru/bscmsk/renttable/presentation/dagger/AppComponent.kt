/*package ru.bscmsk.renttable.presentation.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.bscmsk.renttable.fragments.CityList.CityListFragment
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class, MainModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: CityListFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context):Builder
        fun build(): AppComponent
    }
}*/
