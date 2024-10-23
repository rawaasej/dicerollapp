package com.example.dicerollapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DiceViewModel : ViewModel() {
    private val _diceValue = MutableStateFlow(1)
    val diceValue: StateFlow<Int> = _diceValue

    private val _player1Score = MutableStateFlow(0)
    val player1Score: StateFlow<Int> = _player1Score

    private val _player2Score = MutableStateFlow(0)
    val player2Score: StateFlow<Int> = _player2Score

    fun rollDice() {
        val rolledValue = (1..6).random()
        _diceValue.value = rolledValue

        // Mettre à jour les scores des joueurs en fonction de celui qui a lancé
        if (Math.random() < 0.5) {
            _player1Score.value += rolledValue
        } else {
            _player2Score.value += rolledValue
        }
    }

    fun resetGame() {
        _player1Score.value = 0
        _player2Score.value = 0
        _diceValue.value = 1
    }
}
