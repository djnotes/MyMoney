package me.mehdi.mymoney

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector){
    object Profile: Screen("profile", R.string.profile, Icons.Outlined.Person)
    object Home: Screen("home", R.string.home, Icons.Outlined.Home)
    object Balance: Screen("balance", R.string.balance, Icons.Outlined.Money)
    object Settings: Screen("settings", R.string.settings, Icons.Outlined.Settings)
}



