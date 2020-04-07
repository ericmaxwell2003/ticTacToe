package com.acme.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.acme.tictactoe.model.ActiveGameState
import com.acme.tictactoe.model.Player
import com.acme.tictactoe.model.Symbol
import com.acme.tictactoe.model.TicTacToeGame
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class TicTacToeViewModel(val defaultDispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    private val board = TicTacToeGame(listOf(
            Player("Player 1", Symbol.X),
            Player("Player 2", Symbol.O)))

    @ExperimentalCoroutinesApi
    val gameStateLiveData = liveData(defaultDispatcher) {
        board.gameStatus.consumeEach {
            emit(it)
        }
    }

//    val cells = ObservableArrayMap<String, String?>()
//    val winner = ObservableField<String?>()

    override fun onCleared() {
        super.onCleared()
    }

    fun onResetSelected() {
        viewModelScope.launch(defaultDispatcher) {
            board.restart()
        }
    }

    fun onClickedCellAt(row: Int, col: Int) {
        viewModelScope.launch(defaultDispatcher) {
            board.mark(row, col)
        }
    }

}
