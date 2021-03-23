package me.mehdi.mymoney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import me.mehdi.mymoney.db.CostViewModel
import me.mehdi.mymoney.ui.MyMoneyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import me.mehdi.mymoney.db.Cost
import me.mehdi.mymoney.db.CostViewModelFactory

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import androidx.compose.ui.platform.setContent
import androidx.activity.compose.setContent
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    //    private val costViewModel by viewModels<CostViewModelFactory().>()
    private val costViewModel: CostViewModel by viewModels {
        CostViewModelFactory((application as CostsApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            MyMoneyTheme {
                val navigator = rememberNavController()
                HomeScreen(navigator, costViewModel)
            }
        }


    }



    @Preview
    @Composable
    fun PreviewMainScreen(){
//        val costViewModel: CostViewModel by viewModels {
//            CostViewModelFactory((application as CostsApplication).repository)
//        }

        val navController = rememberNavController()

        HomeScreen(navController = navController, costViewModel = costViewModel)
    }

}


@Composable
fun HomeScreen(navController: NavHostController, costViewModel: CostViewModel?){

    NavHost(navController, startDestination = "home"){
        composable("home"){
            if (costViewModel != null) {
                Home(navController, costViewModel, onItemClicked = {id -> navController.navigate("detail/$id")})
            }
        }
        composable("new-item"){
            if (costViewModel != null) {
                NewItem(navController, costViewModel)
            }
        }
        composable("profile")
        {navBackStackEntry ->
            Profile(navBackStackEntry.arguments?.getString("name").toString())
        }

        composable("detail/{id}", listOf(navArgument("id"){type = NavType.IntType})){
            navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id")?.let {
                if (costViewModel != null) {
                    Detail(it, costViewModel)
                }
            }
        }

        composable("balance"){
            navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id")?.let{
                BalanceScreen()
            }
        }


        composable("settings"){
            SettingsScreen()
        }
    }

}

@Composable
fun BalanceScreen() {
    Box{
        Text("Balance")
    }
}

@Composable
fun SettingsScreen() {
    Box{
        Text("Settings")
    }
}

//@Preview
@Composable
fun Profile(name: String){
    Column{
        Text(name)
        Image(painterResource(R.drawable.eagle), contentDescription = "Eagle")
    }
}

@Composable
fun FAB(navigator: NavController){
    FloatingActionButton(onClick = {navigator.navigate("new-item")}) {
        Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "Add")
}
}

@Composable
fun Home(navController: NavHostController, costViewModel: CostViewModel, onItemClicked : (id: Int) -> Unit) {


    val scaffoldState = rememberScaffoldState()
    var costName by mutableStateOf("")
    var costValue by mutableStateOf("")
    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("costName")?.observe(navController.currentBackStackEntry!!) { value ->
        costName = value
    }

    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("costValue")?.observe(navController.currentBackStackEntry!!) { value ->
        costValue = value
    }



    MyMoneyTheme {
        Scaffold(floatingActionButton = { FAB(navController) },
                topBar = { TopBar(navController) },
                scaffoldState = scaffoldState,
                drawerElevation = 8.dp,
                drawerBackgroundColor = Color.Red,
                drawerGesturesEnabled = true,
                drawerScrimColor = Color.Green,
                drawerContent = { HomeDrawer() },
            bottomBar = { MainBottomNavigation(navController) }
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

                Text(stringResource(id = R.string.here_is_your_costs), modifier = Modifier
                    .padding(PaddingValues(16.dp, 8.dp, 16.dp, 8.dp))
                    .align(Alignment.CenterHorizontally))
                val costs = costViewModel.costs.observeAsState()
                costs.value?.let { items ->
                    Surface(modifier = Modifier.shadow(4.dp)){
                    LazyColumn(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxSize()
                    ) {
                        items(items = items, itemContent = { cost ->

                             cost.id?.let{
                                Row(modifier = Modifier
                                    .clickable { onItemClicked(cost.id) }
                                    .fillMaxWidth()) {
                                    Text(
                                        "${cost.costName}\t : $${cost.costValue}",
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .width(150.dp)


                                    )
                                    Image(
                                        painterResource(id = getRandomImageResource()),
                                        contentDescription = cost.costName,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .size(150.dp)
                                            .border(2.dp, Color.Black),
                                        contentScale = ContentScale.Crop
                                    )

                                }
                            }


                        }
                        )
                    }
                }
                }

            }
        }
    }
}

