package a01730311.tec.milam.games.findLetters

enum class FindLetterLevels(val level : Int) {
    LVL1(1),
    LVL2(2),
    LVL3(3),
    LVL4(4),
    LVL5(5);

    //it determines the width of the board
    //width * height must be equal to getNumPieces
    fun getWidth(): Int {
        return when(this) {
            LVL1 -> 4
            LVL2 -> 5
            LVL3 -> 6
            LVL4 -> 7
            LVL5 -> 7
        }
    }

    //it determines the height of the board
    fun getHeight(): Int {
        return when(this) {
            LVL1 -> 7
            LVL2 -> 7
            LVL3 -> 6
            LVL4 -> 7
            LVL5 -> 8
        }
    }

    //it determines how many letter the level will have
    fun getNumPieces(): Int {
        return when(this) {
            LVL1 -> 28
            LVL2 -> 35
            LVL3 -> 36
            LVL4 -> 49
            LVL5 -> 56
        }
    }

    //it determines how many letters it has to found
    //this number has to be lower than getNumPieces
    fun getObjectiveLetter(): Int {
        return when(this) {
            LVL1 -> 11
            LVL2 -> 11
            LVL3 -> 10
            LVL4 -> 9
            LVL5 -> 8
        }
    }

}