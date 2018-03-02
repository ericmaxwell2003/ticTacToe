package com.acme.tictactoe.presenter;


import com.acme.tictactoe.view.TicTacToeView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * There are a lot more tests we can and should write but for now, just a few smoke tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class TicTacToePresenterTests {

    private TicTacToePresenter presenter;

    @Mock
    private TicTacToeView view;

    @Before
    public void setup() {
        presenter = new TicTacToePresenter(view);
    }

    private void clickAndAssertValueAt(int row, int col, String expectedValue) {
        presenter.onButtonSelected(row, col);
        verify(view).setButtonText(row, col, expectedValue);
    }

    /**
     * This test will simulate and verify o is the winner.
     *
     *    X | X | X
     *    O |   |
     *      | O |
     */
    @Test
    public void test3inRowAcrossTopForX() {

        clickAndAssertValueAt(0,0, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(1,0, "O");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(0,1, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(2,1, "O");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(0,2, "X");
        verify(view).showWinner("X");

    }


    /**
     * This test will simulate and verify x is the winner.
     *
     *    O | X | X
     *      | O |
     *      | X | O
     */
    @Test
    public void test3inRowDiagonalFromTopLeftToBottomForO() {

        clickAndAssertValueAt(0,1, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(0,0, "O");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(2,1, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(1,1, "O");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(0,2, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(2,2, "O");
        verify(view).showWinner("O");

    }


}
