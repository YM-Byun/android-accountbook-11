package com.woowahan.accountbook.ui.settings

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.woowahan.accountbook.ui.theme.*

class SettingsAddViewModel : ViewModel() {
    var name = mutableStateOf("")
    var selectedColorIdx = mutableStateOf(0)

    private val spendingColors = listOf(
        Blue1, Blue2, Blue3, Blue4, Blue5, Green1, Green2, Green3, Green4, Green5,
        Purple1, Purple2, Purple3, Purple4, Purple5, Pink1, Pink2, Pink3, Pink4, Pink5
    )

    private val incomeColors = listOf(
        Olive1, Olive2, Olive3, Olive4, Olive5, Yellow1, Yellow2, Yellow3, Yellow4, Yellow5
    )

    fun getColors(mode: String): List<Color> {
        return if (mode == "spending") {
            spendingColors
        } else {
            incomeColors
        }
    }

}