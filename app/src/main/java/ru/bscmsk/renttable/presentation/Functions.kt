package ru.bscmsk.renttable.presentation

import ru.bscmsk.renttable.presentation.models.Month
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashSet

fun translateMonthtoString(monthnum: Int ):String{
    val dateParser = SimpleDateFormat("MM")
    val date = dateParser.parse(monthnum.toString())
    val dateFormatter = date?.let { SimpleDateFormat("LLLL", Locale("ru")).format(it) }
    return( dateFormatter.toString().replaceFirstChar {it.titlecase(Locale.getDefault()) })
}

fun translateMonthtoInt(month: String ):Int{
    val dateParser = SimpleDateFormat(("MMMM"), Locale("ru"))
    val date = dateParser.parse(month)
    val dateFormatter = SimpleDateFormat(("LL"), Locale("ru")).format(date)
    return dateFormatter.toInt()
}

fun GetDayofWeek(date: LocalDate):String{
    val dateParser1 = SimpleDateFormat("yyyy-MM-dd")
    val date1 = dateParser1.parse(date.toString())
    val dateFormatter1 = date1?.let { SimpleDateFormat("E", Locale("ru")).format(it) }
    return (dateFormatter1.toString()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
}

fun checkData(datastring: String) :Boolean {
    try {
        LocalDate.parse(datastring.replace('/', '.').replace('-', '.'), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        return true
    } catch (e: Exception) {
        return false
    }
}

fun getMonth(dates: List<LocalDate>):List<Month>{
    val monthList = HashSet<Month>()
    dates.forEach {
        monthList.add(Month(translateMonthtoString(it.month.value)))
    }
    return monthList.toList().reversed()
}
