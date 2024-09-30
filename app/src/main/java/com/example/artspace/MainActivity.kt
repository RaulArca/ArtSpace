package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ArtSpaceLayout(){
    var position by remember { mutableStateOf(0) }
    var title = when(position){
        0 -> "Caras con flores"
        1 -> "Máscara blanca flores"
        2 -> "Mujer carnaval"
        3 -> "Baile de carnaval"
        4 -> "Máscara carnaval"
        5 -> "Fiesta de carnaval"
        6 -> "Máscaras de carnaval acrilicos"
        else -> null
    }
    var year = when(position){
        0 -> "2000"
        1 -> "2010"
        2 -> "2015"
        3 -> "2017"
        4 -> "2018"
        5 -> "2022"
        6 -> "2024"
        else -> null
    }
    var author = when(position){
        0 -> "Zeynor Khalian"
        1 -> "Alathia Vernor"
        2 -> "Korvyn Drelmar"
        3 -> "Telyssa Aendor"
        4 -> "Rildar Marvek"
        5 -> "Velindra Solentis"
        6 -> "Otramir Nethralis"
        else -> null
    }
    var painterResource = when(position){
        0 -> painterResource(R.drawable.digital_art_carnival_design)
        1 -> painterResource(R.drawable.digital_art_carnival_design__1)
        2 -> painterResource(R.drawable.digital_art_carnival_design__2)
        3 -> painterResource(R.drawable.digital_art_carnival_design__3)
        4 -> painterResource(R.drawable.digital_art_carnival_design__4)
        5 -> painterResource(R.drawable.digital_art_carnival_design__5)
        6 -> painterResource(R.drawable.digital_art_carnival_design__6)
        else ->null
    }
    var nextImage : () -> Unit = {
        if(position==6){
            position = 0
        }
        else position++
    }
    var previusImage : () -> Unit = {
        if (position== 0 )
            position = 6
        else position--
    }

    Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
        ImageInfoLayout(
            painterResource= painterResource!!,
            title = title!!,
            author = author!!,
            year = year!!)
        ActionButtonsLayout(nextImageFunction = nextImage, previousImageFunction = previusImage)
    }
}


@Composable
fun ImageInfoLayout(
    painterResource: Painter,
    title : String,
    author: String,
    year: String,
    modifier: Modifier= Modifier){
    Column(modifier = modifier.fillMaxWidth().padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        Surface (shadowElevation = 4.dp, modifier = Modifier.border(30.dp, Color.White).fillMaxWidth()) {
            Image(painter = painterResource,
                contentDescription = null,
                Modifier.width(300.dp))
        }
        Spacer(Modifier.padding(30.dp))
        Column (Modifier.background(Color(0xffecebf4)).fillMaxWidth().padding(16.dp)){
            Text(title, fontSize = 25.sp)


            Text(buildAnnotatedString {
                withStyle(style = SpanStyle( fontWeight = FontWeight.Bold)){
                    append(author)
                }
                append(" (")
                append(year)
                append(")")
            })

        }
    }
}




@Composable
fun ActionButtonsLayout(
    nextImageFunction : () -> Unit,
    previousImageFunction : () -> Unit,
    modifier: Modifier= Modifier.width(150.dp)){
    Row( Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = previousImageFunction, modifier= modifier) {
            Text(stringResource(R.string.previous_tag))
        }
        Button(onClick = nextImageFunction,modifier= modifier) {
            Text("Next")
        }
    }
}


