package ru.bscmsk.renttable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.bscmsk.renttable.databinding.ActivityMainBinding
import ru.bscmsk.renttable.fragments.CityList.CityListFragment
import ru.bscmsk.renttable.MainApp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as MainApp).appComponent.inject(this)


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.CityList, CityListFragment())
                .commit()
        }
    }

}