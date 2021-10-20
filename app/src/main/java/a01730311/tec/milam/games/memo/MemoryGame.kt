package a01730311.tec.milam.games.memo

import a01730311.tec.milam.games.memo.utils.DEFAULT_ICONS

class MemoryGame(private val memoryLevels: MemoryLevels){


    val cards: List<MemoryCard>
    var numPairsFound = 0
    private var indexOfSigleSelectedCard:Int? =null

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(memoryLevels.getPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map{ MemoryCard(it)}
    }

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

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if(cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for(card in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == memoryLevels.getPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }
}