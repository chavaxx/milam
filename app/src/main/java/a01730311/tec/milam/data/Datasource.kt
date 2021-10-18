package a01730311.tec.milam.data

import a01730311.tec.milam.components.GameCard
import a01730311.tec.milam.R
import a01730311.tec.milam.components.Profile
import a01730311.tec.milam.games.PreviewFragmentDirections

class Datasource {
    fun loadGameCards(): List<GameCard> {
        return listOf(
            GameCard("memorandum", R.string.game1, R.string.description1, R.color.game_card1, R.drawable.memory, PreviewFragmentDirections.actionPreviewFragmentToMemoFragment(), R.string.memo),
            GameCard("letters", R.string.game2, R.string.description2, R.color.game_card2, R.drawable.puzzle, PreviewFragmentDirections.actionPreviewFragmentToFindLetterFragment(), R.string.letters),
            GameCard("maze", R.string.game3, R.string.description3, R.color.game_card3, R.drawable.maze, PreviewFragmentDirections.actionPreviewFragmentToMazeFragment(), R.string.maze),
            GameCard("pix_art", R.string.game4, R.string.description4, R.color.game_card4, R.drawable.pixart, PreviewFragmentDirections.actionPreviewFragmentToPixartFragment(), R.string.pix_art),
            GameCard("magic_number", R.string.game5, R.string.description5, R.color.game_card5, R.drawable.maths, PreviewFragmentDirections.actionPreviewFragmentToMagicNumberFragment(), R.string.magic_number),
            GameCard("simon_says", R.string.game6, R.string.description6, R.color.game_card6, R.drawable.simon_says, PreviewFragmentDirections.actionPreviewFragmentToSimonSaysFragment(), R.string.simon_says),
        )
    }


    fun loadAvatars(): List<Int> {
        return listOf(
            R.drawable.pikachu,
             R.drawable.squirtle,
            R.drawable.psyduck,
             R.drawable.rattata,
             R.drawable.eevee,
             R.drawable.snorlax
        )
    }
}