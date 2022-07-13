package ru.bscmsk.renttable.presentation.fragments.Rent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.bscmsk.renttable.R
import ru.bscmsk.renttable.databinding.FragmentChoicedaysperiodBinding
import ru.bscmsk.renttable.databinding.FragmentChoiseofdaysBinding
import ru.bscmsk.renttable.presentation.adapters.*
import java.time.LocalDate


data class ModelRentItem (
    val day:LocalDate,
    val list: List<String>
)




class ChoiseofDaysFragment: Fragment() {
    private lateinit var binding: FragmentChoiseofdaysBinding

    lateinit var vmFactory: RentViewModelFactory
    private lateinit var vm: RentViewModel

    private val args: ChoiseofDaysFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiseofdaysBinding.inflate(inflater, container, false)

        val data1 = "22.07.2022"
        val data2 = "30.07.2022"




        binding.changeDataButton.setOnClickListener{
            val dialog = BottomSheetDialog(requireContext())
            val view= FragmentChoicedaysperiodBinding.inflate(inflater, container, false)
            view.button.setOnClickListener {
                //vm.checkData(datastring)
                //Проверить правильно ли введена строка
                //Формат строки дд.мм.гггг
                /*val flag = false
                if (flag)
                {
                    MakeScreen(DataStart, DataEnd)
                    dialog.dismiss()


                }
                else
                {
                    view.textalert.setTextColor(Color.RED)
                }*/

                /*findNavController().navigate(
                    ChoiseofPlacesFragmentDirections.actionChoiseofPlacesFragmentToChoiseofDaysFragment(
                        "22.07.2022", "30.07.2022")
                )*/
            }
            dialog.setContentView(view.root)
            dialog.show()

        }


        vm.saveSuccessLive.observe(viewLifecycleOwner)
        {
            if (it)
                println("Сохранено")
            else {
                println("Не сохранено")
                MakeScreen(data1,data2)
            }
        }

        binding.recyclerViewBonus1.addItemDecoration(VerticalSpaceItemDecoration(11))
        binding.recyclerViewBonus2.addItemDecoration(VerticalSpaceItemDecoration(11))
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(22))
        binding.recyclerView.addItemDecoration(HorisontalSpaceItemDecoration(15))

        var linearLayoutManager = GridLayoutManager(context,2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager


        return binding.root
    }




    fun MakeScreen(DataStart:String, DataEnd:String){
        //vm.getdayswithalltables
        //Я передаю тебе город а также две даты (правда как пока не знаю)
        //День начала и день конца
        //Тебе надо вернуть массив со следующими значениями
        //Все дни от дня начала до дня конца и список мест, которые свободны в этот день.
        //Если дал такие данные что день старта позже дня конца возвращать пустой массив
        //Если парень ввёл такие данные которые не попадают под наш предел (30 дней от текущей даты) тоже возвращать пустой
        //Если данные попадают частично возвращать только те что попадают
        //Например сегодня 1 августа чел хочет с 25 августа по 20 сентября
        //Возвращаешь мне даты с 25 по 30
        //Далее
        //Если в каком то из дней вообще не осталось мест то этот день в массив вписывать не надо
        //И последнее
        //Сравнивай эти данные с теми которые уже находятся в LiveData
        //Если они абсолютно одинаковые то в LiveData их перезаписывать не надо

        var list: ArrayList<ModelRentItem> = ArrayList()
        val listofplaces:List<String> = listOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25")
        var date = ModelRentItem(LocalDate.of(2022,7,6),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,7),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,8),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,9),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,10),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,11),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,12),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,13),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,14),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,15),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,16),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,17),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,18),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,19),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,8,20),listofplaces)
        list.add(date)

        var indexList:ArrayList<Int> = ArrayList<Int>()

        vm.ListofDataWithTablesLive.observe(viewLifecycleOwner)
        {
            //vm.getnewindexes()
            //Я передаю тебе число n
            //Ты должен вернуть массив интов размера n
            //Каждый элемент равен 0
        }

        vm.IndexListLive.observe(viewLifecycleOwner)
        {
            indexList = it as ArrayList<Int>
        }


        getMonthes(datelist = list,indexlist = indexList, user = "AB")

        binding.button.setOnClickListener{
            //vm.savedates(user,datelist,indexlist)
            //передаётся массив и список индексов
            //Из массива ты берёшь сами даты и один элемент из списка мест
            //сам элемент будет по соответствующему индексу из indexlist
            //Сохраняешь это всё на сервер
            //Мне надо знать получилось ли сохранить или нет


        }



    }


    fun getMonthes(datelist:ArrayList<ModelRentItem>,indexlist:List<Int>,user: String){

        //getmonth(listofDate)
        //Ты принимаешь на вход массив из дат
        //Вернуть мне надо список всех месяцев переведённых на русский
        //LiveData под это можешь не делать

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
                getMonthDataList(
                    datelist = datelist,
                    user = user,
                    month = parent!!.getItemAtPosition(position).toString(),
                    indexlist = indexlist
                )
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    fun getMonthDataList(datelist:ArrayList<ModelRentItem>,user: String,month: String,indexlist: List<Int>){
        //vm.getmon(datelist, month)
        //Я передаю тебе месяц на русском и список дат
        //Твоя задача вернуть мне список дат данного месяца

        var list: ArrayList<ModelRentItem> = ArrayList()
        val listofplaces:List<String> = listOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25")
        var date = ModelRentItem(LocalDate.of(2022,7,6),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,7),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,8),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,9),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,10),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,11),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,12),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,13),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,14),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,15),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,16),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,17),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,18),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,7,19),listofplaces)
        list.add(date)
        date = ModelRentItem(LocalDate.of(2022,8,20),listofplaces)
        list.add(date)

        val adapter = DaysRentFewTableAdapter(
            context = requireContext(),
            list = list,
            recView1 = binding.recyclerViewBonus1,
            recView2 = binding.recyclerViewBonus2,
            datelist = datelist,
            indexlist = indexlist,
            vm)
        binding.recyclerView.adapter = adapter

    }

}
