package a01730311.tec.milam.components

import androidx.navigation.NavDirections

data class Game(val id: String, val scores: HashMap<String, Int>, val labelText: Int, val descriptionText: Int, val backgroundColor: Int, val srcCompat: Int, val directions: NavDirections)