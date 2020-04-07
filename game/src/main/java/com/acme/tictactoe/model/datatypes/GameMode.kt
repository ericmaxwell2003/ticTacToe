package com.acme.tictactoe.model.datatypes

sealed class GameMode {
    object InProgress : GameMode()
    object Finished : GameMode()
}