package com.acme.tictactoe.view

import android.graphics.Canvas
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.acme.tictactoe.R
import com.acme.tictactoe.databinding.TictactoeBinding
import com.acme.tictactoe.viewmodel.TicTacToeViewModel

class TicTacToeActivity : AppCompatActivity() {

    val viewModel by viewModels<TicTacToeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: TictactoeBinding = DataBindingUtil.setContentView(this, R.layout.tictactoe)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                viewModel.onResetSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}