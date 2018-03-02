package com.acme.tictactoe.viewModel;


import com.acme.tictactoe.viewmodel.TicTacToeViewModel;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * There are a lot more tests we can and should write but for now, just a few smoke tests.
 */
public class TicTacToeViewModelTests {

    private TicTacToeViewModel viewModel;

    @Before
    public void setup() {
        viewModel = new TicTacToeViewModel();
    }

    private void clickAndAssertValueAt(int row, int col, String expectedValue) {
        viewModel.onClickedCellAt(row, col);
        assertEquals(expectedValue, viewModel.cells.get("" + row + col));
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

        clickAndAssertValueAt(0,0, "X");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(1,0, "O");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(0,1, "X");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(2,1, "O");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(0,2, "X");
        assertEquals("X", viewModel.winner.get());

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

        clickAndAssertValueAt(0,1, "X");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(0,0, "O");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(2,1, "X");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(1,1, "O");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(0,2, "X");
        assertNull(viewModel.winner.get());

        clickAndAssertValueAt(2,2, "O");
        assertEquals("O", viewModel.winner.get());

    }


}
