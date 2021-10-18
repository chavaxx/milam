package a01730311.tec.milam.games

import a01730311.tec.milam.R
import a01730311.tec.milam.components.Game
import a01730311.tec.milam.components.GameCard
import a01730311.tec.milam.screens.home.ProgressViewModel
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

class GameViewModel:ViewModel() {
    private lateinit var selectedGameCard: GameCard
    private lateinit var currentLevel: String
    private lateinit var progressViewModel: ProgressViewModel


    fun init(progressView: ProgressViewModel) {
        progressViewModel = progressView
    }

    fun setGame(game: GameCard) {
        selectedGameCard = game
        currentLevel = "1"
    }

    fun getDescription():Int {
        return selectedGameCard.about
    }

    fun getDirections():NavDirections {
        return selectedGameCard.directions
    }

    fun getID():String {
        return selectedGameCard.gameID
    }

    fun getScore(): String {
        val maxLevel = progressViewModel.getMaxLevel(selectedGameCard.gameID)
        val highestScore = progressViewModel.getScore(selectedGameCard.gameID, maxLevel)
        if (highestScore == 0) return ""
        return "Puntaje más alto: " + highestScore
    }

    fun getName(): Int {
        return selectedGameCard.labelText
    }

    fun getImage(): Int {
        return selectedGameCard.srcCompat
    }

    fun getDescriptionScore(game: String): String {
        val maxLevel = progressViewModel.getMaxLevel(game)
        val highestScore = progressViewModel.getScore(game, maxLevel)
        if (highestScore == 0) {
            if (maxLevel == "1") {
                return "Sin datos"
            }
            return "Nivel " + maxLevel
        } else {
            if (maxLevel == "1") {
                return "Puntaje más alto: " + highestScore
            }
            return "Nivel " + maxLevel + " - " + highestScore
        }
    }

    fun getLevel(): String {
        val maxLevel = progressViewModel.getMaxLevel(selectedGameCard.gameID)
        if (maxLevel == "1") return "Nivel actual"
        return "Nivel " + progressViewModel.getMaxLevel(selectedGameCard.gameID)
    }
}