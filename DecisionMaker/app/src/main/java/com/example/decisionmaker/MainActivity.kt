package com.example.decisionmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionmaker.ui.theme.DecisionMakerTheme
import kotlin.random.Random

/*
* Mies Van Beek
* CCID: mies
* Student ID: 1850463
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DecisionMakerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DecisionScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

//Generate a random number between 0 and 1
fun ranNum(): Float{
    //Looked up how to generate a random number between 0 and 1
    //https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.random/-random/
    val random = Random.nextFloat()

    return random
}

//The Click Counter
@Composable
fun ClickCounter(
    clicks: Int,
){
    Text(
        text = "Clicks: $clicks",
    )
}

//Show what decision has been made
@Composable
fun DisplayDecision(
    decision: Int,
){
    when (decision) {
        0 -> {
            Text(
                text = "Yeah! 😻",
                fontSize = 48.sp


            )
        }
        1 -> {
            Text(
                text = "Nah. 😕",
                fontSize = 48.sp
            )
        }
        else -> {
            Text(
                text = "Should we go?",
                fontSize = 48.sp
            )
        }
    }
}


//The Screen
@Preview
@Composable
fun DecisionScreen(
    modifier: Modifier = Modifier,
){
    var clickCount by remember { mutableIntStateOf(0)}
    var displayDecision: Int by remember { mutableIntStateOf(-1) }

    Column(
        /*Cheatsheet for centering items in Jetpack Compose
        * Author: Sherry Yuan
        * https://proandroiddev.com/cheatsheet-for-centering-items-in-jetpack-compose-1e3534415237
        */

        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterVertically),

        ){

        //Title and decision result
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            DisplayDecision(displayDecision)

        }


        //The Three buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            //OK! Button
            Button(
                onClick = {
                    displayDecision = if(ranNum() >= 0.5){
                        0
                    } else{
                        1
                    }
                    clickCount += 1
                }

            ){
                Text("OK!")
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Meh Button
            Button(
                onClick = {
                    displayDecision = if(ranNum() >= 0.75){
                        0
                    } else{
                        1
                    }
                    clickCount += 1
                }

            ){
                Text("Meh")
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Nah button
            Button(
                onClick = {
                    displayDecision = if(ranNum() >= 0.9){
                        0
                    } else{
                        1
                    }
                    clickCount += 1
                }

            ){
                Text("Nah")
            }
        }

        //The click counter
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

            ){
            ClickCounter(clickCount)
        }

        //student ID and such
        Row(
            modifier = Modifier.fillMaxWidth().padding(50.dp),
            horizontalArrangement = Arrangement.Center

            ){
            Text(
                text = "Student ID: 1850463   |   CCID: mies",
                fontSize = 12.sp


            )
        }
    }
}