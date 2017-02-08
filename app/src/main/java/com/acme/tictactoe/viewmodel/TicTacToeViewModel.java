package com.acme.tictactoe.viewmodel;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.acme.tictactoe.model.Board;
import com.acme.tictactoe.model.Player;

public class TicTacToeViewModel implements ViewModel {

    private static final String SAVED_TTT_VIEWMODEL = "TicTacToeViewModel_SaveBundle";

    private Board model;

    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacToeViewModel() {
        model = new Board();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable(SAVED_TTT_VIEWMODEL, model);
    }

    @Override
    public void loadFromSavedInstanceState(Bundle bundle)
    {
        model = bundle.getParcelable(SAVED_TTT_VIEWMODEL);
        // update the view
        if (model != null) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (model.valueAtCell(row, col) != null) {
                        cells.put("" + row + col, model.valueAtCell(row, col).toString());
                    }
                }
            }
            winner.set(model.getWinner() == null ? null : model.getWinner().toString());
        }
    }

    @Override
    public void onDestroy() {

    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }

}
