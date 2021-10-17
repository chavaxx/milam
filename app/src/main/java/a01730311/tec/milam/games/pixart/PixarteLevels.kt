package a01730311.tec.milam.games.pixart

enum class PixarteLevels(val numPixels : Int) {
    LVL1(99);

    fun getWidth(): Int {
        return when (this) {
            LVL1 -> 11
        }
    }

    fun getHeight(): Int {
        return numPixels / getWidth()
    }

    fun getPixelBoard(): IntArray {
        return when (this) {
            LVL1 -> intArrayOf(
                1,1,1,1,1,1,1,1,1,1,1,
                1,1,2,2,1,1,1,2,2,1,1,
                1,2,2,2,2,1,2,2,2,2,1,
                1,2,2,2,2,2,2,2,2,2,1,
                1,1,2,2,2,2,2,2,2,1,1,
                1,1,1,2,2,2,2,2,1,1,1,
                1,1,1,1,2,2,2,1,1,1,1,
                1,1,1,1,1,2,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,
                )
        }
    }

    fun getColors(): Array<String> {
        return when (this) {
            LVL1 -> arrayOf("#000000", "#FF0000")
        }
    }
}