package ru.bscmsk.renttable.presentation.fragments.Rent

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.LoginDialogFragmentBinding
import ru.bscmsk.renttable.presentation.adapters.*
import java.time.LocalDate

class LoginDialogFragment: DialogFragment() {
    private lateinit var binding: LoginDialogFragmentBinding

    val vmFactory: RentViewModelFactory = RentViewModelFactory()
    private lateinit var vm: RentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View {
        binding = LoginDialogFragmentBinding.inflate(LayoutInflater.from(context),container,false)
        vm = ViewModelProvider(this,vmFactory).get(RentViewModel::class.java)
        //vm.getCityList() надо получить список городов
        //Можешь LiveData не использовать
        binding.userName.text ="Пользователь: Зубенко Михаил Петрович"

        val citylist = ArrayList<String>()
        citylist.add("Москва")
        citylist.add("Питер")
        setSpinner(citylist,user ="Ab")

        binding.recyclerViewPlace.addItemDecoration(HorisontalSpaceItemDecoration(8))
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewPlace.layoutManager = linearLayoutManager




        binding.buttonexit.setOnClickListener{

        }

        return binding.root
    }

    fun setSpinner(citylist: List<String>, user: String){
        val citilistAdapter: ArrayAdapter<String> = ArrayAdapter<String> (requireContext(),R.layout.spinner_city_item,citylist)
        citilistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //vm.getCityfromDB()
        //Получить город из DB

        val index:Int = citylist.indexOf("Москва")
        binding.spinnerCity.setSelection(index)
        binding.spinnerCity.adapter = citilistAdapter

        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                getMonthes(city = parent!!.getItemAtPosition(position).toString(),user = user)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    fun getMonthes(city: String, user: String) {
        //Получить список месяцев на русском
        //Входные данные city и user

        val monthlist:List<String>  = listOf("Июль","Август")
        val monthlistAdapter: ArrayAdapter<String> = ArrayAdapter<String> (requireContext(),
            R.layout.spinner_month_item,monthlist)
        monthlistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = monthlistAdapter

        binding.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            )
            {
                getMonthDataList(user = user, month =  parent!!.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    fun getMonthDataList(user: String, month: String){
        //vm.getdates(month,user)
        //Я передаю тебе месяц на русском и пользователя
        //Надо вернуть список забронированных мест пользователем в этом месяце

        var list: ArrayList<LocalDate> = ArrayList()
        var date = LocalDate.of(2022,7,6)
        list.add(date)
        date = LocalDate.of(2022,7,7)
        list.add(date)
        date = LocalDate.of(2022,7,8)
        list.add(date)
        date = LocalDate.of(2022,7,9)
        list.add(date)
        date = LocalDate.of(2022,7,10)
        list.add(date)
        date = LocalDate.of(2022,7,11)
        list.add(date)
        date = LocalDate.of(2022,7,12)
        list.add(date)


        val adapter = UserRentTablesAdapter(context = requireContext(),list = list,user = "ab", vm)
        binding.recyclerViewPlace.adapter = adapter
    }



}