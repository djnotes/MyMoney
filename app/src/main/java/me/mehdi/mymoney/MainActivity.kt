package me.mehdi.mymoney

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import me.mehdi.mymoney.ui.MyMoneyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
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
        Column(modifier = Modifier.fillMaxSize().drawShadow(8.dp)){
            Text(modifier = Modifier.align(Alignment.CenterHorizontally).clip(RoundedCornerShape(16.dp)), text = stringResource(id = R.string.app_name))
            Text(stringResource(id = R.string.here_is_your_costs))
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
        TopAppBar(modifier = Modifier.padding(8.dp)) {
            Icon(vectorResource(id = R.drawable.ic_baseline_account_balance_24))
            Icon(vectorResource(id = R.drawable.ic_baseline_print_24))
            Icon(vectorResource(id = R.drawable.ic_baseline_help_outline_24))
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