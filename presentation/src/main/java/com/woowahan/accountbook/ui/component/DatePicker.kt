package com.woowahan.accountbook.ui.component

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.MutableState
import java.util.*

fun DatePicker(
    context: Context,
    func: (Int, Int, Int) -> Unit
) {
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    DatePickerDialog(
        context,
        { _: DatePicker?, pYear: Int, pMonth: Int, pDay: Int ->
            func(pYear, pMonth, pDay)
        }, year, month, day
    ).show()
}