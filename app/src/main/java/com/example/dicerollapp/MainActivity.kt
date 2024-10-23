package com.example.dicerollapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dicerollapp.ui.theme.DiceRollAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: DiceViewModel = viewModel()
            DiceRollScreen(viewModel)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceRollAppTheme {
        Greeting("Android")
    }
}
@Composable
fun DiceRollScreen(viewModel: DiceViewModel) {
    val diceValue by viewModel.diceValue.collectAsState()
    val player1Score by viewModel.player1Score.collectAsState()
    val player2Score by viewModel.player2Score.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Lancer de Dé", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Valeur du Dé : $diceValue", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Score Joueur 1 : $player1Score", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Score Joueur 2 : $player2Score", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = { viewModel.rollDice() }) {
                Text(text = "Lancer le Dé")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { viewModel.resetGame() }) {
                Text(text = "Réinitialiser le Jeu")
            }
        }
    }
}
@Composable
fun SimpleStateDiceRoll() {
    var diceValue by remember { mutableStateOf(1) }
    var player1Score by remember { mutableStateOf(0) }
    var player2Score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Lancer de Dé", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Valeur du Dé : $diceValue", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Score Joueur 1 : $player1Score", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Score Joueur 2 : $player2Score", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = {
                diceValue = (1..6).random()
                if (Math.random() < 0.5) {
                    player1Score += diceValue
                } else {
                    player2Score += diceValue
                }
            }) {
                Text(text = "Lancer le Dé")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                player1Score = 0
                player2Score = 0
                diceValue = 1
            }) {
                Text(text = "Réinitialiser le Jeu")
            }
        }
    }
}
