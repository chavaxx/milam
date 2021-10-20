package a01730311.tec.milam.games.memo

//data class Memorycard that contains the attributes of a card
data class MemoryCard(
    val identifier: Int,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false,
)