@Composable
fun TopBar(navController: NavHostController){
    Row {
        TopAppBar(modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterVertically)) {
            val iconModifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(4.dp)
            Icon(painterResource(id = R.drawable.ic_baseline_account_balance_24), contentDescription = "Balance",  modifier = iconModifier)
            Icon(painterResource(id = R.drawable.ic_baseline_print_24), contentDescription = "Print",  modifier = iconModifier)
            Icon(painterResource(id = R.drawable.ic_baseline_help_outline_24), contentDescription = "Help", modifier = iconModifier)
            Icon(painterResource(R.drawable.ic_baseline_person_24), contentDescription = "Person",  modifier = iconModifier.clickable(onClick = {navController.navigate("profile/John Doe")}))
        }
    }
}


@Composable
fun HomeDrawer(){
    val drawerItems = listOf(stringResource(id = R.string.diagrams), stringResource(id = R.string.help), stringResource(id = R.string.about_us))
    Column{
        LazyColumn (modifier = Modifier.fillMaxWidth()) {

            items(items = drawerItems, itemContent = { item ->
                Text(text = item, modifier = Modifier.padding(8.dp))
            })
        }
    }
}



//@Preview
@Composable
fun NewItem(navigator: NavController, costViewModel: CostViewModel){


    var costName by remember { mutableStateOf("") }
    var costValue by remember { mutableStateOf("") }
    val inputModifier = Modifier.padding(8.dp)

    Column (modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        BasicText("هزینه و مقدار آن را وارد کنید (Basic Text): ", style = TextStyle.Default.copy(color = Color.Red, fontWeight = FontWeight(10), fontFamily = FontFamily(listOf(
            Font(R.font.vazir)
        ))
        ), modifier = Modifier.align(Alignment.CenterHorizontally))
        TextField(value = costName, onValueChange = { text -> costName = text }, label = { Text(stringResource(id = R.string.cost_name)) }, modifier = inputModifier, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text))
        TextField(value = costValue, onValueChange = { value -> costValue = value }, label = { Text(stringResource(id = R.string.cost_value)) }, modifier = inputModifier, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))



        Button(onClick = {
            costViewModel.addCost(Cost(costName = costName, costValue = costValue.toDouble()))
            navigator.previousBackStackEntry?.savedStateHandle?.set("costName", costName)
            navigator.previousBackStackEntry?.savedStateHandle?.set("costValue", costValue)
            navigator.popBackStack()

        }){
            Text("Save")
        }
    }
}


fun getRandomImageResource() : Int {
    val resources = arrayOf(
        R.drawable.apple,
    R.drawable.book,
    R.drawable.orange,
    R.drawable.meat,
    R.drawable.beverage,
    R.drawable.vegetables
    )

    val index = Random.nextInt(0, resources.size)
    return resources[index]
}



@Composable
fun BottomBar(){
        BottomAppBar() {
            Row {
                TopAppBar(modifier = Modifier.padding(8.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_account_balance_24),
                        contentDescription = "Balance",
                        modifier = Modifier.clickable {  }
                    )
                    Icon(
                        painterResource(id = R.drawable.ic_baseline_print_24),
                        contentDescription = "Print"
                    )
                    Icon(
                        painterResource(id = R.drawable.ic_baseline_help_outline_24),
                        contentDescription = "Help"
                    )
                }
            }
        }
}



@Composable
fun Detail(id: Int, viewModel: CostViewModel){
    val cost by remember(viewModel, id) {viewModel.findCost(id)}.collectAsState(Cost(costName = "default", costValue = 10.0))
    Surface(modifier = Modifier.shadow(4.dp)) {
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            BasicText(stringResource(id = R.string.prodcuct_details), style = MaterialTheme.typography.h3)
            Image(
                painterResource(id = getRandomImageResource()),
                contentDescription = cost.costName,
                modifier = Modifier.border(1.dp, Color.Black)
            )
            BasicText(cost.costName, style = MaterialTheme.typography.subtitle1)
            BasicText("${cost.costValue}", style = MaterialTheme.typography.subtitle1)
        }
    }

}

@Composable
fun MainBottomNavigation(navController: NavHostController){
    val items = listOf(Screen.Home, Screen.Profile, Screen.Balance, Screen.Settings)
    BottomNavigation(){
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.arguments?.getString(KEY_ROUTE)
        items.forEach{ screen ->
            BottomNavigationItem(
                selected =  currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = stringResource(screen.resourceId)) },
                label = { Text(stringResource(screen.resourceId)) }

            )

        }
    }
}