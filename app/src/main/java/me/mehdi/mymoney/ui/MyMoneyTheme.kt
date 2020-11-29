package me.mehdi.mymoney.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import me.mehdi.mymoney.R

@Composable
fun MyMoneyTheme(content:@Composable () -> Unit){
    val typography = Typography(defaultFontFamily =  fontFamily(font(R.font.vazir)))
    val darkColors = darkColors()
    MaterialTheme(colors = darkColors, typography = typography, content = content)

}