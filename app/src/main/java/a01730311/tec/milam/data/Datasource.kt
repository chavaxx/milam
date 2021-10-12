package a01730311.tec.milam.data

import a01730311.tec.milam.components.GameCard
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Profile
import a01730311.tec.milam.screens.home.HomeFragmentDirections

class Datasource {
    fun loadGameCards(): List<GameCard> {
        return listOf(
            GameCard(R.string.game2, R.string.description2, R.color.game_card2, R.drawable.puzzle, HomeFragmentDirections.actionHomeFragmentToFindLetterFragment()),
            GameCard(R.string.game3, R.string.description3, R.color.game_card3, R.drawable.maze, HomeFragmentDirections.actionHomeFragmentToMazeFragment()),
            GameCard(R.string.game5, R.string.description5, R.color.game_card5, R.drawable.memory, HomeFragmentDirections.actionHomeFragmentToMagicNumberFragment()),
            GameCard(R.string.game6, R.string.description6, R.color.game_card6, R.drawable.simon_says, HomeFragmentDirections.actionHomeFragmentToSimonSaysFragment())
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

    fun loadAvatars(): List<Profile> {
        return listOf(
            Profile("Pikachu", R.drawable.pikachu),
            Profile("Squirtle", R.drawable.squirtle),
            Profile("Pato", R.drawable.psyduck),
            Profile("Ratata", R.drawable.rattata),
            Profile("Eevee", R.drawable.eevee),
            Profile("Snorlax", R.drawable.snorlax)
        )
    }
}