package a01730311.tec.milam.games

import a01730311.tec.milam.components.Game
import a01730311.tec.milam.components.GameCard
import a01730311.tec.milam.screens.home.ProgressViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

class GameViewModel:ViewModel() {
    private lateinit var selectedGameCard: GameCard
    private lateinit var currentLevel: String

    fun setGame(game: GameCard) {
        selectedGameCard = game
        currentLevel = "1"
    }

    fun getDirections():NavDirections {
        return selectedGameCard.directions
    }

    fun getID():String {
        return selectedGameCard.gameID
    }

    fun getScore(progressViewModel: ProgressViewModel): String {
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

    fun getLevel(progressViewModel: ProgressViewModel): String {
        val maxLevel = progressViewModel.getMaxLevel(selectedGameCard.gameID)
        if (maxLevel == "1") return "Nivel actual"
        return "Nivel " + progressViewModel.getMaxLevel(selectedGameCard.gameID)
    }
}