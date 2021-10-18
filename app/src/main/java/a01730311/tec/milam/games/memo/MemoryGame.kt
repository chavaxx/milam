package a01730311.tec.milam.games.memo

import a01730311.tec.milam.games.memo.utils.DEFAULT_ICONS

//class to create a game that reicibes memoryLevels (the board)
class MemoryGame(private val memoryLevels: MemoryLevels){

    //initialize variables
    val cards: List<MemoryCard>
    var numPairsFound = 0
    private var indexOfSigleSelectedCard:Int? =null
    //shuffle all the default icons, take a number equal to number of pairs and double it to make the board
    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(memoryLevels.getPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map{ MemoryCard(it)}
    }
    //function to flip the card and check if a match is found
    fun flipCard(position: Int):Boolean {
        val  card = cards[position]
        var foundMatch = false
        if(indexOfSigleSelectedCard == null){
            restoreCards()
            indexOfSigleSelectedCard = position
        } else{
            foundMatch = checkForMatch(indexOfSigleSelectedCard!!, position)
            indexOfSigleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }
    //function to checks if the cards 1 and 2 are a match and updates the numPairsFound
    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if(cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }
    //function to set all cards to faceDown
    private fun restoreCards() {
        for(card in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }
    //function to check if the user has already won
    fun haveWonGame(): Boolean {
        return numPairsFound == memoryLevels.getPairs()
    }
    //function to get if the card is face Up
    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }
}