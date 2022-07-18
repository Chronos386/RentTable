package ru.bscmsk.renttable.presentation.models

import java.time.LocalDate

class RentFewTables(
    val Date: LocalDate,
    var Tablelist: List<Table>,
    var currentIndex: Int
)