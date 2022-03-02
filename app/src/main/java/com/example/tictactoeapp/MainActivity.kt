package com.example.tictactoeapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoeapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private var activePlayer = 1
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    fun buClick(view : View) {
        val buSelected = view as Button

        var cellId = 0
        when(buSelected.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }


        playGame(cellId, buSelected)
    }

    private fun playGame(cellId : Int, buSelected : Button) {
        if(activePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.redBu)
            player1.add(cellId)
            activePlayer = 2
            buSelected.isEnabled = false
            autoPlay()
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.greenBu)
            player2.add(cellId)
            activePlayer = 1
            buSelected.isEnabled = false
        }

        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //col1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //col2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //col3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //cross1
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        //cross2
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if(winner == 1) {
            Log.d("Player 1 win the game", winner.toString())
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            resetGame()
        } else if(winner == 2) {
            Log.d("Player 2 win the game", winner.toString())
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
            resetGame()
        }
    }
    
    private fun autoPlay() {
        val emptyCells = ArrayList<Int>()
        for( cellId in 1..9) {
            if(!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }

        if(emptyCells.size==0){
            resetGame()
        }

        val randIndex = (0 until emptyCells.size).random()
        val cellId = emptyCells[randIndex]
        val buSelected: Button?
        buSelected = when (cellId) {
            1 -> binding.button1
            2 -> binding.button2
            3 -> binding.button3
            4 -> binding.button4
            5 -> binding.button5
            6 -> binding.button6
            7 -> binding.button7
            8 -> binding.button8
            9 -> binding.button9
            else -> binding.button1
        }

        playGame(cellId, buSelected)
    }
    
    private fun resetGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()

        for(cellId in 1..9) {
            val buSelected : Button = when(cellId) {
                1 -> binding.button1
                2 -> binding.button2
                3 -> binding.button3
                4 -> binding.button4
                5 -> binding.button5
                6 -> binding.button6
                7 -> binding.button7
                8 -> binding.button8
                9 -> binding.button9
                else -> binding.button1
            }

            buSelected.text = ""
            buSelected.setBackgroundResource(R.color.whiteBu)
            buSelected.isEnabled = true
        }
    }

}