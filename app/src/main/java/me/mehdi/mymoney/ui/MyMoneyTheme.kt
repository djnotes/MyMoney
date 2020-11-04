package me.mehdi.mymoney.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MyMoneyTheme(content:@Composable () -> Unit){
    MaterialTheme(content = content)
}