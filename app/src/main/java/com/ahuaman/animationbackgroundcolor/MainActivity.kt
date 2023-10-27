package com.ahuaman.animationbackgroundcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ahuaman.animationbackgroundcolor.ui.theme.AnimationBackgroundColorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationBackgroundColorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var animateColorBackground by remember {
        mutableStateOf(false)
    }

    val animateColor by animateColorAsState(
        targetValue = if(animateColorBackground) Color.Green else Color.Red,
        label = "animateColor",
        animationSpec = tween(1000)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(animateColor)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            animateColorBackground = !animateColorBackground
        }) {
            Text(text = "Click me $name!")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimationBackgroundColorTheme {
        Greeting("Android")
    }
}