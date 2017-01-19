package com.acme.tictactoe.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.acme.tictactoe.R;
import com.acme.tictactoe.model.Board;

public class TicTacToeActivity extends AppCompatActivity {

    private static String TAG = TicTacToeActivity.class.getName();

    private Board gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        gameBoard = new Board();
    }

    public void onCellClicked(View button) {

        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

    }
}
