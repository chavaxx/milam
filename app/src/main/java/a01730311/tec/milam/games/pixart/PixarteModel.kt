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
        //it searches the current level
        pixarteLevels = PixarteLevels.values()[level]
        //it creates the pixelsCollection that is an array that represents
        //the state of every pixel
        pixelsCollection = pixarteLevels.getPixelBoard().map { PixelState(it) }
        //it determines how many pixels are valid
        pixelsToSolve = pixelsCollection.filter { item -> item.identifier != -1 }.count()
        //it gets the color which the user will be able to use in that
        //level from the pixarteLevel object
        colorNumber = pixarteLevels.getColors()
        //there is at least 1 color so the default is the first one
        color = colorNumber[0]
    }

    //it returns the drawing
    fun getPixels() : List<PixelState> {
        return pixelsCollection
    }

    //it returns the levels of the game
    fun getLevel() : PixarteLevels {
        return pixarteLevels
    }

    //it returns if the game has ended
    fun getHasWon() : Boolean {
        return hasWon
    }

    //it changes the color in pixels recycler view
    fun changeColorPixel(position : Int) {
        //searches the pixel in the pixelsCollection
        //by position
        val pixel = pixelsCollection[position]
        pixel.hasColor = true
        //if the pixel has not received its correct color yet
        if(!pixel.hasCorrectColor){
            //it changes the pixel color
            pixel.color = this.color
            //if the color it has received equals the one in colorNumber
            //it means the correct color has been established
            if(pixel.color == colorNumber[pixel.identifier-1]){
                //it changes the state of the pixel
                pixel.hasCorrectColor = true
                //it increments the correctPixels
                correctPixels++
            }
        }
        //if correct pixels equals pixelsToSolve it means
        //that there is no more pixels to fill so the game has
        //ended
        if(correctPixels == pixelsToSolve){
            endGame()
        }
    }

    //change the color that it will add to the pixels
    fun changePickerColor(position: Int){
        color = colorNumber[position]
        this.color = color
    }

    //changes the state of hasWon when the endGame has arrived
    fun endGame(){
        hasWon = true
    }

}