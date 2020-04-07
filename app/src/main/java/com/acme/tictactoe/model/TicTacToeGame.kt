package com.acme.tictactoe.model

import kotlinx.coroutines.channels.Channel

class TicTacToeGame(val players: List<Player>) {

    private var currentTurnIndex = 0

    private var _gameStatus = ActiveGameState(currentTurn = players[currentTurnIndex])
    val gameStatus = Channel<ActiveGameState>(capacity = 9)

    suspend private fun updateGameStatus(newGameState: ActiveGameState) {
        _gameStatus = newGameState
        gameStatus.send(newGameState)
    }

    /**
     * Mark the current row for the player who's current turn it is.
     * Will perform no-op if the arguments are out of range or if that position is already played.
     * Will also perform a no-op if the game is already over.
     *
     * @param row 0..2
     * @param col 0..2
     * @return the player that moved or null if we did not move anything.
     */
    suspend fun mark(row: Int, col: Int) {

        val currentState = _gameStatus
        val board = currentState.gameBoard
        val playerThatMoved = currentState.currentTurn

        // If we're still playing
        if (currentState.mode == Mode.InProgress) {

            // And a valid move was performed
            if(board.mark(row, col , playerThatMoved)) {

                // If that move resulted in a win, mark winner and game finished
                if (board.isWinningMoveByPlayer(playerThatMoved, row, col)) {
                    updateGameStatus(_gameStatus.copy(mode = Mode.Finished, winner = playerThatMoved))

                // Else, the other player is up!
                } else { // flip the current turn and continue
                    updateGameStatus(_gameStatus.copy(currentTurn = players[++currentTurnIndex % 2]))
                }
            }
        }
    }


    /**
     * Restart or start a new game, will clear the board and win status
     */
    suspend fun restart() {
        updateGameStatus(_gameStatus.copy(
                currentTurn = players[0],
                mode = Mode.InProgress,
                winner = null,
                gameBoard = GameBoard()))
    }

}