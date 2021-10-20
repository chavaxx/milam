package a01730311.tec.milam.games.memo
//enum class for the board and game, reicibes the amount of game cards
enum class MemoryLevels(val cardNumber: Int) {
    //enums for amount of cards based on level
    Level1(4),
    Level2(6),
    Level3(8),
    Level4(10),
    Level5(12),
    Level6(14),
    Level7(16),
    Level8(18),
    Level9(20),
    Level10(22),
    Level11(24);

    //function to get the width of the board based on level
    fun getWidth():Int{
        return when(this){
            Level1->2
            Level2->2
            Level3->2
            Level4->2
            Level5->3
            Level6->3
            Level7->4
            Level8->4
            Level9->4
            Level10->4
            Level11->4
        }
    }
    //function to get the height of the board based on level
    fun getHeight():Int{
        return cardNumber/getWidth()
    }
    //function to get the number of pairs that the game has (number of cards/2)
    fun getPairs(): Int{
        return cardNumber/2
    }
}