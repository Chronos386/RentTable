package ru.bscmsk.renttable.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.ActivityMainBinding
import ru.bscmsk.renttable.presentation.fragments.CityList.CityListFragment
import ru.bscmsk.renttable.presentation.fragments.Rent.RentFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("AAA","Ab1")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("AAA","Ab2")
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.FragmentContainer.id, RentFragment())
                .commit()
        }
    }

    fun GoToListPage() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.FragmentContainer,CityListFragment())
            .commit()
    }


}