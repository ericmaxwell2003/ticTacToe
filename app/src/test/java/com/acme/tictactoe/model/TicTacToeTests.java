package com.acme.tictactoe.model;

import android.os.Build;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * There are a lot more tests we can and should write but for now, just a few smoke tests.
 */
public class TicTacToeTests {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    /**
     * This test will simulate and verify x is the winner.
     *
     *    X | X | X
     *    O |   |
     *      | O |
     */
    @Test
    public void test3inRowAcrossTopForX() {

        board.mark(0,0); // x
        assertNull(board.getWinner());

        board.mark(1,0); // o
        assertNull(board.getWinner());

        board.mark(0,1); // x
        assertNull(board.getWinner());

        board.mark(2,1); // o
        assertNull(board.getWinner());

        board.mark(0,2); // x
        assertEquals(Player.X, board.getWinner());
    }


    /**
     * This test will simulate and verify o is the winner.
     *
     *    O | X | X
     *      | O |
     *      | X | O
     */
    @Test
    public void test3inRowDiagonalFromTopLeftToBottomForO() {

        board.mark(0,1); // x
        assertNull(board.getWinner());

        board.mark(0,0); // o
        assertNull(board.getWinner());

        board.mark(2,1); // x
        assertNull(board.getWinner());

        board.mark(1,1); // o
        assertNull(board.getWinner());

        board.mark(0,2); // x
        assertNull(board.getWinner());

        board.mark(2,2); // o
        assertEquals(Player.O, board.getWinner());

    }

}
