package a01730311.tec.milam.games

import a01730311.tec.milam.R
import a01730311.tec.milam.components.Game
import a01730311.tec.milam.components.Profile
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel

class ProgressViewModel:ViewModel() {
    // IMPORTANT: THESE ARE THE KEYS TO SAVE SCORE IN EACH GAME
    private val games: MutableSet<String> = mutableSetOf("simon_says","pix_art","memo","magic_number","maze","letters")
    private val gamesScore: HashMap<String, Game> = hashMapOf()
    private lateinit var gamesPreferences : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun getDataFromPreferences(activity: FragmentActivity?) {
        gamesPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!

        for (game in games) {
            val levels = gamesPreferences.getStringSet(game + "_levels", mutableSetOf("1")) as MutableSet
            val scores: HashMap<String, Int> = HashMap()

            for (level in levels) {
                val score = gamesPreferences.getInt(game + "_" + level,0)
                scores[level] = score
            }

            gamesScore[game] = Game(game, scores)
        }
    }


    fun getScore(game: String): Int? {
        return gamesScore[game]?.scores?.get("1")
    }

    fun getScore(game: String, level:String):Int? {
        return gamesScore[game]?.scores?.get(level)
    }

    fun setScore(game: String, score: Int, level: String) {
        editor = gamesPreferences.edit()
        editor.putInt(game + "_" + level, score)
        gamesScore[game]?.scores?.set(level, score)
        editor.apply()
    }

    fun setScore(game: String, score: Int) {
        editor = gamesPreferences.edit()
        editor.putInt(game + "_" + "1", score)
        gamesScore[game]?.scores?.set("1", score)
        editor.apply()
    }

    fun getAllScores():HashMap<String, Game> {
        return gamesScore
    }


}