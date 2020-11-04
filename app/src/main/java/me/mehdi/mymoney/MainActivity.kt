package me.mehdi.mymoney

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
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
    MyMoneyTheme(){
//        Column(modifier = Modifier.fillMaxSize().padding(16.dp).drawShadow(8.dp).clip(
//            RoundedCornerShape(4.dp))){
//            Text(modifier = Modifier.align(Alignment.CenterHorizontally) , text = "Title")
//            Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {}){
//                Text("Let's Go")
//            }
//        }

        Scaffold(floatingActionButton = {  }){
        Column(modifier = Modifier.fillMaxSize().drawShadow(8.dp).clip(
            RoundedCornerShape(4.dp))){
            Text(modifier = Modifier.align(Alignment.CenterHorizontally).clip(RoundedCornerShape(16.dp)) , text = "My Money")
            Text("Here Are Your Costs")
        }
        }
    }
}

@Composable
fun FAB(){
    FloatingActionButton(onClick = {}) {
        Icon(imageResource(R.drawable.ic_baseline_add_24))
    }
}