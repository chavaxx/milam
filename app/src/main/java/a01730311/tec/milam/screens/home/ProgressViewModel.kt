package a01730311.tec.milam.screens.home

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel

class ProgressViewModel:ViewModel() {
    // IMPORTANT: THESE ARE THE KEYS TO SAVE SCORE IN EACH GAME
    private val games: MutableSet<String> = mutableSetOf("simon_says","pix_art","memorandum","magic_number","maze","letters")
    private val gamesScore: HashMap<String, MutableList<Int>> = hashMapOf()
    private lateinit var userID: String
    private lateinit var gamesPreferences : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun getDataFromPreferences(activity: FragmentActivity?, id: String) {
        gamesPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!
        userID = id

        for (game in games) {
            val levels = gamesPreferences.getStringSet(userID + "_" + game + "_levels", mutableSetOf("1")) as MutableSet
            val scores: MutableList<Int> = mutableListOf()

            for (level in levels) {
                val score = gamesPreferences.getInt(userID + "_" + game + "_" + level,0)
                scores.add(score)
            }

            gamesScore[game] = scores
        }
    }


    fun getScore(game: String): Int? {
        return gamesScore[game]?.get(0)
    }

    fun getScore(game: String, level:String):Int? {
        return gamesScore[game]?.get(level.toInt() - 1)
    }

    fun setScore(game: String, score: Int, level: String) {

        editor = gamesPreferences.edit()

        if (gamesScore[game]?.size!! < level.toInt()) {
            println("adding levels")
            val levels = mutableSetOf<String>()
            for (i in 1..level.toInt()){
                println(gamesScore[game])
                levels.add(i.toString())
            }
            gamesScore[game]?.add(0)
            editor.putStringSet(userID + "_" + game + "_levels", levels)
        }

        editor.putInt(userID + "_" + game + "_" + level, score)
        gamesScore[game]?.set(level.toInt() - 1, score)

        editor.apply()
    }

    fun getMaxLevel(game: String): String {
        return gamesScore[game]?.size!!.toString()
    }

    fun setScore(game: String, score: Int) {
        editor = gamesPreferences.edit()
        editor.putInt(userID + "_" + game + "_" + "1", score)
        gamesScore[game]?.set(0, score)
        editor.apply()
    }

    fun getAllScores():HashMap<String, MutableList<Int>> {
        return gamesScore
    }


}