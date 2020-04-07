package com.acme.tictactoe.model.entities

import com.acme.tictactoe.model.datatypes.PlayerSymbol

data class Player(
        val opponent: Opponent,
        val symbol: PlayerSymbol
)