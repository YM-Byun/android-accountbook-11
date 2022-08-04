package com.woowahan.accountbook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = OffWhite,
    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val SpendingColors = listOf(
    Undefined, Blue1, Blue2, Blue3, Blue4, Blue5, Green1, Green2, Green3, Green4, Green5,
    Purple1, Purple2, Purple3, Purple4, Purple5, Pink1, Pink2, Pink3, Pink4, Pink5
)

val IncomeColors = listOf(
    Undefined, Olive1, Olive2, Olive3, Olive4, Olive5, Yellow1, Yellow2, Yellow3, Yellow4, Yellow5
)

@Composable
fun AccountBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}