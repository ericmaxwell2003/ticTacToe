package com.acme.tictactoe.model

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

/**
 * There are a lot more tests we can and should write but for now, just a few smoke tests.
 */
@ExperimentalCoroutinesApi
class TicTacToeTests {

    private lateinit var ticTacToeGame: TicTacToeGame
    val p1 = Player(name = "P1", symbol = Symbol.X)
    val p2 = Player(name = "P2", symbol = Symbol.O)

    @Before
    fun setup() {
        ticTacToeGame = TicTacToeGame(listOf(p1, p2))
    }

    /**
     * This test will simulate and verify x is the winner.
     *
     * X | X | X
     * O |   |
     *   | O |
     */
    @Test
    fun test3inRowAcrossTopForX() = runBlockingTest {

        ticTacToeGame.mark(0, 0) // x
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(1, 0) // o
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(0, 1) // x
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(2, 1) // o
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(0, 2) // x
        expect(Mode.Finished, p1)
    }

    /**
     * This test will simulate and verify o is the winner.
     *
     * O | X | X
     *   | O |
     *   | X | O
     */
    @Test
    fun test3inRowDiagonalFromTopLeftToBottomForO() = runBlockingTest {

        ticTacToeGame.mark(0, 1) // x
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(0, 0) // o
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(2, 1) // x
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(1, 1) // o
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(0, 2) // x
        expect(Mode.InProgress, null)

        ticTacToeGame.mark(2, 2) // o
        expect(Mode.Finished, p2)

    }

    private suspend fun expect(mode: Mode, winner: Player?) {
        ticTacToeGame.gameStatus.receive().let {
            assertThat(it.winner, IsEqual(winner))
            assertThat(it.mode, IsEqual(mode))
        }
    }

}