package a01730311.tec.milam.games.findLetters

import com.example.findnumber.LetterButon

class FindLetterModel(level : Int) {
    private val letterToFind : Char = ('A'..'Z').random()
    private val chosenLetters : List<LetterButon>
    private var correctLetters : Int
    private var objectiveLetters : Int
    private val letterLvl : FindLetterLevels
    private var hasWon : Boolean = false

    init{
        val allowedLetters = ('A'..'Z' subtract listOf(letterToFind))
        val randomLetters: ArrayList<Char> = arrayListOf()
        correctLetters = 0
        letterLvl = FindLetterLevels.values()[level] //determine level

        val numPieces = letterLvl.getNumPieces()
        objectiveLetters = letterLvl.getObjectiveLetter()
        (0 until numPieces - objectiveLetters).map { randomLetters.add(allowedLetters.random()) }
        (numPieces - objectiveLetters until numPieces).map { randomLetters.add(letterToFind) }
        randomLetters.shuffle()
        chosenLetters = randomLetters.map{ LetterButon(it) }
    }

    fun renderPunctuation() : String{
        return "$correctLetters / $objectiveLetters"
    }

    fun getLetterToFind() : String{
        return letterToFind.toString()
    }

    fun getChosenLetters(): List<LetterButon>{
        return chosenLetters
    }

    fun getLvl(): FindLetterLevels{
        return letterLvl
    }

    fun getHasWon(): Boolean{
        return hasWon
    }

    fun selectLetter(position : Int) {
        val letter = chosenLetters[position]
        if(letter.isSelected) return
        if(letter.letter != letterToFind) {
            lostGame()
            return
        }
        correctLetters++
        letter.isSelected = true
        if(correctLetters == objectiveLetters){
            winGame()
            return
        }
    }

    fun winGame(){
        hasWon = true
    }

    fun lostGame(){

    }
}