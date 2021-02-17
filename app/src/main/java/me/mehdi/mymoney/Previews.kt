package me.mehdi.mymoney

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mehdi.mymoney.ui.MyMoneyTheme
import org.intellij.lang.annotations.JdkConstants


@Preview
@Composable
fun EaglePreview(){
            Box(modifier = Modifier
            .preferredSize(200.dp)
            .border(2.dp, Color.Red, CircleShape)
            .border(3.dp, Color.Blue, CircleShape)
            .border(5.dp, Color.Green, CircleShape), contentAlignment = Alignment.Center){
                Image(painterResource(id = R.drawable.eagle), contentDescription = "Image 1" , modifier = Modifier.preferredSize(100.dp), contentScale = ContentScale.Crop)
                Image(painterResource(id = R.drawable.eagle), contentDescription = "Eagle", modifier = Modifier.preferredSize(100.dp), contentScale = ContentScale.Crop)
        }
}

@Preview
@Composable
fun MyAppPreview() {
    MyMoneyTheme {


        var showMessage by remember { mutableStateOf(false) }

        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(Color.Red).size(200.dp)){

            Box(modifier = Modifier.width(150.dp).height(50.dp).background(Color.Yellow)){
                if (showMessage) {
                    BasicText(
                        "You Pressed The Button",
                        style = TextStyle.Default.copy(fontStyle = FontStyle.Italic),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }


            Button(
                onClick = { showMessage = !showMessage },
            ) {
                BasicText("Show")
            }



        }

    }
}


