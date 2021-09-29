package a01730311.tec.milam.data

import a01730311.tec.milam.components.GameCard
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Profile

class Datasource {
    fun loadGameCards(): List<GameCard> {
        return listOf<GameCard>(
            GameCard(R.string.game1, R.string.description1, R.color.game_card1, R.drawable.memory),
            GameCard(R.string.game2, R.string.description2, R.color.game_card2, R.drawable.puzzle),
            GameCard(R.string.game3, R.string.description3, R.color.game_card3, R.drawable.maze),
            GameCard(R.string.game4, R.string.description4, R.color.game_card4, R.drawable.pixart),
            GameCard(R.string.game5, R.string.description5, R.color.game_card5, R.drawable.memory),
            GameCard(R.string.game6, R.string.description6, R.color.game_card6, R.drawable.simon_says)
        )
    }

    fun loadProfiles(): List<Profile> {
        return listOf<Profile>(
            Profile("Pepe", R.drawable.snorlax),
            Profile("Alex", R.drawable.pikachu),
            Profile("Chava", R.drawable.rattata),
            Profile("Bryan", R.drawable.eevee)
        )
    }
}