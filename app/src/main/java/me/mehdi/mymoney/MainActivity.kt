package me.mehdi.mymoney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.ui.tooling.preview.Preview
import me.mehdi.mymoney.db.CostViewModel
import me.mehdi.mymoney.ui.MyMoneyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : AppCompatActivity() {

    private val costViewModel by viewModels<CostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val navigator = rememberNavController()
            HomeScreen(navigator, costViewModel)
        }

    }
}





@Composable
fun HomeScreen(navController: NavHostController, costViewModel: CostViewModel){

    NavHost(navController, startDestination = "home"){
        composable("home"){
            Home(navController, costViewModel)
        }
        composable("new-item"){NewItem(navController, costViewModel)}
        composable("profile/{name}", listOf(navArgument("name"){type = NavType.StringType}))
        {navBackStackEntry ->
            Profile(navBackStackEntry.arguments?.getString("name").toString())
        }
    }

}

//@Preview
@Composable
fun Profile(name: String){
    Column{
        Text(name)
        Image(imageResource(R.drawable.eagle))
    }
}

@Composable
fun FAB(navigator: NavController){
    FloatingActionButton(onClick = {navigator.navigate("new-item")}) {
        Icon(vectorResource(R.drawable.ic_baseline_add_24))
    }
}


@Composable
fun Home(navController: NavHostController, costViewModel: CostViewModel){




    val scaffoldState = rememberScaffoldState()
    var costName by mutableStateOf("")
    var costValue by mutableStateOf("")
    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("costName")?.observe(navController.currentBackStackEntry!!){value ->
        costName = value
    }

    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("costValue")?.observe(navController.currentBackStackEntry!!){value ->
        costValue = value
    }



    MyMoneyTheme{
        Scaffold(floatingActionButton = { FAB(navController) },
            topBar = { topBar(navController) },
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
                if (costName.isNotEmpty()){
                    Text("Cost Name: $costName")
                }

                if(costValue.isNotEmpty()){
                    Text("Cost Value: $costValue")
                }


            }
        }
    }
}
@Composable
fun topBar(navController: NavHostController){
    Row {
        TopAppBar(modifier = Modifier.padding(8.dp).align(Alignment.CenterVertically)) {
            val iconModifier = Modifier.align(Alignment.CenterVertically).padding(4.dp)
            Icon(modifier = iconModifier, asset = vectorResource(id = R.drawable.ic_baseline_account_balance_24))
            Icon(vectorResource(id = R.drawable.ic_baseline_print_24), modifier = iconModifier)
            Icon(vectorResource(id = R.drawable.ic_baseline_help_outline_24), modifier = iconModifier)
            Icon(vectorResource(R.drawable.ic_baseline_person_24), modifier = iconModifier.clickable(onClick = {navController.navigate("profile/John Doe")}))
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


//@Preview
@Composable
fun NewItem(navigator: NavController, costViewModel: CostViewModel){
    var costName by remember { mutableStateOf("") }
    var costValue by remember { mutableStateOf("") }
    val inputModifier = Modifier.padding(8.dp)

    Column {
//        TextField(value = costName, onValueChange = { text -> costName = text }, label = { Text(stringResource(id = R.string.cost_name)) }, modifier = inputModifier, keyboardType = KeyboardType.Text)
//        TextField(value = costValue, onValueChange = { value -> costValue = value }, label = { Text(stringResource(id = R.string.cost_value)) }, modifier = inputModifier, keyboardType = KeyboardType.Number)

        Button(onClick = {
            navigator.previousBackStackEntry?.savedStateHandle?.set("costName", costName)
            navigator.previousBackStackEntry?.savedStateHandle?.set("costValue", costValue)
            navigator.popBackStack()
        }){
            Text("Save")
        }
    }
}