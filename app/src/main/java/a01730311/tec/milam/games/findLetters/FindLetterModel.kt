package a01730311.tec.milam.games.findLetters

import com.example.findnumber.LetterButon

class FindLetterModel(level : Int) {
    //it finds a random letter in the alphabet
    private val letterToFind : Char = ('A'..'Z').random()
    private val chosenLetters : List<LetterButon>
    private var correctLetters : Int
    private var objectiveLetters : Int
    private val letterLvl : FindLetterLevels
    private var hasWon : Boolean = false

    init{
        //it creates a list of all the letters minus the chosen letter
        val allowedLetters = ('A'..'Z' subtract listOf(letterToFind))
        val randomLetters: ArrayList<Char> = arrayListOf()
        //it initializes the correctLetters to 0
        correctLetters = 0
        //it loades the level from the FindLetterLevels
        letterLvl = FindLetterLevels.values()[level] //determine level

        //it determines how many pieces is going to render
        val numPieces = letterLvl.getNumPieces()
        //it determines what is the objective to find
        objectiveLetters = letterLvl.getObjectiveLetter()
        //it adds to the array randomLetters the letters that the user does not have to find
        (0 until numPieces - objectiveLetters).map { randomLetters.add(allowedLetters.random()) }
        //it adds the letters to find
        (numPieces - objectiveLetters until numPieces).map { randomLetters.add(letterToFind) }
        //it shuffles the letters
        randomLetters.shuffle()
        //for every letter it creates an object, and it saves it in chosenLetters
        chosenLetters = randomLetters.map{ LetterButon(it) }
    }

    //fun that returns the string of the punctuation
    fun renderPunctuation() : String{
        return "$correctLetters / $objectiveLetters"
    }

    //it returns the letter to find
    fun getLetterToFind() : String{
        return letterToFind.toString()
    }

    //it returns the chosenLetters
    fun getChosenLetters(): List<LetterButon>{
        return chosenLetters
    }

    //it returns the level
    fun getLvl(): FindLetterLevels{
        return letterLvl
    }

    //it returns if the level has ended
    fun getHasWon(): Boolean{
        return hasWon
    }

    //logic for when the user select a letter
    fun selectLetter(position : Int) {
        //it finds the letter
        val letter = chosenLetters[position]
        //if the letter is already selected it returns
        if(letter.isSelected) return
        //logic for when it chooses a wrong letter,
        //it returns
        if(letter.letter != letterToFind) {
            lostGame()
            return
        }
        //if the letter is correct it increases the variable
        //correctLetters and changes the state of the letter to selected
        correctLetters++
        letter.isSelected = true
        //if correctLetters equals objectiveLetters, it means the game has ended
        if(correctLetters == objectiveLetters){
            winGame()
            return
        }
    }

    //changes the state of the game
    fun winGame(){
        hasWon = true
    }

    fun lostGame(){

    }
}