package a01730311.tec.milam.games.simonSays

import a01730311.tec.milam.R
import kotlin.random.Random


class SimonSaysModel {

    private var buttonID = arrayOf(R.id.redBtn, R.id.blueBtn, R.id.yellowBtn, R.id.greenBtn)
    private lateinit var sequence: ArrayList<Int>
    private var inGame = false
    private var isCorrect = false
    private var score = 0
    private var currentButton = 0

    fun getInGame(): Boolean {
        return inGame
    }

    fun getMaxScore(): Int {
        return score - 1
    }

    fun getCurrentScore(): Int {
        return sequence.size - 1
    }

    fun getIsCorrect(): Boolean {
        return isCorrect
    }

    fun getSequence(): ArrayList<Int> {
        isCorrect = false
        return sequence
    }


    fun setMaxScore(newScore: Int) {
        score = newScore
    }


    // start the game

    fun startGame(): Int {
        inGame = true
        sequence = ArrayList()
        currentButton = 0
        val newButton: Int = getNewButton()
        sequence.add(newButton)
        return newButton
    }


    private fun getNewButton(): Int {
        val rand = Random
        val randomIndex: Int = rand.nextInt(4)
        return buttonID[randomIndex]
    }

    fun validateButton(buttonID: Int) {

        if (!inGame) return
        if (currentButton < sequence.size && buttonID == sequence[currentButton]) {
            currentButton++
        } else {
            if (sequence.size > score) score = sequence.size
            isCorrect = false
            sequence = ArrayList()
            inGame = false
            return
        }
        if (currentButton == sequence.size) increaseLevel()
    }


    private fun increaseLevel() {
        isCorrect = true
        currentButton = 0
        val button = getNewButton()
        sequence.add(button)
    }

}