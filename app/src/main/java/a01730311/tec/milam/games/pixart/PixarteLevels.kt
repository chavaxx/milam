package a01730311.tec.milam.games.pixart

enum class PixarteLevels(val numPixels : Int) {
    LVL1(20);

    fun getWidth(): Int {
        return when (this) {
            LVL1 -> 5
        }
    }

    fun getHeight(): Int {
        return numPixels / getWidth()
    }

    fun getPixelBoard(): IntArray {
        return when (this) {
            LVL1 -> intArrayOf(
                2,2,2,2,2,
                2,1,1,1,2,
                2,1,1,1,1,
                2,2,2,2,2,)
        }
    }

    fun getColors(): Array<String> {
        return when (this) {
            LVL1 -> arrayOf("#000000", "#FF0000")
        }
    }
}