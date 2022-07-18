package ru.bscmsk.renttable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.ActivityMainBinding
import ru.bscmsk.renttable.presentation.fragments.LoginFragment
import ru.bscmsk.renttable.presentation.fragments.Rent.RentViewModel
import ru.bscmsk.renttable.presentation.fragments.Rent.RentViewModelFactory
import ru.bscmsk.renttable.presentation.viewModels.MainViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModels {
       MainViewModelFactory(
            cityInteractor = appComponent.cityInteractor,
            userInteractor = appComponent.userInteractor,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.graph_nav)

        vm.checkCity()
        vm.CityCheckLive.observe(this){
            if (it) {
                graph.setStartDestination(R.id.rentFragment)
                navHostFragment.navController.graph = graph
            }
            else
            {
                vm.checkUser()
                vm.UserCheckLive.observe(this){
                    if (it)
                        graph.setStartDestination(R.id.cityListFragment)
                    else
                        graph.setStartDestination(R.id.loginFragment)
                    navHostFragment.navController.graph = graph
                }
            }

        }


    }

    fun gotoLoginFragment(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navHostFragment.navController.navigateUp()
        navHostFragment.navController.navigate(R.id.loginFragment)
    }
}