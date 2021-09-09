package a01730311.tec.milam.data

import a01730311.tec.milam.GameCard
import a01730311.tec.milam.R

class Datasource {
    fun loadGameCards(): List<GameCard> {
        return listOf<GameCard>(
            GameCard(R.string.game1, R.color.game_card1, R.drawable.memory),
            GameCard(R.string.game2, R.color.game_card2, R.drawable.puzzle),
            GameCard(R.string.game3, R.color.game_card3, R.drawable.maze),
            GameCard(R.string.game4, R.color.game_card4, R.drawable.pixart),
            GameCard(R.string.game5, R.color.game_card5, R.drawable.memory),
            GameCard(R.string.game6, R.color.game_card6, R.drawable.simon_says)
        )
    }
}