package com.acme.tictactoe.model

import com.acme.tictactoe.model.entities.Player

class GameBoard {

    private val cells: Array<Array<Player?>> = arrayOf(
            arrayOfNulls(3),
            arrayOfNulls(3),
            arrayOfNulls(3))

    fun mark(row: Int, col: Int, player: Player) : Boolean {
        return if(isValid(row, col)) {
            cells[row][col] = player
            true
        } else {
            false
        }
    }

    fun playerAt(row: Int, col: Int) = cells[row][col]

    private fun isValid(row: Int, col: Int) = when {
        isOutOfBounds(row) || isOutOfBounds(col) -> false
        isCellValueAlreadySet(row, col) -> false
        else -> true
    }

    private fun isOutOfBounds(idx: Int) = (idx < 0 || idx > 2)

    private fun isCellValueAlreadySet(row: Int, col: Int) = (cells[row][col] != null)

    /**
     * Algorithm adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
     * @param player
     * @param currentRow
     * @param currentCol
     * @return true if `player` who just played the move at the `currentRow`, `currentCol`
     * has a tic tac toe.
     */
    fun isWinningMoveByPlayer(player: Player?, currentRow: Int, currentCol: Int): Boolean {
        return (cells[currentRow][0] == player // 3-in-the-row
                && cells[currentRow][1] == player
                && cells[currentRow][2] == player) ||

                (cells[0][currentCol] == player // 3-in-the-column
                        && cells[1][currentCol] == player
                        && cells[2][currentCol] == player) ||

                (currentRow == currentCol // 3-in-the-diagonal
                        && cells[0][0] == player
                        && cells[1][1] == player
                        && cells[2][2] == player) ||

                (currentRow + currentCol == 2 // 3-in-the-opposite-diagonal
                        && cells[0][2] == player
                        && cells[1][1] == player
                        && cells[2][0] == player)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameBoard

        if (!cells.contentDeepEquals(other.cells)) return false

        return true
    }

    override fun hashCode(): Int {
        return cells.contentDeepHashCode()
    }
}