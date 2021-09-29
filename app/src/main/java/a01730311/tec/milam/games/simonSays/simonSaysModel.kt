package a01730311.tec.milam.games.simonSays

import a01730311.tec.milam.R
import android.content.SharedPreferences
import kotlin.random.Random


class simonSaysModel {

    private var buttonID = arrayOf<Int>(R.id.redBtn, R.id.blueBtn, R.id.yellowBtn, R.id.greenBtn)
    private lateinit var sequence: ArrayList<Int>
    private var inGame = false
    private var isCorrect = false
    private var score = 0
    private lateinit var maxScore: SharedPreferences
    private var currentButton = 0

    fun getInGame(): Boolean {
        return inGame
    }

    fun getScore(): Int {
        return score
    }

    fun getIsCorrect(): Boolean {
        return isCorrect
    }

    fun getSequence(): ArrayList<Int>? {
        isCorrect = false
        return sequence
    }


    fun setScore(newScore: Int) {
        score = newScore
    }

    fun startGame(): Int {
        val newButton: Int
        inGame = true
        sequence = ArrayList()
        currentButton = 0
        newButton = getNewButton()
        sequence!!.add(newButton)
        return newButton
    }


    fun getNewButton(): Int {
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


    fun increaseLevel() {
        isCorrect = true
        currentButton = 0
        val button = getNewButton()
        sequence.add(button)
    }

}