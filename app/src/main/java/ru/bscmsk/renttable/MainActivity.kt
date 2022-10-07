package ru.bscmsk.renttable

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import ru.bscmsk.renttable.app.appComponent
import ru.bscmsk.renttable.databinding.ActivityMainBinding
import ru.bscmsk.renttable.presentation.viewModels.MainViewModel
import ru.bscmsk.renttable.presentation.viewModels.viewFactories.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels {
        MainViewModelFactory(
            cityInteractor = appComponent.cityInteractor,
            userInteractor = appComponent.userInteractor
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.graph_nav)

        vm.checkCity()
        vm.cityCheckLive.observe(this) { cityExist ->
            if (cityExist) {
                graph.setStartDestination(R.id.rentFragment)
                navHostFragment.navController.graph = graph
            } else {
                vm.checkUser()
                vm.userCheckLive.observe(this) { userExist ->
                    if (userExist)
                        graph.setStartDestination(R.id.cityListFragment)
                    else
                        graph.setStartDestination(R.id.loginFragment)
                    navHostFragment.navController.graph = graph
                }
            }
        }
    }

    fun gotoLoginFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navHostFragment.navController.navigateUp()
        navHostFragment.navController.navigate(R.id.loginFragment)
    }
}