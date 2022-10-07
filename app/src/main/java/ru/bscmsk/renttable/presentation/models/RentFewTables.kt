package ru.bscmsk.renttable.presentation.models

import java.time.LocalDate

class RentFewTables(
    val date: LocalDate,
    var tableList: List<TablePresentation>,
    var currentIndex: Int
)