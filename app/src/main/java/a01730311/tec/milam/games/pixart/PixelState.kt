package a01730311.tec.milam.games.pixart

data class PixelState(
    val identifier : Int,
    var hasColor : Boolean = false,
    var hasCorrectColor : Boolean = false,
    var color: String = "#FFFFFF"
)
