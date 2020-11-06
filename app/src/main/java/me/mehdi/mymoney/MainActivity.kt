package me.mehdi.mymoney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import me.mehdi.mymoney.ui.MyMoneyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HomeScreen()
        }

    }
}



@Preview
@Composable
fun HomeScreen(){
    val scaffoldState = rememberScaffoldState()
    MyMoneyTheme{
        Scaffold(floatingActionButton = { FAB() },
                topBar = { topBar() },
                scaffoldState = scaffoldState,
                drawerElevation = 8.dp,
                drawerBackgroundColor = Color.Red,
                drawerGesturesEnabled = true,
                drawerScrimColor = Color.Green,
                drawerContent = { HomeDrawer() }
        ){
        Column(modifier = Modifier.fillMaxSize().drawShadow(4.dp)){
            Text(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp), text = stringResource(id = R.string.app_name))
            Text(stringResource(id = R.string.here_is_your_costs), modifier = Modifier.padding(PaddingValues(16.dp, 8.dp, 16.dp, 8.dp)).align(Alignment.CenterHorizontally))
        }
        }
    }
}

@Composable
fun FAB(){
    FloatingActionButton(onClick = {}) {
        Icon(vectorResource(R.drawable.ic_baseline_add_24))
    }
}

@Composable
fun topBar(){
    Row {
        TopAppBar(modifier = Modifier.padding(8.dp).align(Alignment.CenterVertically)) {
            val iconModifier = Modifier.align(Alignment.CenterVertically).padding(4.dp)
            Icon(modifier = iconModifier, asset = vectorResource(id = R.drawable.ic_baseline_account_balance_24))
            Icon(vectorResource(id = R.drawable.ic_baseline_print_24), modifier = iconModifier)
            Icon(vectorResource(id = R.drawable.ic_baseline_help_outline_24), modifier = iconModifier)
        }
    }
}


@Composable
fun HomeDrawer(){
    val drawerItems = listOf(stringResource(id = R.string.diagrams), stringResource(id = R.string.help), stringResource(id = R.string.about_us))
    Column{
        LazyColumnFor(items = drawerItems) { item ->
            Text(text = item, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun bottomBar(){
    BottomAppBar() {
        Row {
            TopAppBar(modifier = Modifier.padding(8.dp)) {
                Icon(vectorResource(id = R.drawable.ic_baseline_account_balance_24))
                Icon(vectorResource(id = R.drawable.ic_baseline_print_24))
                Icon(vectorResource(id = R.drawable.ic_baseline_help_outline_24))
            }
        }
    }
}


@Preview
@Composable
fun NewItem(){
    var costName by   mutableStateOf("")
    var costValue by mutableStateOf("")
    val inputModifier = Modifier.padding(8.dp)

    Column {
        TextField(value = costName, onValueChange = { text -> costName = text }, label = { Text(stringResource(id = R.string.cost_name)) }, modifier = inputModifier, keyboardType = KeyboardType.Text)
        TextField(value = costValue, onValueChange = { value -> costValue = value }, label = { Text(stringResource(id = R.string.cost_value)) }, modifier = inputModifier, keyboardType = KeyboardType.Number)
    }
}