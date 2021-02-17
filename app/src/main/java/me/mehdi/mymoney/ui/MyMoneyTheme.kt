package me.mehdi.mymoney.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import me.mehdi.mymoney.R

@Composable
fun MyMoneyTheme(content:@Composable () -> Unit){
    val typography = Typography(defaultFontFamily = FontFamily(Font(R.font.lalezar)))
    MaterialTheme(typography = typography, content = content)
}