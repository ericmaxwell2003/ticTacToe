package com.acme.tictactoe.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.acme.tictactoe.model.Player
import com.acme.tictactoe.model.Symbol
import com.acme.tictactoe.model.TicTacToeGame
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.net.URI

class TicTacToeViewModel (val defaultDispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    private val gameModel = TicTacToeGame(listOf(
            Player("Player 1", Symbol.X),
            Player("Player 2", Symbol.O)))

    @ExperimentalCoroutinesApi
    val gameStateLiveData = liveData(defaultDispatcher) {
        Uri.parse("").fragment
        for(activeGameState in gameModel.gameStatus) {
            emit(activeGameState)
        }
    }

    override fun onCleared() {
        super.onCleared()
        gameModel.shutdown()
    }

    fun onResetSelected() {
        viewModelScope.launch(defaultDispatcher) {
            gameModel.restart()
        }
    }

    fun onClickedCellAt(row: Int, col: Int) {
        viewModelScope.launch(defaultDispatcher) {
            gameModel.mark(row, col)
        }
    }

}
