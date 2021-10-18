package a01730311.tec.milam.games.pixart

import android.util.Log

class PixarteModel(level : Int) {
    private val pixelsCollection : List<PixelState>
    private val colorNumber : Array<String>
    private var pixarteLevels : PixarteLevels
    private var color : String
    private val pixelsToSolve : Int
    private var correctPixels : Int = 0
    private var hasWon : Boolean = false

    init {
        pixarteLevels = PixarteLevels.values()[level]
        pixelsCollection = pixarteLevels.getPixelBoard().map { PixelState(it) }
        pixelsToSolve = pixelsCollection.filter { item -> item.identifier != -1 }.count()
        colorNumber = pixarteLevels.getColors()
        color = colorNumber[0]
    }

    fun getPixels() : List<PixelState> {
        return pixelsCollection
    }

    fun getLevel() : PixarteLevels {
        return pixarteLevels
    }

    fun getHasWon() : Boolean {
        return hasWon
    }

    fun changeColorPixel(position : Int) {
        val pixel = pixelsCollection[position]
        pixel.hasColor = true
        if(!pixel.hasCorrectColor){
            pixel.color = this.color
            if(pixel.color == colorNumber[pixel.identifier-1]){
                pixel.hasCorrectColor = true
                correctPixels++
            }
        }
        if(correctPixels == pixelsToSolve){
            endGame()
        }
    }

    fun changePickerColor(position: Int){
        color = colorNumber[position]
        this.color = color
    }

    fun endGame(){
        hasWon = true
    }

}