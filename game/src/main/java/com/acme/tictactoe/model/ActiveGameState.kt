package com.acme.tictactoe.model

import com.acme.tictactoe.model.datatypes.GameMode
import com.acme.tictactoe.model.entities.Player

data class ActiveGameState(
        val currentTurn: Player,
        val mode: GameMode = GameMode.InProgress,
        val winner: Player? = null,
        val gameBoard: GameBoard = GameBoard()
)