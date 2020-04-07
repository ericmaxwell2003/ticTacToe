package com.acme.tictactoe.view.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.acme.tictactoe.model.Player
import com.acme.tictactoe.model.Symbol

@BindingAdapter("app:player")
fun playerBinding(button: TextView, player: Player?) {
    button.text = when(player?.symbol) {
        Symbol.X -> "X"
        Symbol.O -> "O"
        null -> " "
    }
}