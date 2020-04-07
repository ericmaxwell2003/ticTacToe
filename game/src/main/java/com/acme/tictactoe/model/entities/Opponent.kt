package com.acme.tictactoe.model.entities

// Implement the contract
data class Opponent(override val id: String, val name: String) : Identifiable