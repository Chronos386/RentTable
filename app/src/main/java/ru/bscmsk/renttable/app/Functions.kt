package ru.bscmsk.renttable.app

import ru.bscmsk.renttable.presentation.models.Month
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashSet

fun translateMonthToString(monthNum: Int): String {
    val dateParser = SimpleDateFormat("MM")
    val date = dateParser.parse(monthNum.toString())
    val dateFormatter = date?.let { SimpleDateFormat("LLLL", Locale("ru")).format(it) }
    return (dateFormatter.toString().replaceFirstChar { it.titlecase(Locale.getDefault()) })
}

fun translateMonthToInt(month: String): Int {
    val dateParser = SimpleDateFormat(("MMMM"), Locale("ru"))
    val date = dateParser.parse(month)
    val dateFormatter = SimpleDateFormat(("LL"), Locale("ru")).format(date)
    return dateFormatter.toInt()
}

fun getDayOfWeek(date: LocalDate): String {
    val dateParser1 = SimpleDateFormat("yyyy-MM-dd")
    val date1 = dateParser1.parse(date.toString())
    val dateFormatter1 = date1?.let { SimpleDateFormat("E", Locale("ru")).format(it) }
    return (dateFormatter1.toString()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
}

fun checkDate(dataString: String): Boolean {
    return try {
        LocalDate.parse(
            dataString.replace('/', '.').replace('-', '.'),
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
        )
        true
    } catch (e: Exception) {
        false
    }
}

fun getMonth(dates: List<LocalDate>): List<Month> {
    val monthList = HashSet<Month>()
    dates.forEach {
        monthList.add(Month(translateMonthToString(it.month.value)))
    }
    return monthList.toList().reversed()
}
