package ru.bscmsk.renttable.presentation.fragments.Rent

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.FragmentChoicedaysperiodBinding
import ru.bscmsk.renttable.databinding.FragmentChoiseofdaysBinding
import ru.bscmsk.renttable.databinding.FragmentChoiseofplacesBinding
import ru.bscmsk.renttable.presentation.adapters.*
import ru.bscmsk.renttable.presentation.fragments.LoginFragmentDirections
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ChoiseofPlacesFragment: Fragment() {
    private lateinit var binding: FragmentChoiseofplacesBinding

    lateinit var vmFactory: RentViewModelFactory
    private lateinit var vm: RentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiseofplacesBinding.inflate(inflater, container, false)

        vm = ViewModelProvider(this,vmFactory).get(RentViewModel::class.java)

        binding.recyclerViewDay.addItemDecoration(VerticalSpaceItemDecoration(12))


        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewPlace.layoutManager = linearLayoutManager
        binding.recyclerViewPlace.addItemDecoration(HorisontalSpaceItemDecoration(8))


        val linearLayoutManager2 = LinearLayoutManager(requireContext())
        linearLayoutManager2.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewDay.layoutManager = linearLayoutManager2
        binding.recyclerViewDay.addItemDecoration(VerticalSpaceItemDecoration(12))



        //makeScreen("Москва","AB")

        //vm.getUserfromDB() Получить пользователя из базыданных
        //LiveData под это можешь не делать

        vm.CityLive.observe(viewLifecycleOwner)
        {
            makeScreen(it,user ="AB")
        }

        binding.button.setOnClickListener{

            val dialog = BottomSheetDialog(requireContext())
            val view= FragmentChoicedaysperiodBinding.inflate(inflater, container, false)
            view.button.setOnClickListener {
                //vm.checkData(datastring)
                //Проверить правильно ли введена строка
                //Формат строки дд.мм.гггг
                /*val flag = false
                if (flag)
                {
                    dialog.dismiss()
                }
                else
                {
                    view.textalert.setTextColor(Color.RED)
                }*/

                findNavController().navigate(
                    ChoiseofPlacesFragmentDirections.actionChoiseofPlacesFragmentToChoiseofDaysFragment(
                        "22.07.2022", "30.07.2022")
                )
            }
            dialog.setContentView(view.root)
            dialog.show()
        }

        return binding.root

    }

    fun makeScreen(city:String, user: String){
        val list:List<String> = listOf("1","2","3","4","5","6","7","8","9","10")

        //vm.gettable(city) Получить количество мест в офисе данного города.
        // Так как такого пока нет, то передать List<String> из 20 элементов
        //Типо ("1","2",....,"20")
        // LiveData под это можешь не делать


        var index = -1

        vm.CurrentTableIndexLive.observe(viewLifecycleOwner){
            index = it
            getMonthes(user = "AB",table = index, city = city)
        }

        val adapter = TablesAdapter(context = requireContext(),list = list)
        binding.recyclerViewPlace.adapter = adapter
    }

    fun getMonthes(user: String, table: Int, city: String)
    {

        //vm.getmonths(city,table)
        // Надо вернуть все месяцы, переведённые на русский

        val monthlist:List<String>  = listOf("Июль","Август")
        val monthlistAdapter: ArrayAdapter<String> = ArrayAdapter<String> (requireContext(),
            R.layout.spinner_month_item,monthlist)
        monthlistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = monthlistAdapter

        vm.saveSuccessLive.observe(viewLifecycleOwner)
        {
            if (it)
                println("Сохранено")
            else
                println("Не сохранено")
            getMonthDataList(user = "AB",month = binding.spinnerMonth.selectedItem.toString(),table = table)
        }

        binding.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            )
            {
                getMonthDataList(user, parent!!.getItemAtPosition(position).toString(),table)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    fun getMonthDataList(user: String,month: String,table:Int){

        //vm.getdates(month,table)
        //Я передаю тебе месяц на русском
        //Твоя задача вернуть мне список
        //Элемент списка дата и пользователь, если таковой есть
        //Если пользователя нет пиши свободно или пусто
        // LiveData можно не использовать

        var list2: ArrayList<LocalDate> = ArrayList()
        var date = LocalDate.of(2022,7,6)
        list2.add(date)
        date = LocalDate.of(2022,7,7)
        list2.add(date)
        date = LocalDate.of(2022,7,8)
        list2.add(date)
        date = LocalDate.of(2022,7,9)
        list2.add(date)
        date = LocalDate.of(2022,7,10)
        list2.add(date)
        date = LocalDate.of(2022,7,11)
        list2.add(date)
        date = LocalDate.of(2022,7,12)
        list2.add(date)

        val adapter = DayRentOneTableAdapter(context = requireContext(),list = list2, user = user,table = table,vm)
        binding.recyclerViewDay.adapter = adapter
    }
}