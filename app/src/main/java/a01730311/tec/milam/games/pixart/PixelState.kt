package a01730311.tec.milam.games.pixart

data class PixelState(
    val identifier : Int,  //number that defines which color needs
    var hasColor : Boolean = false, //determines if it has a color
    var hasCorrectColor : Boolean = false, //determines if it has the correct color
    var color: String = "#FFFFFF" //determines which color it has
)
